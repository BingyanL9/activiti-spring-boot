package com.testing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testing.repository.TeacherRepository;

@Service
public class TeacherService {
  
  @Autowired
  private TeacherRepository teacherRepository;

}
