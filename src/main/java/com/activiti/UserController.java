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
import org.springframework.web.bind.annotation.ResponseBody;

import com.activiti.model.ClubUser;
import com.activiti.model.ClubUserViewObject;
import com.activiti.model.StudentUser;
import com.activiti.model.TeacherUser;
import com.activiti.model.TeacherUserViewObject;
import com.activiti.service.ClubUserService;
import com.activiti.service.StudentUserService;
import com.activiti.service.TeacherUserService;

import net.sf.json.JSONObject;

@Controller
@ComponentScan("com.activiti.service")
public class UserController {

  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private StudentUserService studentUserService;
  
  @Autowired
  private ClubUserService clubUserService;

  @Autowired
  private TeacherUserService teacherUserService;

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
        newUser = studentUser;
        newUser.setUserName(userName);
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
  
  @RequestMapping(value = "/studentusers/{userName}", method = RequestMethod.GET)
  public String getStudentUser(Map<String, Object> model,
      @PathVariable("userName") String userName) {
    logger.debug("Start get a student user by user name: " + userName);

    StudentUser studentUser = studentUserService.findByName(userName);
    model.put("studentUser", studentUser);
    return "fragments/studentForm :: studentUserForm";
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
        || studentUser.getUserName() == "" || studentUser.getCollege() == "") {
      return true;
    }
    return false;
  }


  @RequestMapping(value = "/teacherusers", method = RequestMethod.POST)
  public @ResponseBody String addTeacherUser(@ModelAttribute TeacherUserViewObject teacherUserOJ) {
    logger.debug("Add a teacherUser!");
    JSONObject jsonObj = new JSONObject();
    if (isNullTeacherUser(teacherUserOJ)) {
      jsonObj.put("result", "Add Failed! Something is null");
    } else {
      try {
        TeacherUser teacherUser = new TeacherUser();
        teacherUser.setUserName(teacherUserOJ.getUserName());
        teacherUser.setDisplayName(teacherUserOJ.getDisplayName());
        teacherUser.setBudget(teacherUserOJ.getBudget());
        teacherUser.setCardNum(teacherUserOJ.getCardNum());
        teacherUser.setCollege(teacherUserOJ.getCollege());
        teacherUser.setEmail(teacherUserOJ.getEmail());
        if(teacherUserOJ.getLeader_userName()!=null) {
          teacherUser.setLeader(teacherUserService.findByName(teacherUserOJ.getLeader_userName()));
        }
        teacherUser.setPassword(teacherUserOJ.getPassword());
        teacherUser.setTitle(teacherUserOJ.getTitle());
        teacherUser.setRole(teacherUserOJ.getRole());
        teacherUserService.save(teacherUser);
        jsonObj.put("result", "Add Success!");
      } catch (Exception e) {
        logger.error(e.getMessage());
        jsonObj.put("result", "Add Failed!");
      }

    }
    return jsonObj.toString();
  }

  @ResponseBody
  @RequestMapping(value = "/teacherusers/{userName}", method = RequestMethod.PUT)
  public String editTeacherUser(@ModelAttribute TeacherUserViewObject teacherUserOJ,
      @PathVariable String userName) {
    logger.debug("Edit a studentUser by userName:" + userName);
    JSONObject jsonObj = new JSONObject();
    if (isNullTeacherUser(teacherUserOJ)) {
      jsonObj.put("result", "Edit Failed! Something is null");
    } else {
      try {
        TeacherUser newUser = teacherUserService.findByName(userName);
        newUser.setUserName(userName);
        newUser.setTitle(teacherUserOJ.getTitle());
        newUser.setDisplayName(teacherUserOJ.getDisplayName());
        newUser.setBudget(teacherUserOJ.getBudget());
        newUser.setCardNum(teacherUserOJ.getCardNum());
        newUser.setCollege(teacherUserOJ.getCollege());
        newUser.setEmail(teacherUserOJ.getEmail());
        if(teacherUserOJ.getLeader_userName()!=null) {
          newUser.setLeader(teacherUserService.findByName(teacherUserOJ.getLeader_userName()));
        }
        newUser.setPassword(teacherUserOJ.getPassword());
        newUser.setRole(teacherUserOJ.getRole());
        teacherUserService.update(newUser);
        jsonObj.put("result", "Edit Success!");
      } catch (Exception e) {
        logger.error(e.getMessage());
        jsonObj.put("result", "Edit Failed!");
      }

    }
    return jsonObj.toString();
  }

  @RequestMapping(value = "/teacherusers", method = RequestMethod.GET)
  public @ResponseBody List<TeacherUserViewObject> getAllTeacherUser() {
    List<TeacherUser> teacherUsers = teacherUserService.getAllTeacherUser();
    List<TeacherUserViewObject> teacherUserViewObjects = new ArrayList<TeacherUserViewObject>();
    for (TeacherUser teacherUser : teacherUsers) {
      TeacherUserViewObject teacherUserViewObject = new TeacherUserViewObject();
      teacherUserViewObject.setUserName(teacherUser.getUserName());
      teacherUserViewObject.setTitle(teacherUser.getTitle());
      teacherUserViewObject.setRole(teacherUser.getRole());
      teacherUserViewObject.setPassword(teacherUser.getPassword());
      teacherUserViewObject.setLeader_userName(teacherUser.getLeader().getUserName());
      teacherUserViewObject.setEmail(teacherUser.getEmail());
      teacherUserViewObject.setDisplayName(teacherUser.getDisplayName());
      teacherUserViewObject.setCollege(teacherUser.getCollege());
      teacherUserViewObject.setCardNum(teacherUser.getCardNum());
      teacherUserViewObject.setBudget(teacherUser.getBudget());
      teacherUserViewObjects.add(teacherUserViewObject);
    }
    return teacherUserViewObjects;
  }
  
  @RequestMapping(value = "/teacherusers/{userName}", method = RequestMethod.GET)
  public String getteacher(Map<String, Object> model,
      @PathVariable("userName") String userName) {
    logger.debug("Start get a teacher user by user name: " + userName);

    TeacherUser teacherUser = teacherUserService.findByName(userName);
    model.put("teacherUser", teacherUser);
    return "fragments/teacherForm :: teacherUserForm";
  }

  @ResponseBody
  @RequestMapping(value = "/teacherusers/{teacherUsers}", method = RequestMethod.DELETE)
  public String deleteTeacherUsers(@PathVariable String[] teacherUsers) {

    logger.debug("Start to delete teacher users! " + teacherUsers);
    JSONObject jsonObj = new JSONObject();
    List<TeacherUser> deletedTeacherUser = new ArrayList<TeacherUser>();
    for (int index = 0; index < teacherUsers.length; index++) {
      TeacherUser teacherUser = teacherUserService.findByName(teacherUsers[index]);
      deletedTeacherUser.add(teacherUser);
    }
    try {
      teacherUserService.deleteTeachers(deletedTeacherUser);
      jsonObj.put("result", "Delete Success!");
    } catch (Exception e) {
      logger.error(e.getMessage());
      jsonObj.put("result", "Delete Failed!");
    }
    return jsonObj.toString();
  }

  public boolean isNullTeacherUser(TeacherUserViewObject teacherUserOJ) {
    if (teacherUserOJ.getDisplayName() == "" || teacherUserOJ.getPassword() == ""
        || teacherUserOJ.getBudget() == 0.0 || teacherUserOJ.getCardNum() == ""
        || teacherUserOJ.getEmail() == "" || teacherUserOJ.getRole().name() == ""
        || teacherUserOJ.getUserName() == "" || teacherUserOJ.getTitle() == ""
        || teacherUserOJ.getCollege() == "") {
      return true;
    }
    return false;
  }

  
  @RequestMapping(value = "/clubusers", method = RequestMethod.POST)
  public @ResponseBody String addClubUser(@ModelAttribute ClubUserViewObject clubUserOJ) {
    logger.debug("Add a clubUser!");
    JSONObject jsonObj = new JSONObject();
    if (isNullClubUser(clubUserOJ)) {
      jsonObj.put("result", "Add Failed! Something is null");
    } else {
      try {
        ClubUser clubUser = new ClubUser();
        clubUser.setUserName(clubUserOJ.getUserName());
        clubUser.setDisplayName(clubUserOJ.getDisplayName());
        clubUser.setCardNum(clubUserOJ.getCardNum());
        clubUser.setCollege(clubUserOJ.getCollege());
        clubUser.setEmail(clubUserOJ.getEmail());
        clubUser.setPassword(clubUserOJ.getPassword());
        clubUser.setRole(clubUserOJ.getRole());
        if (clubUserOJ.getLeaderClubName()!=null) {
          clubUser.setLeaderClub(clubUserService.findByName(clubUserOJ.getLeaderClubName()));
        }
        clubUserService.save(clubUser);
        jsonObj.put("result", "Add Success!");
      } catch (Exception e) {
        logger.error(e.getMessage());
        jsonObj.put("result", "Add Failed!");
      }

    }
    return jsonObj.toString();
  }
  
  @ResponseBody
  @RequestMapping(value = "/clubusers/{userName}", method = RequestMethod.PUT)
  public String editTeacherUser(@ModelAttribute ClubUserViewObject clubUserOJ,
      @PathVariable String userName) {
    logger.debug("Edit a clubUser by userName:" + userName);
    JSONObject jsonObj = new JSONObject();
    if (isNullClubUser(clubUserOJ)) {
      jsonObj.put("result", "Add Failed! Something is null");
    } else {
      try {
        ClubUser newUser = clubUserService.findByName(userName);
        newUser.setUserName(userName);
        newUser.setDisplayName(clubUserOJ.getDisplayName());
        newUser.setCardNum(clubUserOJ.getCardNum());
        newUser.setCollege(clubUserOJ.getCollege());
        newUser.setEmail(clubUserOJ.getEmail());
        if(clubUserOJ.getLeaderClubName()!=null) {
          newUser.setLeaderClub(clubUserService.findByName(clubUserOJ.getLeaderClubName()));
        }
        newUser.setPassword(clubUserOJ.getPassword());
        newUser.setRole(clubUserOJ.getRole());
        clubUserService.update(newUser);
        jsonObj.put("result", "Edit Success!");
      } catch (Exception e) {
        logger.error(e.getMessage());
        jsonObj.put("result", "Edit Failed!");
      }

    }
    return jsonObj.toString();
  }
  
  @RequestMapping(value = "/clubusers", method = RequestMethod.GET)
  public @ResponseBody List<ClubUserViewObject> getAllClubUser() {
    List<ClubUser> clubUsers = clubUserService.getclubUsers();
    List<ClubUserViewObject> clubUserViewObjects = new ArrayList<ClubUserViewObject>();
    for (ClubUser clubUser : clubUsers) {
      ClubUserViewObject clubUserViewObject = new ClubUserViewObject();
      clubUserViewObject.setUserName(clubUser.getUserName());
      clubUserViewObject.setRole(clubUser.getRole());
      clubUserViewObject.setPassword(clubUser.getPassword());
      clubUserViewObject.setLeaderClubName(clubUser.getLeaderClub().getUserName());
      clubUserViewObject.setEmail(clubUser.getEmail());
      clubUserViewObject.setDisplayName(clubUser.getDisplayName());
      clubUserViewObject.setCollege(clubUser.getCollege());
      clubUserViewObject.setCardNum(clubUser.getCardNum());
      clubUserViewObjects.add(clubUserViewObject);
    }
    return clubUserViewObjects;
  }
  
  @ResponseBody
  @RequestMapping(value = "/clubusers/{clubUsers}", method = RequestMethod.DELETE)
  public String deleteClubUsers(@PathVariable String[] clubUsers) {

    logger.debug("Start to delete Club users! " + clubUsers);
    JSONObject jsonObj = new JSONObject();
    List<ClubUser> deletedClubUser = new ArrayList<ClubUser>();
    for (int index = 0; index < clubUsers.length; index++) {
      ClubUser clubUser = clubUserService.findByName(clubUsers[index]);
      deletedClubUser.add(clubUser);
    }
    try {
      clubUserService.deleteTeachers(deletedClubUser);
      jsonObj.put("result", "Delete Success!");
    } catch (Exception e) {
      logger.error(e.getMessage());
      jsonObj.put("result", "Delete Failed!");
    }
    return jsonObj.toString();
  }

  public boolean isNullClubUser(ClubUserViewObject clubUserOJ) {
    if (clubUserOJ.getDisplayName() == "" || clubUserOJ.getPassword() == ""
        || clubUserOJ.getCardNum() == ""  || clubUserOJ.getEmail() == "" 
        || clubUserOJ.getRole().name() == "" || clubUserOJ.getUserName() == "" 
        || clubUserOJ.getCollege() == "") {
      return true;
    }
    return false;
  }
}
