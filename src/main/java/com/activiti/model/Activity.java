package com.activiti.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Activity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @Column(name = "activityname", nullable = false, length = 100)
  private String activityName;
  
  @Column(name = "budget")
  private double budget;
  
  @Column(name = "starting_date")
  private String starting_date;
  
  @Column(name = "end_time")
  private String end_time;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private ClubUser chargeClub;
  
  @OneToMany(mappedBy = "activity", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  private List<Application> applications;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getActivity_name() {
    return activityName;
  }

  public void setActivity_name(String activity_name) {
    this.activityName = activity_name;
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

  public ClubUser getCharge_club() {
    return chargeClub;
  }

  public void setCharge_club(ClubUser charge_club) {
    this.chargeClub = charge_club;
  }

  public String getActivityName() {
    return activityName;
  }

  public void setActivityName(String activityName) {
    this.activityName = activityName;
  }

  public ClubUser getChargeClub() {
    return chargeClub;
  }

  public void setChargeClub(ClubUser chargeClub) {
    this.chargeClub = chargeClub;
  }

  public List<Application> getApplications() {
    return applications;
  }

  public void setApplications(List<Application> applications) {
    this.applications = applications;
  }

}
