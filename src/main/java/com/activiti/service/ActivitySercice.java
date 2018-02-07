package com.activiti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activiti.repository.ActivityRepository;

@Service
public class ActivitySercice {

  @Autowired
  private ActivityRepository activityRepository;
}
