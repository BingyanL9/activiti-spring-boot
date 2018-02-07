package com.activiti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.activiti.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
