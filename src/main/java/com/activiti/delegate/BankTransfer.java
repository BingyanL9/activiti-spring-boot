package com.activiti.delegate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.activiti.model.Activity;
import com.activiti.model.Application;
import com.activiti.model.Application_Type;
import com.activiti.model.Approval;
import com.activiti.model.Approval_status;
import com.activiti.model.Project;
import com.activiti.model.StudentUser;
import com.activiti.model.TeacherUser;
import com.activiti.model.User;
import com.activiti.service.ActivityService;
import com.activiti.service.ApprovalService;
import com.activiti.service.MailService;
import com.activiti.service.ProjectService;
import com.activiti.service.StudentUserService;
import com.activiti.service.TeacherUserService;

public class BankTransfer implements JavaDelegate {
  private Expression application;

  @Autowired
  private TaskService taskService;

  @Autowired
  private RuntimeService runtimeService;

  @Autowired
  private ActivityService activityService;

  @Autowired
  private TeacherUserService teacherUserService;

  @Autowired
  private StudentUserService studentUserService;

  @Autowired
  private ProjectService projectService;
  
  @Autowired
  private MailService mailService;

  @Autowired
  private ApprovalService approvalService;

  @Transactional
  @Override
  public void execute(DelegateExecution execution) {
    Application app = (Application) application.getValue(execution);
    Approval approval = app.getApproval();
    Map<String, Object> model = new HashMap<String, Object>();
    double balance = 0;
    User user = app.getOwner();
    
    String[] to = {user.getEmail()};
    model.put("sendDate", "2018");
    model.put("message",  app.getDescription() + "已批准！");
    
    if (app.getApplication_type().equals(Application_Type.ActivityExpense)) {
      Activity activity = app.getActivity();
      balance = activity.getBudget() - app.getTotal();
      if (balance >= 0) {
        activity.setBudget(balance);
        activityService.save(activity);
        approval.setApproval_statu(Approval_status.approval);
        mailService.mail(to, "报销系统提示", model, "fragments/Email");
      } else {
        approval.setApproval_statu(Approval_status.rejected);
        approval.setDisapproval_reason("您没有足够的预算！");
      }
    } else if (app.getApplication_type().equals(Application_Type.DailyExpense)) {
      TeacherUser teacher = teacherUserService.findByName(user.getUserName());
      balance = teacher.getBudget() - app.getTotal();
      if (balance >= 0) {
        teacher.setBudget(balance);
        teacherUserService.update(teacher);
        approval.setApproval_statu(Approval_status.approval);
        mailService.mail(to, "报销系统提示", model, "fragments/Email");
      } else {
        approval.setApproval_statu(Approval_status.rejected);
        approval.setDisapproval_reason("您没有足够的预算！");
      }
    } else if (app.getApplication_type().equals(Application_Type.ProjectExpense)) {
      Project project = app.getProject();
      balance = project.getBudget() - app.getTotal();
      if (balance >= 0) {
        project.setBudget(balance);
        projectService.save(project);
        approval.setApproval_statu(Approval_status.approval);
        mailService.mail(to, "报销系统提示", model, "fragments/Email");
      } else {
        approval.setApproval_statu(Approval_status.rejected);
        approval.setDisapproval_reason("您没有足够的预算！");
      }
    } else {
      StudentUser studentUser = studentUserService.findStudentUserByName(user.getUserName());
      balance = studentUser.getBudget() - app.getTotal();
      if (balance >= 0) {
        studentUser.setBudget(balance);
        studentUserService.update(studentUser);
        approval.setApproval_statu(Approval_status.approval);
        mailService.mail(to, "报销系统提示", model, "fragments/Email");
      } else {
        approval.setApproval_statu(Approval_status.rejected);
        approval.setDisapproval_reason("您没有足够的预算！");
      }
    }
    approvalService.save(approval);
    Task task = taskService.createTaskQuery().processInstanceId(approval.getProcessInstanceId())
        .singleResult();
    taskService.complete(task.getId());
  }

}
