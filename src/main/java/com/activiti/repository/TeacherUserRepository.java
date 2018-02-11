package com.activiti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.activiti.model.TeacherUser;

@Repository
public interface TeacherUserRepository extends JpaRepository<TeacherUser, Long> {

  TeacherUser findByUserName(String userName);

  @Query("select email from TeacherUser t where t.userName=?1")
  String findTeacherEmail(String userName);
  
  @Query("select role from TeacherUser t where t.userName=?1")
  String findTeacherRole(String userName); 

}
