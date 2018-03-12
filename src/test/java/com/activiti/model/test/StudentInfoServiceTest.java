package com.activiti.model.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.activiti.DemoApplication;
import com.activiti.model.Role;
import com.activiti.model.StudentUser;
import com.activiti.repository.StudentUserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@ActiveProfiles("test")
@Transactional
public class StudentInfoServiceTest {
  
  private StudentUser studentInfo;
  
  @Autowired
  private StudentUserRepository studentInfoRepository;
  
  @Before
  public void setUp() {
    studentInfo = new StudentUser("201810311100", "Echo", "123456", "bingyanl@126.com", "1234567890", Role.ordinary);
    studentInfoRepository.save(studentInfo);
  }
  
//  @Test
//  public void find() {
//    String email = studentInfoRepository.findStudentEmail("201810311100");
//    assertNotNull(email);
//    assertEquals(email,"bingyanl@126.com");
//    
//  }

}
