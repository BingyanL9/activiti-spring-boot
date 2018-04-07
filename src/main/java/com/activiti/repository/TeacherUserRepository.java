package com.activiti.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.activiti.model.StudentUser;
import com.activiti.model.TeacherUser;

@Repository
public interface TeacherUserRepository extends JpaRepository<TeacherUser, Long> {

  TeacherUser findByUserName(String userName);

  @Query("select email from TeacherUser t where t.userName=?1")
  String findTeacherEmail(String userName);
  
  @Query("select role from TeacherUser t where t.userName=?1")
  String findTeacherRole(String userName); 
  
  @Query("select t from TeacherUser t order by t.college")
  List<TeacherUser> findTeacherUsers(Pageable pageable);

}
