package com.activiti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.activiti.model.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

}
