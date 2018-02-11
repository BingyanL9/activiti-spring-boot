package com.activiti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.activiti.model.ClubUser;
@Repository
public interface ClubUserRepository extends JpaRepository<ClubUser, Long> {
  
  ClubUser findByUserName(String userName);

}
