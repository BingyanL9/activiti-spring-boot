package com.activiti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.activiti.model.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

  Teacher findByTno(long tno);

  @Query("select email from Teacher t where t.tno=?1")
  String findTeacherEmail(Long tno);
  
  @Query("select role from Teacher t where t.tno=?1")
  String findTeacherRole(Long tno);
  
  

}
