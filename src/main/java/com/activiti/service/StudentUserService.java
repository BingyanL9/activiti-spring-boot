package com.activiti.service;

import java.security.Principal;
import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.activiti.model.Project;
import com.activiti.model.StudentUser;
import com.activiti.model.TeacherUser;
import com.activiti.repository.StudentUserRepository;

@Service
public class StudentUserService {

  private static final Logger logger = LoggerFactory.getLogger(StudentUserService.class);
  
  @Autowired
  private StudentUserRepository studentUserRepository;
  
  @Autowired
  private IdentityService identityService;
  
  public static final int PAZESIZE = 10;
  
  public StudentUser findByName(String userName) {
    return studentUserRepository.findByUserName(userName);
  }
  
  public StudentUser getCurrentUser() {
    return findByName(getCurrentUserName());
  }

  public String getCurrentUserDisplayName() {
    return studentUserRepository.getUserDisplayName(getCurrentUserName());
  }
  
  public String getCardNumByUserName() {
    return studentUserRepository.getCardnumByUserName(getCurrentUserName());
  }
  
  private String getCurrentUserName() {
    logger.debug("Start getting current username.");
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserDetails) {
      return ((UserDetails) principal).getUsername();
    }
    if (principal instanceof Principal) {
      return ((Principal) principal).getName();
    }
    return String.valueOf(principal);
  }

  public void save(StudentUser studentUser) {
    studentUserRepository.save(studentUser);
    saveAsActivityUser(studentUser);
  }
  
  public void update(StudentUser studentUser) {
    studentUserRepository.save(studentUser);
    updateActivityUser(studentUser);
  }
  
  public void saveAsActivityUser(StudentUser studentUser) {
    org.activiti.engine.identity.User activitiUser = identityService.newUser(studentUser.getUserName());
    activitiUser.setEmail(studentUser.getEmail());
    activitiUser.setLastName(studentUser.getDisplayName());
    activitiUser.setPassword(studentUser.getPassword());
    identityService.saveUser(activitiUser);
    Group users = identityService.createGroupQuery().groupType("users").singleResult();
    identityService.createMembership(activitiUser.getId(), users.getId());
    Group g =
        identityService.createGroupQuery().groupType(studentUser.getRole().toString()).singleResult();
    identityService.createMembership(activitiUser.getId(), g.getId());
  }
  
  public List<StudentUser> getAllStudentUser(){
    return studentUserRepository.findAll();
  }
  
  @Transactional
  public void deleteStudentUsers(List<StudentUser> studentUsers) {
    studentUserRepository.delete(studentUsers);
    for (StudentUser studentUser : studentUsers) {
      identityService.deleteUser(studentUser.getUserName());
      List<Group> groups =
          identityService.createGroupQuery().groupMember(studentUser.getUserName()).list();
      if (!groups.isEmpty()) {
        for (Group group : groups) {
          identityService.deleteMembership(studentUser.getUserName(), group.getId());
        }
      }
    }
  }
  
  private void updateActivityUser(StudentUser studentUser) {
    org.activiti.engine.identity.User activitiUser = identityService.createUserQuery().userId(studentUser.getUserName()).singleResult();
    activitiUser.setEmail(studentUser.getEmail());
    activitiUser.setLastName(studentUser.getDisplayName());
    activitiUser.setPassword(studentUser.getPassword());
    identityService.saveUser(activitiUser);
  }
  
  public List<StudentUser> getStudentUsers(int offset, int limit) {
    return studentUserRepository.findStudentUsers(new PageRequest(offset, limit));
  }
  
  public int getPageSize() {
    return (getAllStudentUser().size()  +  PAZESIZE  - 1) / PAZESIZE; 
  }
  
  public StudentUser findStudentUserByName(String userName) {
    return studentUserRepository.findByUserName(userName);
  }

}
