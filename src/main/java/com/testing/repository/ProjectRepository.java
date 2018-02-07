package com.testing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testing.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
