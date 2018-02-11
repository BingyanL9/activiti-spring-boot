package com.activiti.model;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity
@IdClass(Approval_id.class)
public class Approval implements Serializable{
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Id
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  private Application application;
  
  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  private TeacherUser approval_person;
  
  @Id 
  @ManyToOne(fetch = FetchType.LAZY)
  private ClubUser approcval_club;
  
  @Column(name = "approval_time", nullable = false)
  private Date approval_time;
  
  @Column(name = "approval_statu", nullable = false)
  @Enumerated(EnumType.STRING)
  private Approval_status approval_statu;
  
  @Column(name = "disapproval_reason", length = 200 )
  private String disapproval_reason;
  
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
    return approcval_club;
  }

  public void setApprocval_club(ClubUser approcval_club) {
    this.approcval_club = approcval_club;
  }

  public Date getApproval_time() {
    return approval_time;
  }

  public void setApproval_time(Date approval_time) {
    this.approval_time = approval_time;
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

  public Approval(Application application, TeacherUser approval_person, ClubUser approcval_club,
      Date approval_time, Approval_status approval_statu, String disapproval_reason) {
    super();
    this.application = application;
    this.approval_person = approval_person;
    this.approcval_club = approcval_club;
    this.approval_time = approval_time;
    this.approval_statu = approval_statu;
    this.disapproval_reason = disapproval_reason;
  }

}
