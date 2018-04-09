package com.activiti.service;

import java.io.Serializable;
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
import com.activiti.model.Activity;
import com.activiti.model.Application;
import com.activiti.model.Application_Type;
import com.activiti.model.Approval;
import com.activiti.model.Approval_status;
import com.activiti.model.ClubUser;
import com.activiti.model.Payee;
import com.activiti.model.Project;
import com.activiti.model.Role;
import com.activiti.model.StudentUser;
import com.activiti.model.TeacherUser;
import com.activiti.repository.ApprovalRepository;

@Service
public class ApprovalService implements Serializable {

  private static final long serialVersionUID = 1L;

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

  @Autowired
  private TeacherUserService teacherUserService;

  @Autowired
  private ActivityService activityService;

  @Autowired
  private ProjectService projectService;

  @Autowired
  private StudentUserService studentUserService;

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
  public void saveWhenCreate(Payee payee, Approval approval, Application application,
      AbroadOtherInfo abroadOtherInfo) {
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
    if (approval.getApproval_statu().equals(Approval_status.level_1)
        && project_responService.getResponUserSize(project.getId()) >= 2) {
      complete(approval, project, "2");
      approval.setApproval_statu(Approval_status.level_2);

    } else if (approval.getApproval_statu().equals(Approval_status.level_2)
        && project_responService.getResponUserSize(project.getId()) >= 3) {
      complete(approval, project, "3");
      approval.setApproval_statu(Approval_status.level_3);

    } else if (approval.getApproval_statu().equals(Approval_status.level_3)
        && project_responService.getResponUserSize(project.getId()) >= 4) {
      complete(approval, project, "4");
      approval.setApproval_statu(Approval_status.level_4);

    } else if (approval.getApproval_statu().equals(Approval_status.level_4)
        && project_responService.getResponUserSize(project.getId()) >= 5) {
      complete(approval, project, "5");
      approval.setApproval_statu(Approval_status.level_5);
    }else {
      approval.setApproval_person(null);
    }
    save(approval);
    return temp;
  }

  @Transactional
  public void complete(Approval approval, Project project, String level) {
    String processInstanceId = approval.getProcessInstanceId();
    Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
    taskService.complete(task.getId());
    Task taskNext =
        taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
    TeacherUser projectLeader = project_responService.getResponUser(project.getId(), level);
    taskService.setAssignee(taskNext.getId(), projectLeader.getUserName());
    approval.setApproval_person(projectLeader);
  }

  @Transactional
  public void expenseTranser(Approval approval, Map<String, Object> variableMap) {
    String processInstanceId = approval.getProcessInstanceId();
    Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();

    if (!approval.getApplication().getPaymode().equals("cash")) {
      variableMap.put("paymode", "other");
      taskService.complete(task.getId(), variableMap);
      bankTranfer(approval.getApplication());
    } else {
      variableMap.put("paymode", "cash");
      taskService.complete(task.getId(), variableMap);
      cashApproval(approval);
    }
    approval.setApproval_person(null);
    approval.setApproval_group_id(null);
    save(approval);
    Task taskNext =
        taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
    taskService.complete(taskNext.getId());
  }

  @Transactional
  private void cashApproval(Approval approval) {
    String[] to = {approval.getApplication().getOwner().getEmail()};
    Map<String, Object> model = new HashMap<String, Object>();

    Application app = approval.getApplication();
    double balance = 0;
    String approvalMessage = app.getDescription() + "已批准！请前往财务处领取现金 " + app.getTotal() + "!";
    String rejectMessage = app.getDescription() + "已拒绝！您没有足够的预算！";

    if (app.getApplication_type().equals(Application_Type.ActivityExpense)) {
      Activity activity = app.getActivity();
      balance = activity.getBudget() - app.getTotal();

      if (balance >= 0) {
        model.put("message", approvalMessage);
        approval.setApproval_statu(Approval_status.approval);
        activity.setCash(app.getTotal());
        activityService.save(activity);
      } else {
        approval.setApproval_statu(Approval_status.rejected);
        model.put("message", rejectMessage);
        approval.setDisapproval_reason("您没有足够的预算！");
      }
    } else if (app.getApplication_type().equals(Application_Type.DailyExpense)) {
      TeacherUser teacher = teacherUserService.findByName(app.getOwner().getUserName());
      balance = teacher.getBudget() - app.getTotal();
      if (balance >= 0) {
        model.put("message", approvalMessage);
        approval.setApproval_statu(Approval_status.approval);
        teacher.setCash(app.getTotal());
        teacherUserService.update(teacher);
      } else {
        approval.setApproval_statu(Approval_status.rejected);
        approval.setDisapproval_reason("您没有足够的预算！");
        model.put("message", rejectMessage);
      }
    } else if (app.getApplication_type().equals(Application_Type.ProjectExpense)) {
      Project project = app.getProject();
      balance = project.getBudget() - app.getTotal();
      if (balance >= 0) {
        model.put("message", approvalMessage);
        approval.setApproval_statu(Approval_status.approval);
        project.setCash(app.getTotal());
        projectService.save(project);
      } else {
        approval.setApproval_statu(Approval_status.rejected);
        approval.setDisapproval_reason("您没有足够的预算！");
        model.put("message", rejectMessage);
      }
    } else {
      StudentUser studentUser =
          studentUserService.findStudentUserByName(app.getOwner().getUserName());
      balance = studentUser.getBudget() - app.getTotal();
      if (balance >= 0) {
        approval.setApproval_statu(Approval_status.approval);
        model.put("message", approvalMessage);
        studentUser.setCash(app.getTotal());
        studentUserService.update(studentUser);
      } else {
        approval.setApproval_statu(Approval_status.rejected);
        approval.setDisapproval_reason("您没有足够的预算！");
        model.put("message", rejectMessage);
      }
    }
    save(approval);
    model.put("sendDate", "2018");
    mailService.mail(to, "报销系统提示", model, "fragments/Email");
  }

  @Transactional
  public void bankTranfer(Application app) {
    Approval approval = app.getApproval();
    Map<String, Object> model = new HashMap<String, Object>();
    double balance = 0;

    String[] to = {app.getOwner().getEmail()};
    model.put("sendDate", "2018");

    if (app.getApplication_type().equals(Application_Type.ActivityExpense)) {
      Activity activity = app.getActivity();
      balance = activity.getBudget() - app.getTotal();
      if (balance >= 0) {
        activity.setBudget(balance);
        activityService.save(activity);
        model.put("message", app.getDescription() + "已批准！");
        approval.setApproval_statu(Approval_status.approval);

      } else {
        approval.setApproval_statu(Approval_status.rejected);
        model.put("message", app.getDescription() + "已拒绝！您没有足够的预算！");
        approval.setDisapproval_reason("您没有足够的预算！");
      }
    } else if (app.getApplication_type().equals(Application_Type.DailyExpense)) {
      TeacherUser teacher = teacherUserService.findByName(app.getOwner().getUserName());
      balance = teacher.getBudget() - app.getTotal();
      if (balance >= 0) {
        teacher.setBudget(balance);
        teacherUserService.update(teacher);
        model.put("message", app.getDescription() + "已批准！");
        approval.setApproval_statu(Approval_status.approval);
      } else {
        approval.setApproval_statu(Approval_status.rejected);
        approval.setDisapproval_reason("您没有足够的预算！");
        model.put("message", app.getDescription() + "已拒绝！您没有足够的预算！");
      }
    } else if (app.getApplication_type().equals(Application_Type.ProjectExpense)) {
      Project project = app.getProject();
      balance = project.getBudget() - app.getTotal();
      if (balance >= 0) {
        project.setBudget(balance);
        projectService.save(project);
        model.put("message", app.getDescription() + "已批准！");
        approval.setApproval_statu(Approval_status.approval);
      } else {
        approval.setApproval_statu(Approval_status.rejected);
        approval.setDisapproval_reason("您没有足够的预算！");
        model.put("message", app.getDescription() + "已拒绝！您没有足够的预算！");
      }
    } else {
      StudentUser studentUser =
          studentUserService.findStudentUserByName(app.getOwner().getUserName());
      balance = studentUser.getBudget() - app.getTotal();
      if (balance >= 0) {
        studentUser.setBudget(balance);
        studentUserService.update(studentUser);
        approval.setApproval_statu(Approval_status.approval);
        model.put("message", app.getDescription() + "已批准！");
      } else {
        approval.setApproval_statu(Approval_status.rejected);
        approval.setDisapproval_reason("您没有足够的预算！");
        model.put("message", app.getDescription() + "已拒绝！您没有足够的预算！");
      }
    }
    save(approval);
    mailService.mail(to, "报销系统提示", model, "fragments/Email");
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
    approval.setApproval_person(null);
    approval.setApproval_group_id(group.getId());

    List<User> groupUsers = identityService.createUserQuery().memberOfGroup(group.getId()).list();
    for (User user : groupUsers) {
      to.add(user.getEmail());
    }
    save(approval);
    model.put("message", "您有一份" + approval.getApplication().getDescription() + "需要认领!");
    model.put("sendDate", "2018");
    logger.debug("strat to send emial.");
    mailService.mail(to.toArray(new String[0]), "报销系统提示", model, "fragments/Email");
  }

  @Transactional
  public void specialApproval(Approval approval, Map<String, Object> variableMap) {
    Application application = approval.getApplication();

    Map<String, Object> map = new HashMap<String, Object>();
    if (applicationService.isNeedAssetProcessingOfficeApproval(application.getId())) {
      map.put("operation", "assetProcessingOfficeApproval");
      Group group = identityService.createGroupQuery()
          .groupType(Role.asset_processing_office.toString()).singleResult();
      candidateGroup(approval, map, group);

    } else if (applicationService.isNeedLibraryApproval(application.getId())) {
      map.put("operation", "libraryApproval");
      Group group =
          identityService.createGroupQuery().groupType(Role.liberary.toString()).singleResult();
      candidateGroup(approval, map, group);
    } else {
      Group group = identityService.createGroupQuery().groupType(Role.finance_group.toString())
          .singleResult();
      candidateGroup(approval, variableMap, group);
    }
    save(approval);
  }


}
