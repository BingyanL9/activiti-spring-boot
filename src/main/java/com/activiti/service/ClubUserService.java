package com.activiti.service;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.activiti.model.ClubUser;
import com.activiti.repository.ClubUserRepository;
@Service
public class ClubUserService {

  private static final Logger logger = LoggerFactory.getLogger(StudentUserService.class);
  
  @Autowired
  private ClubUserRepository clubRepository;

  
  public ClubUser findByName(String userName) {
    return clubRepository.findByUserName(userName);
  }
  
  public ClubUser getCurrentUser() {
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
  
  public ClubUser findByCardNum(String cardNum) {
    return clubRepository.findByCardNum(cardNum);
  }
}
