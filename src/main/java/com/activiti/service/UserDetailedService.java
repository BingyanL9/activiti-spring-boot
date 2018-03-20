package com.activiti.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.activiti.model.User;
import com.activiti.model.UserWithSalt;;


@Service
public class UserDetailedService implements UserDetailsService{
  private static final Logger logger = LoggerFactory.getLogger(UserDetailedService.class);
  
  @Autowired
  private StudentUserService studentUserService;
  
  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    logger.debug("Load user by userName: " + userName);
    List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
    User studentInfo = studentUserService.findByName(userName);
    
    if (studentInfo == null) {
      logger.error("User not found");
      throw new UsernameNotFoundException("User not found");
    }

    authorities.add(new SimpleGrantedAuthority(studentInfo.getRole().toString()));
    return new UserWithSalt(userName, userName, studentInfo.getPassword(),
        authorities);
  }

  


}
