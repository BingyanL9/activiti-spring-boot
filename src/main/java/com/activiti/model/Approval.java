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
  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Application.class, cascade = CascadeType.REMOVE)
  private long apno;
  
  @Id
  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Teacher.class)
  private String approval_person;
  
  @Id 
  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Club.class)
  private String approcval_club;
  
  @Column(name = "approval_time", nullable = false)
  private Date approval_time;
  
  @Column(name = "approval_statu", nullable = false)
  @Enumerated(EnumType.STRING)
  private Approval_status approval_statu;
  
  @Column(name = "disapproval_reason", length = 200 )
  private String disapproval_reason;

  public Approval(long apno, String approval_person, String approcval_club, Date approval_time,
      Approval_status approval_statu, String disapproval_reason) {
    super();
    this.apno = apno;
    this.approval_person = approval_person;
    this.approcval_club = approcval_club;
    this.approval_time = approval_time;
    this.approval_statu = approval_statu;
    this.disapproval_reason = disapproval_reason;
  }

  public Approval_status getApproval_statu() {
    return approval_statu;
  }

  public void setApproval_statu(Approval_status approval_statu) {
    this.approval_statu = approval_statu;
  }

  public long getApno() {
    return apno;
  }

  public void setApno(long apno) {
    this.apno = apno;
  }

  public String getApproval_person() {
    return approval_person;
  }

  public void setApproval_person(String approval_person) {
    this.approval_person = approval_person;
  }

  public String getApprocval_club() {
    return approcval_club;
  }

  public void setApprocval_club(String approcval_club) {
    this.approcval_club = approcval_club;
  }

  public Date getApproval_time() {
    return approval_time;
  }

  public void setApproval_time(Date approval_time) {
    this.approval_time = approval_time;
  }

  public String getDisapproval_reason() {
    return disapproval_reason;
  }

  public void setDisapproval_reason(String disapproval_reason) {
    this.disapproval_reason = disapproval_reason;
  }
  
}
