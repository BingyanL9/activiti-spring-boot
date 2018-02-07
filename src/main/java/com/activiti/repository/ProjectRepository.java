package com.activiti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.activiti.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
