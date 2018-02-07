package com.activiti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.activiti.model.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

}
