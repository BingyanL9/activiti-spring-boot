package com.activiti.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.activiti.model.Activity;
import com.activiti.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
  
  @Query("select p from Project p where p.cardNum =?1")
  Project findByCardNum(String cardNum);
}
