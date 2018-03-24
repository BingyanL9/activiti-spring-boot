package com.activiti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.activiti.model.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long>  {

  User findByUserName(String userName);
  
  @Query("select displayName from User s where s.userName=?1")
  String getUserDisplayName(String userName);
  
  @Query("select cardNum from User s where s.userName=?1")
  String getCardnumByUserName(String userName);
}
