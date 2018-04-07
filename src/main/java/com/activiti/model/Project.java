package com.activiti.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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

  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }

  @Column(name = "starting_date")
  private String starting_date;

  @Column(name = "end_time")
  private String end_time;

  @OneToOne(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Project_respon project_respon;

  @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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

  public Project_respon getProject_respon() {
    return project_respon;
  }

  public void setProject_respon(Project_respon project_respon) {
    this.project_respon = project_respon;
  }

  public List<Application> getDocumentApplications() {
    return documentApplications;
  }

  public void setDocumentApplications(List<Application> documentApplications) {
    this.documentApplications = documentApplications;
  }


}
