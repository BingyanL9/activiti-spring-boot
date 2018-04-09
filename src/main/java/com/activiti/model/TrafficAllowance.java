package com.activiti.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TrafficAllowance {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @Column(name = "allowancename",length = 100)
  private String allowanceName;
  
  @Column(name = "days")
  private int days;
  
  @Column(name = "standard", length=32)
  private String standard;
  
  @Column(name = "money")
  private Double money;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private Application application;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAllowanceName() {
    return allowanceName;
  }

  public void setAllowanceName(String allowanceName) {
    this.allowanceName = allowanceName;
  }

  public int getDays() {
    return days;
  }

  public void setDays(int days) {
    this.days = days;
  }

  public String getStandard() {
    return standard;
  }

  public void setStandard(String standard) {
    this.standard = standard;
  }

  public Application getApplication() {
    return application;
  }

  public void setApplication(Application application) {
    this.application = application;
  }

  public Double getMoney() {
    return money;
  }

  public void setMoney(Double money) {
    this.money = money;
  }
  
}
