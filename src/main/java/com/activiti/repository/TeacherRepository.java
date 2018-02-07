package com.activiti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.activiti.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

  Teacher findById(long tno);

  @Query("select email from Teacher t where t.tno=?1")
  String findTeacherEmail(Long tno);
  
  @Query("select role from Teacher t where t.tno=?1")
  String findTeacherRole(Long tno);
  
  

}
