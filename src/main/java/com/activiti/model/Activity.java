package com.activiti.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Activity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long ano;
  
  @Column(name = "activityname", nullable = false, length = 100)
  private String activityName;
  
  @Column(name = "budget")
  private double budget;
  
  @Column(name = "starting_date")
  private Date starting_date;
  
  @Column(name = "end_time")
  private Date end_time;
  
  @ManyToOne(fetch = FetchType.LAZY ,cascade = CascadeType.REMOVE)
  private ClubUser chargeClub;

  public Long getAno() {
    return ano;
  }

  public void setAno(Long ano) {
    this.ano = ano;
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

  public ClubUser getCharge_club() {
    return chargeClub;
  }

  public void setCharge_club(ClubUser charge_club) {
    this.chargeClub = charge_club;
  }
  

  public Activity() {
    super();
    // TODO Auto-generated constructor stub
  }

  public Activity(Long ano, String activity_name, double budget, Date starting_date, Date end_time,
      ClubUser charge_club) {
    super();
    this.ano = ano;
    this.activityName = activity_name;
    this.budget = budget;
    this.starting_date = starting_date;
    this.end_time = end_time;
    this.chargeClub = charge_club;
  }
}
