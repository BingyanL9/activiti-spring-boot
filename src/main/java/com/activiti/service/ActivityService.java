package com.activiti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.activiti.model.Activity;
import com.activiti.model.ClubUser;
import com.activiti.repository.ActivityRepository;
import com.activiti.repository.ClubUserRepository;

@Service
public class ActivityService {

  @Autowired
  private ActivityRepository activityRepository;
  
  @Autowired
  private ClubUserRepository clubUserRepository;
  
  public Activity findByNameAndChargeClub (String activityName, ClubUser chargeClub) {
    return activityRepository.findByNameAndChargeClub(activityName, chargeClub);
  }
  
  @Transactional
  public Activity findByCardNumAndActivityName(String activityName, String cardNum) {
    ClubUser clubUser = clubUserRepository.findByCardNum(cardNum);
    return findByNameAndChargeClub(activityName, clubUser);
  }
  
  
  public void save(Activity activity) {
    activityRepository.save(activity);
  }
}
