package com.activiti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.activiti.model.StudentUser;

@Repository
public interface StudentUserRepository extends JpaRepository<StudentUser, Long>{
  
  StudentUser findByUserName(String userName);
  
  @Query("select displayName from StudentUser s where s.userName=?1")
  String getUserDisplayName(String userName);
}
