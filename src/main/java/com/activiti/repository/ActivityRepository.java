package com.activiti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.activiti.model.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

}
