package com.activiti.service;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.activiti.model.Application_Type;
import com.activiti.model.StudentUser;
import com.activiti.repository.ClubUserRepository;
import com.activiti.repository.StudentUserRepository;

@Service
public class StudentUserService {

  private static final Logger logger = LoggerFactory.getLogger(StudentUserService.class);
  
  @Autowired
  private StudentUserRepository studentUserRepository;
  
  @Autowired
  private ClubUserService clubUserService;
  
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
  
  public Application_Type getApplicationTypebyCardnum( String cardNum ) {
    if (getCardNumByUserName().equals(cardNum)) {
      if (clubUserService.findByName(getCurrentUserName()) != null) {
        return Application_Type.ActivityExpense;
      }else
        return Application_Type.DailyExpense;
    }else
      return Application_Type.ProjectExpense;
  }
 
}
