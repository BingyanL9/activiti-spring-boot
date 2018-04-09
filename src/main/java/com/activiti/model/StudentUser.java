package com.activiti.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class StudentUser extends User{

  @Column(name = "budget")
  private double budget;

  public double getBudget() {
    return budget;
  }

  public void setBudget(double budget) {
    this.budget = budget;
  }
  
  
}
