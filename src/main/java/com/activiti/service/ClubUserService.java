package com.activiti.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
}
