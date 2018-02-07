package com.testing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testing.repository.FeedbackRepository;

@Service
public class FeedbackService {

  @Autowired
  private FeedbackRepository feedbackRepository;
}
