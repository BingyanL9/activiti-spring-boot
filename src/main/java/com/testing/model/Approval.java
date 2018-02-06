package com.testing.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Approval {
  
  @Id
  @ManyToOne
  private long apno;
  
  @Id
  @OneToMany
  private long approval_person;
  
  @Id 
  @ManyToOne
  private long approcval_club;
  
  @Column(name = "approval_time", nullable = false)
  private Date approval_time;
  
  @Column(name = "approval_statu", nullable = false)
  @Enumerated(EnumType.STRING)
  private String approval_statu;
  
  @Column(name = "disapproval_reason", length = 200 )
  private String disapproval_reason;

  public long getApno() {
    return apno;
  }

  public void setApno(long apno) {
    this.apno = apno;
  }

  public long getApproval_person() {
    return approval_person;
  }

  public void setApproval_person(long approval_person) {
    this.approval_person = approval_person;
  }

  public long getApprocval_club() {
    return approcval_club;
  }

  public void setApprocval_club(long approcval_club) {
    this.approcval_club = approcval_club;
  }

  public Date getApproval_time() {
    return approval_time;
  }

  public void setApproval_time(Date approval_time) {
    this.approval_time = approval_time;
  }

  public String getApproval_statu() {
    return approval_statu;
  }

  public void setApproval_statu(String approval_statu) {
    this.approval_statu = approval_statu;
  }

  public String getDisapproval_reason() {
    return disapproval_reason;
  }

  public void setDisapproval_reason(String disapproval_reason) {
    this.disapproval_reason = disapproval_reason;
  }
  
}
