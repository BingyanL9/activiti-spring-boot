package com.activiti.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class DocumentItem {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @Column(name = "item_name", nullable = false, length = 100)
  private String item_name;
  
  @Column(name = "item_money")
  private double item_money;
  
  @Column(name = "item_description")
  private String item_description;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private Application application;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private ActivityBudgetApply activityBudgetApply;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getItem_name() {
    return item_name;
  }

  public void setItem_name(String item_name) {
    this.item_name = item_name;
  }

  public double getItem_money() {
    return item_money;
  }

  public void setItem_money(double item_money) {
    this.item_money = item_money;
  }

  public Application getApplication() {
    return application;
  }

  public void setApplication(Application application) {
    this.application = application;
  }

  public String getItem_description() {
    return item_description;
  }

  public void setItem_description(String item_description) {
    this.item_description = item_description;
  }

  public ActivityBudgetApply getActivityBudgetApply() {
    return activityBudgetApply;
  }

  public void setActivityBudgetApply(ActivityBudgetApply activityBudgetApply) {
    this.activityBudgetApply = activityBudgetApply;
  }

}
