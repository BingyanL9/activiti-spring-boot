package com.activiti.model;

import java.util.Date;

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
  
  @Column(name = "activity_name", nullable = false, length = 100)
  private String activity_name;
  
  @Column(name = "budget")
  private double budget;
  
  @Column(name = "starting_date")
  private Date starting_date;
  
  @Column(name = "end_time")
  private Date end_time;
  
  @ManyToOne(fetch = FetchType.LAZY,optional = false,targetEntity = Club.class)
  private String charge_cno;

  public Long getAno() {
    return ano;
  }

  public void setAno(Long ano) {
    this.ano = ano;
  }

  public String getActivity_name() {
    return activity_name;
  }

  public void setActivity_name(String activity_name) {
    this.activity_name = activity_name;
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

  public String getCharge_cno() {
    return charge_cno;
  }

  public void setCharge_cno(String charge_cno) {
    this.charge_cno = charge_cno;
  }

  public Activity(Long ano, String activity_name, double budget, Date starting_date, Date end_time,
      String charge_cno) {
    super();
    this.ano = ano;
    this.activity_name = activity_name;
    this.budget = budget;
    this.starting_date = starting_date;
    this.end_time = end_time;
    this.charge_cno = charge_cno;
  }

}
