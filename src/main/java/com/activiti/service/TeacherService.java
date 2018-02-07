package com.activiti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activiti.repository.TeacherRepository;

@Service
public class TeacherService {
  
  @Autowired
  private TeacherRepository teacherRepository;

}
