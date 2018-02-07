package com.activiti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activiti.repository.FeedbackRepository;

@Service
public class FeedbackService {

  @Autowired
  private FeedbackRepository feedbackRepository;
}
