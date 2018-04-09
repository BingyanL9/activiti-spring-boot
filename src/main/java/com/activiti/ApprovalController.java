package com.activiti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.activiti.model.Application;
import com.activiti.model.Approval;
import com.activiti.model.Approval_status;
import com.activiti.model.Project;
import com.activiti.model.Role;
import com.activiti.model.TeacherUser;
import com.activiti.service.ApplicationService;
import com.activiti.service.ApprovalService;
import com.activiti.service.MailService;
import com.activiti.service.Project_responService;
import com.activiti.service.UserService;

@Controller
@ComponentScan("com.activiti.service")
public class ApprovalController {

  private static final Logger logger = LoggerFactory.getLogger(ApprovalController.class);

  @Autowired
  private ApprovalService approvalService;

  @Autowired
  private RuntimeService runtimeService;
  
  @Autowired
  private IdentityService identityService;

  @Autowired
  private UserService userService;
  
  @Autowired
  private ApplicationService applicationService;
  
  @Autowired
  private MailService mailService;
  
  @Autowired
  private TaskService taskService;

  @RequestMapping(value = "/approval/{approvalId}/assignee", method = RequestMethod.PUT)
  public String assignTask(@PathVariable("approvalId") Long approvalId) {
    logger.debug("Start assignee a task by approval id : " + approvalId);
    Approval approval = approvalService.findById(approvalId);
    synchronized (this) {
      if (approval.getApproval_group_id() != null) {
        approval.setApproval_group_id(null);
        approval.setApproval_person(userService.getCurrentUser());
        Task task = taskService.createTaskQuery().processInstanceId(approval.getProcessInstanceId()).singleResult();
        task.setAssignee(userService.getCurrentUser().getUserName());
        if (approval.getApproval_statu().equals(Approval_status.pending)) {
          approval.setApproval_statu(Approval_status.level_1);
        }
        approvalService.save(approval);
      }
    }
    return "redirect:/approval";
  }

  @RequestMapping(value = "/approval/{approvalId}", method = RequestMethod.GET)
  public String getApproval(Map<String, Object> model,
      @PathVariable("approvalId") Long approvalId) {
    logger.debug("Start get an approval id : " + approvalId);
    Approval approval = approvalService.findById(approvalId);
    model.put("approval", approval);
    return "fragments/activityInfo :: expenseRejectForm";
  }

  @RequestMapping(value = "/approval/{approvalId}/rejected", method = RequestMethod.PUT)
  public String rejecctTask(@ModelAttribute Approval approval,
      @PathVariable("approvalId") Long approvalId) {
    logger.debug("Start assignee a task by approval id : " + approvalId);
    Approval newApproval = approvalService.findById(approvalId);
    newApproval.setApproval_statu(Approval_status.rejected);
    newApproval.setApproval_person(null);
    newApproval.setDisapproval_reason(approval.getDisapproval_reason());
    String processInstanceId = newApproval.getProcessInstanceId();
    Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
    Map<String, Object> variableMap = new HashMap<String, Object>();
    variableMap.put("operation", "disapproval");
    taskService.complete(task.getId(),variableMap);
    approvalService.save(newApproval);

    logger.debug("strat to send emial.");
    String[] to = {newApproval.getApplication().getOwner().getEmail()};
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("message", newApproval.getApplication().getDescription() + "被拒绝！拒绝理由为："
        + approval.getDisapproval_reason());
    model.put("sendDate", "2018");
    mailService.mail(to, "Notifation", model, "fragments/Email");
    return "redirect:/approval";
  }
  
  @RequestMapping(value = "/approval/{approvalId}/approval", method = RequestMethod.PUT)
  public String approvalTask(@PathVariable("approvalId") Long approvalId) {
    logger.debug("Start approval a task by approval id : " + approvalId);

    Approval approval = approvalService.findById(approvalId);
    String processInstanceId = approval.getProcessInstanceId();
    Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
    String taskName = task.getName();
    Map<String, Object> variableMap = new HashMap<String, Object>();
    variableMap.put("operation", "approval");
   System.out.println(taskName);
    if (taskName.equals("medical_group_approval") || taskName.equals("clubCharge approval")) {
      Group group = identityService.createGroupQuery().groupType(Role.finance_group.toString())
          .singleResult();
      approvalService.candidateGroup(approval, variableMap, group);
      approval.setApproval_statu(Approval_status.level_2);

    } else if (taskName.equals("finance_approval")) {
      approvalService.expenseTranser(approval, variableMap);

    } else if (taskName.equals("project_leaders_approval")) {
      String temp = approvalService.projectLeaderApproval(approval);
      if (temp.equals(approval.getApproval_statu().name())) {
        approvalService.specialApproval(approval, variableMap);
        updateLevel(approval);
      }
    } else if (taskName.equals("leader_approval")) {
      approvalService.specialApproval(approval, variableMap);
      updateLevel(approval);
    } else if (taskName.equals("asset_processing_office approval")
        || taskName.equals("library_approval")) {
      Group group = identityService.createGroupQuery().groupType(Role.finance_group.toString())
          .singleResult();
      approvalService.candidateGroup(approval, variableMap, group);
      updateLevel(approval);
    }
    approvalService.save(approval);
    return "redirect:/approval";
  }
  
  private void updateLevel(Approval approval) {
    if (approval.getApproval_statu().equals(Approval_status.level_1)) {
        approval.setApproval_statu(Approval_status.level_2);
      } else if (approval.getApproval_statu().equals(Approval_status.level_2)) {
        approval.setApproval_statu(Approval_status.level_3);
      } else if (approval.getApproval_statu().equals(Approval_status.level_3)) {
        approval.setApproval_statu(Approval_status.level_4);
      } else if (approval.getApproval_statu().equals(Approval_status.level_4)) {
        approval.setApproval_statu(Approval_status.level_5);
      }
  }

}
