package com.activiti.model;

import java.util.Date;

public class ActivityViewObject {

  private Long id;
  private String activityName;
  private double budget;
  private Date starting_date;
  private Date end_time;
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
  public Date getStarting_date() {
    return starting_date;
  }
  public void setStarting_date(Date starting_date) {
    this.starting_date = starting_date;
  }
  public Date getEnd_time() {
    return end_time;
  }
  public void setEnd_time(Date end_time) {
    this.end_time = end_time;
  }
  public String getChargeClub() {
    return chargeClub;
  }
  public void setChargeClub(String chargeClub) {
    this.chargeClub = chargeClub;
  }
  
}
