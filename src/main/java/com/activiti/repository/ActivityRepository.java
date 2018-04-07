package com.activiti.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.activiti.model.Activity;
import com.activiti.model.ClubUser;
import com.activiti.model.TeacherUser;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
  
  @Query("select a from Activity a where a.activityName=?1 and a.chargeClub=?2")
  public Activity findByNameAndChargeClub (String activityName, ClubUser chargeClub);
  
  @Query("select a from Activity a order by a.chargeClub.userName")
  List<Activity> findActivitys(Pageable pageable);
}
