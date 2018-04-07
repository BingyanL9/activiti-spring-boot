package com.activiti.model;

import java.util.Date;

public class ActivityViewObject {

  private Long id;
  private String activityName;
  private double budget;
  private String starting_date;
  private String end_time;
  private String chargeClub;
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getActivityName() {
    return activityName;
  }
  public void setActivityName(String activityName) {
    this.activityName = activityName;
  }
  public double getBudget() {
    return budget;
  }
  public void setBudget(double budget) {
    this.budget = budget;
  }

  public String getStarting_date() {
    return starting_date;
  }
  public void setStarting_date(String starting_date) {
    this.starting_date = starting_date;
  }
  public String getEnd_time() {
    return end_time;
  }
  public void setEnd_time(String end_time) {
    this.end_time = end_time;
  }
  public String getChargeClub() {
    return chargeClub;
  }
  public void setChargeClub(String chargeClub) {
    this.chargeClub = chargeClub;
  }
  
}
