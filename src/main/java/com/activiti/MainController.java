package com.activiti;

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

import com.activiti.model.DocumentExpenseViewObject;
import com.activiti.model.Role;
import com.activiti.service.UserService;


@Controller
@ComponentScan("com.activiti.service")
public class MainController {
  
  private static final Logger logger = LoggerFactory.getLogger(MainController.class);
  
  @Autowired
  private UserService userService;
  
  @Autowired
  private IdentityService identityService;
  
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
    String usersId = UUID.randomUUID().toString();
    Group users = identityService.newGroup(usersId);
    users.setName("普通用户");
    users.setType(Role.ordinary.toString());
    identityService.saveGroup(users);
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
      liberary.setName("财务组");
      liberary.setType(Role.finance_group.toString());
      identityService.saveGroup(liberary);
      String asset_processing_officeId = UUID.randomUUID().toString();
      Group asset_processing_office = identityService.newGroup(asset_processing_officeId);
      asset_processing_office.setName("财务组");
      asset_processing_office.setType(Role.finance_group.toString());
      identityService.saveGroup(asset_processing_office);
  }

  @RequestMapping(value = {"/apply"})
  public String apply(Map<String, Object> model) {
    model.put("user", userService.getCurrentUser());
    logger.debug("Start to apply an expense");
    model.put("DocumentExpenseViewObject", new DocumentExpenseViewObject());
    return "apply";
  }

}
