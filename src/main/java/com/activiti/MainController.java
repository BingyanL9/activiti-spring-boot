package com.activiti;

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

import com.activiti.model.Application;
import com.activiti.model.CityTrafficExpenseViewObject;
import com.activiti.model.DocumentExpenseViewObject;
import com.activiti.model.Role;
import com.activiti.model.TravelExpenseViewObject;
import com.activiti.model.User;
import com.activiti.service.ApplicationService;
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
  
  private int initialTime = 0;
  
  @RequestMapping(value = {"/", "/home"})
  public String home(Map<String, Object> model) {
    model.put("user", userService.getCurrentUser());
    logger.debug("Welcome to home page.");
    
    if (initialTime == 0) {
      InitialGroup();
      initialTime ++;
    }
    return "home";
  }

private void InitialGroup() {
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
  }

  @RequestMapping(value = {"/apply"})
  public String apply(Map<String, Object> model) {
    model.put("user", userService.getCurrentUser());
    logger.debug("Start to apply an expense");
    model.put("DocumentExpenseViewObject", new DocumentExpenseViewObject());
    model.put("CityTrafficExpenseViewObject", new CityTrafficExpenseViewObject());
    model.put("TravelExpenseViewObject", new TravelExpenseViewObject());
    model.put("menu", "apply");
    return "apply";
  }
  
  @RequestMapping(value = {"/applylist"})
  public String applyList(Map<String, Object> model) {
    User user = userService.getCurrentUser();
    model.put("user", user);
    logger.debug("Start to show apply list.");
    List<Application> applications = applicationService.getApplicationsByUser(user.getUserName());
    model.put("applications", applications);
    model.put("menu", "applyList");
    return "applylist";
  }

}
