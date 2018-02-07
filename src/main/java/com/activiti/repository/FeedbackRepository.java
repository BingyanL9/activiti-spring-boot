package com.activiti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.activiti.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

}
