package com.activiti.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity
@IdClass(Dedicated_budget_id.class)
public class Dedicated_budget implements Serializable{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Id
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  private TeacherUser teacher;
  
  @Id
  private String item_name;
  
  @Column(name = "budget")
  private double budget;

  public Dedicated_budget(TeacherUser teacher, String item_name, double budget) {
    super();
    this.teacher = teacher;
    this.item_name = item_name;
    this.budget = budget;
  }

  public TeacherUser getTeacher() {
    return teacher;
  }

  public void setTeacher(TeacherUser teacher) {
    this.teacher = teacher;
  }

  public String getItem_name() {
    return item_name;
  }

  public void setItem_name(String item_name) {
    this.item_name = item_name;
  }

  public double getBudget() {
    return budget;
  }

  public void setBudget(double budget) {
    this.budget = budget;
  }
  
}
