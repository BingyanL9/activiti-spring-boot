package com.activiti.model.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.activiti.DemoApplication;
import com.activiti.model.Role;
import com.activiti.model.StudentInfo;
import com.activiti.repository.StudentInfoRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@ActiveProfiles("test")
@Transactional
public class StudentInfoServiceTest {
  
  private StudentInfo studentInfo;
  
  @Autowired
  private StudentInfoRepository studentInfoRepository;
  
  @Before
  public void setUp() {
    studentInfo = new StudentInfo("201810311100", "Echo", "123456", "bingyanl@126.com",Role.ordinary);
    studentInfoRepository.save(studentInfo);
  }
  
  @Test
  public void find() {
    String eamil = studentInfoRepository.findStudentEmail("001");
    assertNotNull(eamil);
  }

}
