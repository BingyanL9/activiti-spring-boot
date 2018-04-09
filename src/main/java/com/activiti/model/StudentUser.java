package com.activiti.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class StudentUser extends User{

  @Column(name = "budget")
  private double budget;

  @Column(name = "cash", nullable = true)
  private double cash;
  
  public double getBudget() {
    return budget;
  }

  public void setBudget(double budget) {
    this.budget = budget;
  }

  public StudentUser(double budget) {
    super();
    this.budget = budget;
  }

  public StudentUser() {
    super();
    // TODO Auto-generated constructor stub
  }

  public double getCash() {
    return cash;
  }

  public void setCash(double cash) {
    this.cash = cash;
  }
  
}
