package com.activiti.model;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
//@IdClass(Approval_id.class)
public class Approval {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @OneToOne(mappedBy = "approval")
  private Application application;

  @ManyToOne(fetch = FetchType.LAZY)
  private TeacherUser approval_person;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private ClubUser approval_club;
  
  @Column(name = "approval_statu", nullable = false)
  @Enumerated(EnumType.STRING)
  private Approval_status approval_statu;
  
  @Column(name = "disapproval_reason", length = 200 )
  private String disapproval_reason;
  
  @Column(name = "processInstanceId", nullable = false)
  private String processInstanceId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Application getApplication() {
    return application;
  }

  public void setApplication(Application application) {
    this.application = application;
  }

  public TeacherUser getApproval_person() {
    return approval_person;
  }

  public void setApproval_person(TeacherUser approval_person) {
    this.approval_person = approval_person;
  }

  public ClubUser getApprocval_club() {
    return approval_club;
  }

  public void setApprocval_club(ClubUser approcval_club) {
    this.approval_club = approcval_club;
  }

  public Approval_status getApproval_statu() {
    return approval_statu;
  }

  public void setApproval_statu(Approval_status approval_statu) {
    this.approval_statu = approval_statu;
  }

  public String getDisapproval_reason() {
    return disapproval_reason;
  }

  public void setDisapproval_reason(String disapproval_reason) {
    this.disapproval_reason = disapproval_reason;
  }

  public String getProcessInstanceId() {
    return processInstanceId;
  }

  public void setProcessInstanceId(String processInstanceId) {
    this.processInstanceId = processInstanceId;
  }
  
}
