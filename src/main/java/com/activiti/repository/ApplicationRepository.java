package com.activiti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.activiti.model.Application;
import com.activiti.model.User;
@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
  
  @Query("select a from Application a where a.owner.userName =?1")
  List<Application> getApplicationsByUser(String userName);
  
  Application findById(Long id);

}
