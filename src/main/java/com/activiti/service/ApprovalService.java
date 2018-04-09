package com.activiti.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.activiti.model.AbroadOtherInfo;
import com.activiti.model.Application;
import com.activiti.model.Approval;
import com.activiti.model.Approval_status;
import com.activiti.model.ClubUser;
import com.activiti.model.Payee;
import com.activiti.model.Project;
import com.activiti.model.Role;
import com.activiti.model.TeacherUser;
import com.activiti.repository.ApprovalRepository;

@Service
public class ApprovalService {

  @Autowired
  private ApprovalRepository approvalRepository;
  
  @Autowired
  private ApplicationService applicationService;
  
  @Autowired
  private PayeeService payeeService;
  
  @Autowired
  private TaskService taskService;
  
  @Autowired
  private Project_responService project_responService;
  
  @Autowired
  private MailService mailService;
  
  @Autowired
  private IdentityService identityService;
  
  @Autowired
  private AbroadOtherInfoService abroadOtherInfoService;
  
  private static final Logger logger = LoggerFactory.getLogger(ApplicationService.class);
  
  public void save(Approval approval) {
    approvalRepository.save(approval);
  }
  
  @Transactional
  public void saveWhenCreate(Payee payee, Approval approval, Application application) {
    
    logger.debug("Start to save applicaiton!");
    applicationService.save(application);
    application.setPayee(payee);
    application.setApproval(approval);
    applicationService.save(application);
    payee.setApplication(application);
    approval.setApplication(application);
    payeeService.save(payee);
    save(approval);
  }
  
  @Transactional
  public void saveWhenCreate(Payee payee, Approval approval, Application application, AbroadOtherInfo abroadOtherInfo) {
    logger.debug("Start to save applicaiton!");
    
    applicationService.save(application);
    application.setPayee(payee);
    application.setApproval(approval);
    application.setAbroadOtherInfo(abroadOtherInfo);
    applicationService.save(application);
    payee.setApplication(application);
    approval.setApplication(application);
    abroadOtherInfo.setApplication(application);
    payeeService.save(payee);
    abroadOtherInfoService.save(abroadOtherInfo);
    save(approval);
    
  }
  
  public List<Approval> getApprovalByUser(String userName) {
    return approvalRepository.getApprovalByUser(userName);
  }
  
  public List<Approval> getApprovalByGroupId(String groupId) {
    return approvalRepository.getApprovalByGroupId(groupId);
  }
  
  public Approval findById(Long approvalId) {
    return approvalRepository.findOne(approvalId);
  }
 
  @Transactional
  public String projectLeaderApproval(Approval approval) {
    Project project = approval.getApplication().getProject();
    String temp = approval.getApproval_statu().name();
    if (approval.getApproval_statu().equals(Approval_status.level_1) &&
      project_responService.getResponUserSize(project.getId()) >=2 ) {
      complete(approval, project, "2");
      approval.setApproval_statu(Approval_status.level_2);
      
    } else if (approval.getApproval_statu().equals(Approval_status.level_2) &&
        project_responService.getResponUserSize(project.getId()) >=3) {
      complete(approval, project, "3");
      approval.setApproval_statu(Approval_status.level_3);
      
    } else if (approval.getApproval_statu().equals(Approval_status.level_3)
        && project_responService.getResponUserSize(project.getId()) >=4) {
      complete(approval, project, "4");
      approval.setApproval_statu(Approval_status.level_4);
      
    } else if (approval.getApproval_statu().equals(Approval_status.level_4)
        && project_responService.getResponUserSize(project.getId()) >= 5) {
      complete(approval, project, "5");
      approval.setApproval_statu(Approval_status.level_5);
    }
    return temp;
  }

  @Transactional
  public void complete(Approval approval, Project project, String level) {
    String processInstanceId = approval.getProcessInstanceId();
    Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
    taskService.complete(task.getId());
    Task taskNext = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
    TeacherUser projectLeader = project_responService.getResponUser(project.getId(), level);
    taskService.setAssignee(taskNext.getAssignee(), projectLeader.getUserName());
  }

  @Transactional
  public void expenseTranser(Approval approval,Map<String, Object> variableMap) {
    String processInstanceId = approval.getProcessInstanceId();
    Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
    
    if (!approval.getApplication().getPaymode().equals("cash")) {
      variableMap.put("application", approval.getApplication());
      taskService.complete(task.getId(), variableMap);
    }else {
      variableMap.put("paymode", "cash");
      taskService.complete(task.getId(), variableMap);
      Task taskNext = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
      taskService.complete(taskNext.getId());
      String[] to = {approval.getApplication().getOwner().getEmail()};
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("message", approval.getApplication().getDescription() + "已批准！请前往财务处领取现金！");
      model.put("sendDate", "2018");
      mailService.mail(to, "报销系统提示", model, "fragments/Email");
    }
  }

  @Transactional
  public void candidateGroup(Approval approval, Map<String, Object> variableMap, Group group) {
    String processInstanceId = approval.getProcessInstanceId();
    Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
    taskService.complete(task.getId(), variableMap);
    
    List<String> to = new ArrayList<String>();
    Map<String, Object> model = new HashMap<String, Object>();
    Task taskNext =
        taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
    taskService.addCandidateGroup(taskNext.getId(), group.getId());
    approval.setApproval_group_id(group.getId());

    List<User> groupUsers = identityService.createUserQuery().memberOfGroup(group.getId()).list();
    for (User user : groupUsers) {
      to.add(user.getEmail());
    }
    model.put("message", "您有一份" + approval.getApplication().getDescription() + "需要认领!");
    model.put("sendDate", "2018");
    logger.debug("strat to send emial.");
    mailService.mail(to.toArray(new String[0]), "报销系统提示", model, "fragments/Email");
  }
  
  @Transactional
  public void specialApproval(Approval approval, Map<String, Object> variableMap) {
    Application application = approval.getApplication();
     
     if (application.getTravelItems().isEmpty()) {
       Group group =identityService.createGroupQuery().groupType(Role.finance_group.toString()).singleResult();
       candidateGroup(approval, variableMap, group);
     
     }else {
       Map<String, Object> map = new HashMap<String, Object>();
       if (applicationService.isNeedAssetProcessingOfficeApproval(application.getId())) {
         map.put("operation", "assetProcessingOfficeApproval");
         Group group =identityService.createGroupQuery().groupType(Role.asset_processing_office.toString()).singleResult();
         candidateGroup(approval, map, group);
         
       }else if (applicationService.isNeedLibraryApproval(application.getId())) {
         map.put("operation", "libraryApproval");
         Group group =identityService.createGroupQuery().groupType(Role.liberary.toString()).singleResult();
         candidateGroup(approval, map, group);
       }
     }
  }

  
}
