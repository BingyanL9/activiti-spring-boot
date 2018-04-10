package com.activiti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.activiti.model.Activity;
import com.activiti.model.ActivityBudgetApply;
import com.activiti.model.Application;
import com.activiti.model.Approval;
import com.activiti.model.CityTrafficExpenseViewObject;
import com.activiti.model.DocumentExpenseViewObject;
import com.activiti.model.Message;
import com.activiti.model.OnboardTravelExpenseViewObject;
import com.activiti.model.Project;
import com.activiti.model.Role;
import com.activiti.model.StudentUser;
import com.activiti.model.TeacherUser;
import com.activiti.model.TravelExpenseViewObject;
import com.activiti.model.User;
import com.activiti.model.Voucher;
import com.activiti.service.ActivityBudgetApplyService;
import com.activiti.service.ActivityService;
import com.activiti.service.ApplicationService;
import com.activiti.service.ApprovalService;
import com.activiti.service.ClubUserService;
import com.activiti.service.MailService;
import com.activiti.service.MessageService;
import com.activiti.service.ProjectService;
import com.activiti.service.StudentUserService;
import com.activiti.service.TeacherUserService;
import com.activiti.service.UserService;


@Controller
@ComponentScan("com.activiti.service")
public class MainController {
  
  private static final Logger logger = LoggerFactory.getLogger(MainController.class);
  
  @Autowired
  private UserService userService;
  
  @Autowired
  private IdentityService identityService;
  
  @Autowired
  private ApplicationService applicationService;
  
  @Autowired
  private ApprovalService approvalService;
  
  @Autowired
  private MessageService messageService;
  
  @Autowired
  private StudentUserService studentUserService;
  
  @Autowired
  private ClubUserService clubUserService;
  
  @Autowired
  private TeacherUserService teacherUserService;
  
  @Autowired
  private MailService mailService;
  
  @Autowired
  private ActivityBudgetApplyService activityBudgetApplyService;
  
  @Autowired
  private ActivityService activityService;
  
  private int initialTime = 0;
  
  @RequestMapping(value = {"/", "/home"})
  public String home(Map<String, Object> model) {
    User user = userService.getCurrentUser();
    model.put("user", user);
    List<Message> messages =  messageService.getMessages(0, messageService.PAZESIZE);
    model.put("messages", messages);
    model.put("pagefirst", "true");
    if(messageService.getPageSize() == 1 || messageService.getPageSize() == 0) {
      model.put("pagelast", "true");
    }
    setRole(model, user);
    
    logger.debug("Welcome to home page.");
//    logger.debug("strat to send emial.");
//    String[] to = {"bingyanl@126.com"};
//    Map<String, Object> model1 = new HashMap<String, Object>();
//    model1.put("message", "您有一份申请单需要审批!");
//    model1.put("sendDate", "2018");
//    mailService.mail(to, "Notifation", model1, "fragments/Email");
//      if (initialTime == 0) {
//        InitialGroup(userService.getCurrentUser());
//        initialTime ++;
//      }
    return "home";
  }

  private void setRole(Map<String, Object> model, User user) {
    if(studentUserService.findByName(user.getUserName()) != null) {
      model.put("role", "student");
    }else if(clubUserService.findByName(user.getUserName()) != null) {
      model.put("role", "club");
    }else if (teacherUserService.findByName(user.getUserName()) != null){
      model.put("role", "teacher");
    }
  }

private void InitialGroup(User user) {
    String ordinaryId = UUID.randomUUID().toString();
    Group ordinary = identityService.newGroup(ordinaryId);
    ordinary.setName("普通用户");
    ordinary.setType(Role.ordinary.toString());
    identityService.saveGroup(ordinary);
    String adminUsersId = UUID.randomUUID().toString();
    Group adminUsers = identityService.newGroup(adminUsersId);
    adminUsers.setName("管理员用户");
    adminUsers.setType(Role.admin.toString());
      identityService.saveGroup(adminUsers);
      String medicalId = UUID.randomUUID().toString();
      Group medical = identityService.newGroup(medicalId);
      medical.setName("医疗组");
      medical.setType(Role.medical_group.toString());
      identityService.saveGroup(medical);
      String finance_groupId = UUID.randomUUID().toString();
      Group finance_group = identityService.newGroup(finance_groupId);
      finance_group.setName("财务组");
      finance_group.setType(Role.finance_group.toString());
      identityService.saveGroup(finance_group);
      String liberaryId = UUID.randomUUID().toString();
      Group liberary = identityService.newGroup(liberaryId);
      liberary.setName("图书馆");
      liberary.setType(Role.liberary.toString());
      identityService.saveGroup(liberary);
      String asset_processing_officeId = UUID.randomUUID().toString();
      Group asset_processing_office = identityService.newGroup(asset_processing_officeId);
      asset_processing_office.setName("资产管理办公室");
      asset_processing_office.setType(Role.asset_processing_office.toString());
      identityService.saveGroup(asset_processing_office);
      String userId = UUID.randomUUID().toString();
      Group users = identityService.newGroup(userId);
      users.setName("用户");
      users.setType("users");
      identityService.saveGroup(users);
      userService.saveAsActivityUser(user);
  }

  @RequestMapping(value = {"/apply"})
  public String apply(Map<String, Object> model) {
    User user = userService.getCurrentUser();
    model.put("user", user);
    setRole(model, user);
    logger.debug("Start to apply an expense");
    model.put("DocumentExpenseViewObject", new DocumentExpenseViewObject());
    model.put("CityTrafficExpenseViewObject", new CityTrafficExpenseViewObject());
    model.put("TravelExpenseViewObject", new TravelExpenseViewObject());
    model.put("OnboardTravelExpenseViewObject", new OnboardTravelExpenseViewObject());
    model.put("menu", "apply");
    return "apply";
  }
  
  @RequestMapping(value = {"/applylist"})
  public String applyList(Map<String, Object> model) {
    User user = userService.getCurrentUser();
    model.put("user", user);
    setRole(model, user);
    logger.debug("Start to show apply list.");
    List<Application> applications = applicationService.getApplicationsByUser(user.getUserName());
    model.put("applications", applications);
    model.put("menu", "applyList");
    return "applyDashboard";
  }
  
  @RequestMapping(value = {"/approval"})
  public String approval(Map<String, Object> model) {
    User user = userService.getCurrentUser();
    model.put("user", user);
    setRole(model, user);
    logger.debug("Start to show approval.");
    List<Group> groups = identityService.createGroupQuery().groupMember(user.getUserName()).list();
    List<Approval> candidateApprovals = new ArrayList<Approval>();
    for (Group g :groups) {
      candidateApprovals.addAll(approvalService.getApprovalByGroupId(g.getId()));
    }
    List<Approval> assignApprovals = approvalService.getApprovalByUser(user.getUserName());
    model.put("assignApprovals", assignApprovals);
    model.put("candidateApprovals", candidateApprovals);
    model.put("menu", "approval");
    model.put("approval", new Approval());
    return "approval";
  }
  
  @RequestMapping(value = {"/admin"})
  public String admin(Map<String, Object> model) {
    User user = userService.getCurrentUser();
    model.put("user", user);
    logger.debug("Start to show admin page.");
    model.put("menu", "admin");
    return "admin";
  }
  
  @RequestMapping(value = {"/project"})
  public String project(Map<String, Object> model) {
    User user = userService.getCurrentUser();
    model.put("user", user);
    logger.debug("Start to show project page.");
    model.put("menu", "project");
    return "projectDashboard";
  }
  
  @RequestMapping(value = {"/issue"})
  public String issue(Map<String, Object> model) {
    User user = userService.getCurrentUser();
    model.put("user", user);
    logger.debug("Start to show admin page.");
    model.put("menu", "issue");
    model.put("message", new Message());
    List<Message> messages =  messageService.getMessages(0, messageService.PAZESIZE);
    model.put("messages", messages);
    model.put("pagefirst", "true");
    if(messageService.getPageSize() == 1 || messageService.getPageSize() == 0) {
      model.put("pagelast", "true");
    }
    return "issue";
  }
  
  @RequestMapping(value = {"/budget"})
  public String budget(Map<String, Object> model) {
    User user = userService.getCurrentUser();
    model.put("user", user);
    logger.debug("Start to show budget page.");
    model.put("menu", "budget");
    List<StudentUser> studentUsers =  studentUserService.getStudentUsers(0, studentUserService.PAZESIZE);
    List<TeacherUser> teacherUsers =  teacherUserService.getTeacherUsers(0, teacherUserService.PAZESIZE);
    List<Activity> activities =  activityService.getActivities(0, teacherUserService.PAZESIZE);
    model.put("studentUsers", studentUsers);
    model.put("studentUser", new StudentUser());
    model.put("teacherUsers", teacherUsers);
    model.put("teacherUser", new TeacherUser());
    model.put("activitys", activities);
    model.put("activity", new Activity());
    model.put("medicalpagefirst", "true");
    model.put("dailypagefirst", "true");
    model.put("activitypagefirst", "true");
    if(studentUserService.getPageSize() == 1 || studentUserService.getPageSize() == 0) {
      model.put("medicalpagelast", "true");
    }
    if(teacherUserService.getPageSize() == 1 || teacherUserService.getPageSize() == 0) {
      model.put("dailypagelast", "true");
    }
    if(activityService.getTotalPageSize() == 1 || activityService.getTotalPageSize() == 0) {
      model.put("activitypagelast", "true");
    }
    return "budget";
  }
  
  @RequestMapping(value = {"/activityapplication"})
  public String activityApplication(Map<String, Object> model) {
    User user = userService.getCurrentUser();
    model.put("user", user);
    logger.debug("Start to show activityapplication page.");
    model.put("menu", "activityapplication");
    String clubUserName = user.getUserName();
    model.put("role", "club");
    List<ActivityBudgetApply> activityBudgetApplys =
        activityBudgetApplyService.getActivityBudgetByClubName(clubUserName);
    model.put("activityBudgetApplys", activityBudgetApplys);
    List<Activity> activitys =
        activityService.findActivitysByClub(user.getUserName(), 0, activityService.PAZESIZE);
    model.put("activitys", activitys);
    model.put("activitypagefirst", "true");
    if (activityService.getPageSize(user.getUserName()) == 1
        || activityService.getPageSize(user.getUserName()) == 0) {
      model.put("activitypagelast", "true");
    }
    model.put("activityBudgetApply", new ActivityBudgetApply());
    return "activityapplication";
  }
  
  @RequestMapping(value = {"/activityapproval"})
  public String activityApproval(Map<String, Object> model) {
    User user = userService.getCurrentUser();
    model.put("user", user);
    logger.debug("Start to show activityapproval page.");
    model.put("menu", "activityapproval");
    String clubUserName = user.getUserName();
    model.put("role", "club");
    List<ActivityBudgetApply> activityBudgetApplys = activityBudgetApplyService.getActivityBudgetByApprovalUserName(clubUserName);
    model.put("activityBudgetApplys", activityBudgetApplys);
    model.put("activityBudgetApply", new ActivityBudgetApply());
    return "activityapproval";
  }

}
