package com.activiti;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.activiti.model.StudentUser;
import com.activiti.service.StudentUserService;

import net.sf.json.JSONObject;

@Controller
@ComponentScan("com.activiti.service")
public class UserController {
  
  private static final Logger logger = LoggerFactory.getLogger(MainController.class);
  
  @Autowired
  private StudentUserService studentUserService;
  
  @RequestMapping(value = "/studentusers", method = RequestMethod.POST)
  public @ResponseBody String addStudentUser(@ModelAttribute StudentUser studentUser) {
    logger.debug("Add a studentUser!");
    JSONObject jsonObj = new JSONObject();
    if (isNullStudentUser(studentUser)) {
      jsonObj.put("result", "Add Failed! Something is null");
    } else {
      try {
        studentUserService.save(studentUser);
        jsonObj.put("result", "Add Success!");
      } catch (Exception e) {
        logger.error(e.getMessage());
        jsonObj.put("result", "Add Failed!");
      }

    }
    return jsonObj.toString();
  }
  
  @ResponseBody
  @RequestMapping(value = "/studentusers/{userName}", method = RequestMethod.PUT)
  public String editStudentUser(@ModelAttribute StudentUser studentUser,
      @PathVariable String userName) {
    System.out.println(userName);
    logger.debug("Edit a studentUser by userName:" + userName);
    JSONObject jsonObj = new JSONObject();
    if (isNullStudentUser(studentUser)) {
      jsonObj.put("result", "Edit Failed! Something is null");
    } else {
      try {
        StudentUser newUser = studentUserService.findByName(userName);
        newUser.setUserName(userName);
        newUser = studentUser;
        studentUserService.update(newUser);
        jsonObj.put("result", "Edit Success!");
      } catch (Exception e) {
        logger.error(e.getMessage());
        jsonObj.put("result", "Edit Failed!");
      }

    }
    return jsonObj.toString();
  }
  
  @RequestMapping(value = "/studentusers", method = RequestMethod.GET)
  public @ResponseBody List<StudentUser> getAllStudentUser() {
    List<StudentUser> studentUsers = studentUserService.getAllStudentUser();
    return studentUsers;
  }
  
  @ResponseBody
  @RequestMapping(value = "/studentusers/{studentUsers}", method = RequestMethod.DELETE)
  public String deleteStudentUsers(@PathVariable String[] studentUsers) {

    logger.debug("Start to delete Student users! " + studentUsers);
    JSONObject jsonObj = new JSONObject();
    List<StudentUser> deletedStudentUser = new ArrayList<StudentUser>();
    for (int index = 0; index < studentUsers.length; index++) {
      StudentUser studentUser = studentUserService.findByName(studentUsers[index]);
      deletedStudentUser.add(studentUser);
    }
    try {
      studentUserService.deleteStudentUsers(deletedStudentUser);
      jsonObj.put("result", "Delete Success!");
    } catch (Exception e) {
      logger.error(e.getMessage());
      jsonObj.put("result", "Delete Failed!");
    }
    return jsonObj.toString();
  }
  
  public boolean isNullStudentUser(StudentUser studentUser) {
    if (studentUser.getDisplayName() == "" || studentUser.getPassword() == ""
        || studentUser.getBudget() == 0.0 || studentUser.getCardNum() == ""
        || studentUser.getEmail() == "" || studentUser.getRole().name() == ""
        || studentUser.getUserName()=="") {
      return true;
    }
    return false;
  }

}
