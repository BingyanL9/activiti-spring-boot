package com.activiti.service;

import java.security.Principal;
import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.activiti.model.StudentUser;
import com.activiti.model.TeacherUser;
import com.activiti.repository.TeacherUserRepository;

@Service
public class TeacherUserService {
  
  @Autowired
  private TeacherUserRepository teacherRepository;
  
  @Autowired
  private IdentityService identityService;
  
  public static final int PAZESIZE = 10;
  
  public TeacherUser findByName (String username) {
    return teacherRepository.findByUserName(username);
  }
  
  public TeacherUser findCurrentUser () {
    return findByName(getCurrentUserName());
  }
  
  private String getCurrentUserName() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserDetails) {
      return ((UserDetails) principal).getUsername();
    }
    if (principal instanceof Principal) {
      return ((Principal) principal).getName();
    }
    return String.valueOf(principal);
  }

  @Transactional
  public void save(TeacherUser teacherUser) {
    teacherRepository.save(teacherUser);
    if(teacherUser.getLeader()==null) {
      teacherUser.setLeader(teacherUser);
    }
    teacherRepository.save(teacherUser);
    saveAsActivityUser(teacherUser);
  }
  
  @Transactional
  public List<TeacherUser> getAllTeacherUser() {
    return teacherRepository.findAll();
  }
  
  @Transactional
  public void update(TeacherUser teacherUser) {
    teacherRepository.save(teacherUser);
    updateActivityUser(teacherUser);
  }

  private void updateActivityUser(TeacherUser teacherUser) {
    org.activiti.engine.identity.User activitiUser = identityService.createUserQuery().userId(teacherUser.getUserName()).singleResult();
    activitiUser.setEmail(teacherUser.getEmail());
    activitiUser.setLastName(teacherUser.getDisplayName());
    activitiUser.setPassword(teacherUser.getPassword());
    identityService.saveUser(activitiUser);
  }

  public void saveAsActivityUser(TeacherUser teacherUser) {
    org.activiti.engine.identity.User activitiUser = identityService.newUser(teacherUser.getUserName());
    activitiUser.setEmail(teacherUser.getEmail());
    activitiUser.setLastName(teacherUser.getDisplayName());
    activitiUser.setPassword(teacherUser.getPassword());
    identityService.saveUser(activitiUser);
    Group users = identityService.createGroupQuery().groupType("users").singleResult();
    identityService.createMembership(activitiUser.getId(), users.getId());
    Group g =
        identityService.createGroupQuery().groupType(teacherUser.getRole().toString()).singleResult();
    identityService.createMembership(activitiUser.getId(), g.getId());
  }
  
  @Transactional
  public void deleteTeachers(List<TeacherUser> teacherUsers) {
    teacherRepository.delete(teacherUsers);
    for (TeacherUser teacherUser : teacherUsers) {
      identityService.deleteUser(teacherUser.getUserName());
      List<Group> groups =
          identityService.createGroupQuery().groupMember(teacherUser.getUserName()).list();
      if (!groups.isEmpty()) {
        for (Group group : groups) {
          identityService.deleteMembership(teacherUser.getUserName(), group.getId());
        }
      }
    }
  }
  
  public List<TeacherUser> getTeacherUsers(int offset, int limit) {
    return teacherRepository.findTeacherUsers(new PageRequest(offset, limit));
  }
  
  public int getPageSize() {
    return (getAllTeacherUser().size()  +  PAZESIZE  - 1) / PAZESIZE; 
  }
}
