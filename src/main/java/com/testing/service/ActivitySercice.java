package com.testing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testing.repository.ActivityRepository;

@Service
public class ActivitySercice {

  @Autowired
  private ActivityRepository activityRepository;
}
