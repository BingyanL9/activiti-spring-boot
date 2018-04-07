package com.activiti;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.activiti.model.ActivityBudgetApply;
import com.activiti.model.Approval_status;
import com.activiti.model.ClubUser;
import com.activiti.model.StudentUser;
import com.activiti.model.TeacherUser;
import com.activiti.model.User;
import com.activiti.service.ActivityBudgetApplyService;
import com.activiti.service.ActivityService;
import com.activiti.service.ClubUserService;
import com.activiti.service.ProjectService;
import com.activiti.service.StudentUserService;
import com.activiti.service.TeacherUserService;
import com.activiti.service.UserService;

@Controller
@ComponentScan("com.activiti.service")
public class BudgetController {

  @Autowired
  private StudentUserService studentUserService;

  @Autowired
  private TeacherUserService teacherUserService;

  @Autowired
  private UserService userService;
  
  @Autowired
  private ClubUserService clubUserService;
  
  @Autowired
  private ActivityBudgetApplyService activityBudgetApplyService;

  private static final Logger logger = LoggerFactory.getLogger(BudgetController.class);

  @RequestMapping(value = "/medicalbudget/page/{page}", method = RequestMethod.GET)
  public String getStudentUsers(Map<String, Object> model, @PathVariable("page") int page) {
    logger.debug("Start get a student user medical budget by page no : " + page);

    List<StudentUser> studentUsers =
        studentUserService.getStudentUsers(page, studentUserService.PAZESIZE);
    model.put("studentUsers", studentUsers);
    if (page == 0) {
      model.put("medicalpagefirst", "true");
    }
    if (studentUserService.getPageSize() -1  == page) {
      model.put("medicalpagelast", "true");
    }
    model.put("studentUser", new StudentUser());
    return "fragments/medicalBudget :: medicalBudget";
  }

  @RequestMapping(value = "/medicalbudget/{userName}", method = RequestMethod.GET)
  public String getStudentUser(Map<String, Object> model,
      @PathVariable("userName") String userName) {
    logger.debug("Start search a student user by user name: " + userName);

    StudentUser studentUser = studentUserService.findByName(userName);
    List<StudentUser> studentUsers = new ArrayList<StudentUser>();
    studentUsers.add(studentUser);
    model.put("medicalpagefirst", "true");
    model.put("medicalpagelast", "true");
    model.put("studentUsers", studentUsers);
    model.put("studentUser", new StudentUser());
    return "fragments/medicalBudget :: medicalBudget";
  }
  
  @RequestMapping(value = "/medicalbudget/{userName}", method = RequestMethod.PUT)
  public String updateMedicalBudget(@ModelAttribute StudentUser studentUser,
      @PathVariable("userName") String userName ) {
    logger.debug("Start edit a student user's budget by user name: " + userName);

    StudentUser newStudentUser = studentUserService.findByName(userName);
    newStudentUser.setBudget(studentUser.getBudget());
    studentUserService.update(newStudentUser);
    return "redirect:/budget";
  }
  
  @RequestMapping(value = "/dailybudget/page/{page}", method = RequestMethod.GET)
  public String getTeacherUsers(Map<String, Object> model, @PathVariable("page") int page) {
    logger.debug("Start get a student user daily budget by page no : " + page);

    List<TeacherUser> teacherUsers =
        teacherUserService.getTeacherUsers(page, teacherUserService.PAZESIZE);
    model.put("teacherUsers", teacherUsers);
    if (page == 0) {
      model.put("dailypagefirst", "true");
    }
    if (teacherUserService.getPageSize() -1  == page) {
      model.put("dailypagelast", "true");
    }
    model.put("teacherUser", new TeacherUser());
    return "fragments/dailyBudget :: dailyBudget";
  }

  @RequestMapping(value = "/dailybudget/{userName}", method = RequestMethod.GET)
  public String getTeacherUser(Map<String, Object> model,
      @PathVariable("userName") String userName) {
    logger.debug("Start search a teacher user by user name: " + userName);

    TeacherUser teacherUser = teacherUserService.findByName(userName);
    List<TeacherUser> teacherUsers = new ArrayList<TeacherUser>();
    teacherUsers.add(teacherUser);
    model.put("dailypagefirst", "true");
    model.put("dailypagelast", "true");
    model.put("teacherUsers", teacherUsers);
    model.put("teacherUser", new TeacherUser());
    return "fragments/dailyBudget :: dailyBudget";
  }
  
  @RequestMapping(value = "/dailybudget/{userName}", method = RequestMethod.PUT)
  public String updateDailyBudget(@ModelAttribute TeacherUser teacherUser,
      @PathVariable("userName") String userName ) {
    logger.debug("Start edit a teacher user's budget by user name: " + userName);

    TeacherUser newteacherUser = teacherUserService.findByName(userName);
    newteacherUser.setBudget(teacherUser.getBudget());
    teacherUserService.update(newteacherUser);
    return "redirect:/budget";
  }
  
  @RequestMapping(value = "/activitybudget", method = RequestMethod.POST)
  public String createActivityBudget(@ModelAttribute ActivityBudgetApply activityBudgetApply) {
    logger.debug("Start create a avtivity budget!");
    User user = userService.getCurrentUser();
    ClubUser clubUser = clubUserService.findByName(user.getUserName());
    activityBudgetApply.setChargeUserName(clubUser.getUserName());
    activityBudgetApply.setApprovalUserName(clubUser.getLeaderClub().getUserName());
    activityBudgetApply.setStatus(Approval_status.pending);
    activityBudgetApplyService.save(activityBudgetApply);
    return "redirect:/activityapplication";
  }

}
