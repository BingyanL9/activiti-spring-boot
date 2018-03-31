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
import javax.persistence.OneToMany;

@Entity
public class Project {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @Column(name = "project_name", nullable = false, length = 100)
  private String project_name;
  
  @Column(name = "budget")
  private double budget;
  
  @Column(name = "cardNum")
  private String cardNum;
  
  @Column(name = "starting_date")
  private Date starting_date;
  
  @Column(name = "end_time")
  private Date end_time;
  
  @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  private List<Project_respon> project_respons;
  
  @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  private List<Application> documentApplications;

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

  public String getCardnum() {
    return cardNum;
  }

  public void setCardnum(String cardnum) {
    this.cardNum = cardnum;
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

  public List<Project_respon> getProject_respons() {
    return project_respons;
  }

  public void setProject_respons(List<Project_respon> project_respons) {
    this.project_respons = project_respons;
  }

  public List<Application> getDocumentApplications() {
    return documentApplications;
  }

  public void setDocumentApplications(List<Application> documentApplications) {
    this.documentApplications = documentApplications;
  }
  
}
