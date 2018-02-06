package com.testing.model.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DemoApplication;
import com.testing.model.StudentInfo;
import com.testing.repository.StudentInfoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@ActiveProfiles("test")
@Transactional
public class StudentInfoServiceTest {
  
  private StudentInfo studentInfo;
  
  @Autowired
  private StudentInfoRepository studentInfoRepository;
  
  @Before
  public void setUp() {
    studentInfo = new StudentInfo((long) 001, "Echo", "123456", "bingyanl@126.com");
    studentInfoRepository.save(studentInfo);
  }
  
  @Test
  public void find() {
    String eamil = studentInfoRepository.findStudentEmail((long) 001);
    assertNotNull(eamil);
  }

}
