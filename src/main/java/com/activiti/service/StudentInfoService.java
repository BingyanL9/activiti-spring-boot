package com.activiti.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activiti.model.StudentInfo;
import com.activiti.repository.StudentInfoRepository;

@Service
public class StudentInfoService {

  private static final Logger logger = LoggerFactory.getLogger(StudentInfoService.class);
  
  @Autowired
  private StudentInfoRepository studentInfoRepository;
  
  public StudentInfo findStudentInfo(String sno) {
    return studentInfoRepository.findBySno(sno);
  }
  
  public String findStudentEmail(String sno) {
    logger.debug("Start finding a email of a student.");
    String email = studentInfoRepository.findStudentEmail(sno);
    return email;
  }
  
}
