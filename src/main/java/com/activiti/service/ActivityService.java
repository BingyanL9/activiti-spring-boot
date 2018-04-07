package com.activiti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.activiti.model.Activity;
import com.activiti.model.ClubUser;
import com.activiti.model.Message;
import com.activiti.repository.ActivityRepository;
import com.activiti.repository.ClubUserRepository;

@Service
public class ActivityService {

  @Autowired
  private ActivityRepository activityRepository;
  
  @Autowired
  private ClubUserRepository clubUserRepository;
  
  public static final int PAZESIZE = 10;
  
  public Activity findByNameAndChargeClub (String activityName, ClubUser chargeClub) {
    return activityRepository.findByNameAndChargeClub(activityName, chargeClub);
  }
  
  @Transactional
  public Activity findByCardNumAndActivityName(String activityName, String cardNum) {
    ClubUser clubUser = clubUserRepository.findByCardNum(cardNum);
    return findByNameAndChargeClub(activityName, clubUser);
  }
  
  public List<Activity> getAllActivity(){
    return activityRepository.findAll();
  }
  
  
  public void save(Activity activity) {
    activityRepository.save(activity);
  }
  
  public void deletes(List<Activity> activitys) {
    activityRepository.delete(activitys);
  }
  
  public Activity findById(Long id) {
    return activityRepository.findOne(id);
  }
  
  public List<Activity> getActivities(int offset, int limit) {
    return activityRepository.findActivitys(new PageRequest(offset, limit));
  }
  
  public int getPageSize() {
    return (getAllActivity().size()  +  PAZESIZE  - 1) / PAZESIZE; 
  }
}
