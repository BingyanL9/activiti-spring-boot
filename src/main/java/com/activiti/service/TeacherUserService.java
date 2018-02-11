package com.activiti.service;

import org.springframework.beans.factory.annotation.Autowired;
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

}
