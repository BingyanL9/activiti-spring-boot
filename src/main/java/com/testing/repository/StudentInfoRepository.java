package com.testing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.testing.model.StudentInfo;


public interface StudentInfoRepository extends JpaRepository<StudentInfo, Long>{
  
  StudentInfo findById(Long sno);
  
  @Query("select email from StudentInfo s where s.sno=?1")
  String findStudentEmail(Long sno);
}