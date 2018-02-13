package com.activiti.service;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.activiti.model.StudentUser;
import com.activiti.repository.StudentUserRepository;

@Service
public class StudentUserService {

  private static final Logger logger = LoggerFactory.getLogger(StudentUserService.class);
  
  @Autowired
  private StudentUserRepository studentUserRepository;
  
  public StudentUser findByName(String userName) {
    return studentUserRepository.findByUserName(userName);
  }
  
  public String getCurrentUserDisplayName() {
    return studentUserRepository.getUserDisplayName(getCurrentUserName());
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
 
}
