package com.activiti.model;

import javax.persistence.Column;

public class ProjectViewObject {
  private Long id;
  private String project_name;
  private double budget;
  private String cardNum;
  private String starting_date;
  private String end_time;
  private double cash;
  
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getProject_name() {
    return project_name;
  }
  public void setProject_name(String project_name) {
    this.project_name = project_name;
  }
  public double getBudget() {
    return budget;
  }
  public void setBudget(double budget) {
    this.budget = budget;
  }
  public String getCardNum() {
    return cardNum;
  }
  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
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
  public double getCash() {
    return cash;
  }
  public void setCash(double cash) {
    this.cash = cash;
  }
}
