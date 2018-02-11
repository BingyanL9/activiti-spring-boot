package com.activiti.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
  
}
