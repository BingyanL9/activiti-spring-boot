package com.activiti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.activiti.model.Application;
@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

}
