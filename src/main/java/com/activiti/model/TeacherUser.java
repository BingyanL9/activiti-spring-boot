package com.activiti.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class TeacherUser extends User {


  @Column(name = "title", length = 32)
  private String title;

  @Column(name = "budget")
  private double budget;
  
  @Column(name = "cash", nullable = true)
  private double cash;

  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  private TeacherUser leader;

  @OneToMany(mappedBy = "leader", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<TeacherUser> subordinates;

  @OneToMany(mappedBy = "charge", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Project_respon> project_respons;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public TeacherUser getLeader() {
    return leader;
  }

  public void setLeader(TeacherUser leader) {
    this.leader = leader;
  }

  public List<TeacherUser> getSubordinates() {
    return subordinates;
  }

  public void setSubordinates(List<TeacherUser> subordinates) {
    this.subordinates = subordinates;
  }

  public double getBudget() {
    return budget;
  }

  public void setBudget(double budget) {
    this.budget = budget;
  }
  
  public double getCash() {
    return cash;
  }

  public void setCash(double cash) {
    this.cash = cash;
  }

  
  public List<Project_respon> getProject_respons() {
    return project_respons;
  }

  public void setProject_respons(List<Project_respon> project_respons) {
    this.project_respons = project_respons;
  }

  public TeacherUser() {
    super();
    // TODO Auto-generated constructor stub
  }

}
