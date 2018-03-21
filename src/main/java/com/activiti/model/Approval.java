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
  private ClubUser approcval_club;
  
  @Column(name = "approval_statu", nullable = false)
  @Enumerated(EnumType.STRING)
  private Approval_status approval_statu;
  
  @Column(name = "disapproval_reason", length = 200 )
  private String disapproval_reason;
  
}
