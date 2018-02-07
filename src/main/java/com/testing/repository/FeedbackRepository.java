package com.testing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testing.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

}
