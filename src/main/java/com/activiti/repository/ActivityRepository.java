package com.activiti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.activiti.model.Activity;
import com.activiti.model.ClubUser;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
  
  @Query("select a from Activity a where a.activityName=?1 and a.chargeClub=?2")
  public Activity findByNameAndChargeClub (String activityName, ClubUser chargeClub);
}
