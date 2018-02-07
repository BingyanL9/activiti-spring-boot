package com.testing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testing.model.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

}
