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
public class TeacherUser extends User{
  
  
  @Column(name = "title", length =32) 
  private String title;
  
  @Column(name = "budget")
  private double budget;
  
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  private TeacherUser leader;
  
  @OneToMany(mappedBy = "leader", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<TeacherUser> subordinates;
  
  @OneToMany(mappedBy = "application_teacher", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Application> documentApplications;
  
  @OneToMany(mappedBy = "approval_person", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Approval> approvals;
  
  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  private Project_respon project_respon;

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

  public List<Application> getDocumentApplications() {
    return documentApplications;
  }

  public void setDocumentApplications(List<Application> documentApplications) {
    this.documentApplications = documentApplications;
  }

  public double getBudget() {
    return budget;
  }

  public void setBudget(double budget) {
    this.budget = budget;
  }

  public List<Approval> getApprovals() {
    return approvals;
  }

  public void setApprovals(List<Approval> approvals) {
    this.approvals = approvals;
  }

  public Project_respon getProject_respon() {
    return project_respon;
  }

  public void setProject_respon(Project_respon project_respon) {
    this.project_respon = project_respon;
  }

  public TeacherUser() {
    super();
    // TODO Auto-generated constructor stub
  }

}
