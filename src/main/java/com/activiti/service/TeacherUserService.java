package com.activiti.service;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.activiti.model.TeacherUser;
import com.activiti.repository.TeacherUserRepository;

@Service
public class TeacherUserService {
  
  @Autowired
  private TeacherUserRepository teacherRepository;
  
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

  public void save(TeacherUser teacherUser) {
    teacherRepository.save(teacherUser);
  }
}
