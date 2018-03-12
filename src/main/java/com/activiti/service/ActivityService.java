package com.activiti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activiti.model.Activity;
import com.activiti.model.ClubUser;
import com.activiti.repository.ActivityRepository;

@Service
public class ActivityService {

  @Autowired
  private ActivityRepository activityRepository;
  
  public Activity findByNameAndChargeClub (String activityName, ClubUser chargeClub) {
    return activityRepository.findByNameAndChargeClub(activityName, chargeClub);
  }
}
