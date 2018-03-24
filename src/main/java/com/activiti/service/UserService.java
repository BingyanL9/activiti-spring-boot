package com.activiti.service;

import java.security.Principal;

import org.activiti.engine.IdentityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.activiti.model.User;
import com.activiti.repository.UserRepository;

@Service
public class UserService {

  private static final Logger logger = LoggerFactory.getLogger(UserService.class);
  
  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private IdentityService identityService;
  
  public User findByName(String userName) {
    return userRepository.findByUserName(userName);
  }
  
  public void save(User user) {
    userRepository.save(user);
    org.activiti.engine.identity.User activitiUser = identityService.newUser(user.getUserName());
    activitiUser.setEmail(user.getEmail());
    activitiUser.setLastName(user.getDisplayName());
    activitiUser.setPassword(user.getPassword());
    identityService.saveUser(activitiUser);
  }
  
  public User getCurrentUser() {
    return findByName(getCurrentUserName());
  }

  public String getCurrentUserDisplayName() {
    return userRepository.getUserDisplayName(getCurrentUserName());
  }
  
  public String getCardNumByUserName() {
    return userRepository.getCardnumByUserName(getCurrentUserName());
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
