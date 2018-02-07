package com.testing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testing.model.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

}
