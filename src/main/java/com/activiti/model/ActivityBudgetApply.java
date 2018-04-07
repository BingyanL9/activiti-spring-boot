package com.activiti.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ActivityBudgetApply {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "activityName", nullable=false, length=128)
  private String activityName;
  
  @Column(name = "budget", nullable=false)
  private double budget;
  
  @Column(name = "starting_date", nullable=false, length=16)
  private String starting_date;
  
  @Column(name = "end_time", nullable=false, length=16)
  private String end_time;
  
  @Column(name = "chargeUserName", nullable=false, length=16)
  private String chargeUserName;
  
  @Column(name = "approvalUserName", nullable=false, length=16)
  private String approvalUserName;
  
  @OneToMany(mappedBy = "activityBudgetApply", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<DocumentItem> items;
  
  @Column(name = "disApprovalReason", length=128)
  private String disApprovalReason;
  
  @Column(name = "status", nullable = false)
  @Enumerated(EnumType.STRING)
  private Approval_status status;

  public String getActivityName() {
    return activityName;
  }

  public void setActivityName(String activityName) {
    this.activityName = activityName;
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

  public String getChargeUserName() {
    return chargeUserName;
  }

  public void setChargeUserName(String chargeUserName) {
    this.chargeUserName = chargeUserName;
  }

  public String getApprovalUserName() {
    return approvalUserName;
  }

  public void setApprovalUserName(String approvalUserName) {
    this.approvalUserName = approvalUserName;
  }

  public List<DocumentItem> getItems() {
    return items;
  }

  public void setItems(List<DocumentItem> items) {
    this.items = items;
  }

  public String getDisApprovalReason() {
    return disApprovalReason;
  }

  public void setDisApprovalReason(String disApprovalReason) {
    this.disApprovalReason = disApprovalReason;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Approval_status getStatus() {
    return status;
  }

  public void setStatus(Approval_status status) {
    this.status = status;
  }

}
