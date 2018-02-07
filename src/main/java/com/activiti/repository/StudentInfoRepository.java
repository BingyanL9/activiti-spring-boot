package com.activiti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.activiti.model.StudentInfo;

@Repository
public interface StudentInfoRepository extends JpaRepository<StudentInfo, Long>{
  
  StudentInfo findBySno(String sno);
  
  
  @Query("select email from StudentInfo s where s.sno=?1")
  String findStudentEmail(String sno);
}
