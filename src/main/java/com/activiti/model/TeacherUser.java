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
  
  @Column(name = "college", nullable = false, length = 50) 
  private String college;
  
  @Column(name = "title", length =32) 
  private String title;
  
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
  
  public String getCollege() {
    return college;
  }

  public void setCollege(String college) {
    this.college = college;
  }

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

}
