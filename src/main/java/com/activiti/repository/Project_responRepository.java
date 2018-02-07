package com.activiti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.activiti.model.Project_respon;
@Repository
public interface Project_responRepository extends JpaRepository<Project_respon, Long> {

}
