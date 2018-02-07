package com.activiti.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Project {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long pno;
  
  @Column(name = "project_name", nullable = false, length = 100)
  private String project_name;
  
  @Column(name = "budget", length = 11)
  private long budget;
  
  @Column(name = "starting_date")
  private Date starting_date;
  
  @Column(name = "end_time")
  private Date end_time;

  public Project(Long pno, String project_name, long budget, Date starting_date, Date end_time) {
    super();
    this.pno = pno;
    this.project_name = project_name;
    this.budget = budget;
    this.starting_date = starting_date;
    this.end_time = end_time;
  }

  public Long getPno() {
    return pno;
  }

  public void setPno(Long pno) {
    this.pno = pno;
  }

  public String getProject_name() {
    return project_name;
  }

  public void setProject_name(String project_name) {
    this.project_name = project_name;
  }

  public long getBudget() {
    return budget;
  }

  public void setBudget(long budget) {
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
 
}
