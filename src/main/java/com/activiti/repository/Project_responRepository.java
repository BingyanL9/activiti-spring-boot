package com.activiti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.activiti.model.Project_respon;
import com.activiti.model.TeacherUser;
@Repository
public interface Project_responRepository extends JpaRepository<Project_respon, Long> {
  
  @Query("select p.charge from Project_respon p where p.project.id = ?1")
  List<TeacherUser> getResponUsers(Long projectId);
  
  @Query("select p.level from Project_respon p where p.project.id = ?1 ")
  List<String> getLevels(Long projectId);

}
