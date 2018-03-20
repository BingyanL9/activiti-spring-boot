package com.activiti.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AbroadItem {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @Column(name = "name", length = 32)
  private String name;
  
  @Column(name = "days")
  private int days;
  
  @Column(name = "currency", length = 32)
  private String currency;
  
  @Column(name = "standard")
  private Double standard;
  
  @Column(name = "foreignexchange")
  private Double foreignExchange;
  
  @Column(name = "exchangerate")
  private Double exchangeRate;
  
  @Column(name = "RNBnum")
  private Double RNBNum;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private Application application;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getDays() {
    return days;
  }

  public void setDays(int days) {
    this.days = days;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public Double getStandard() {
    return standard;
  }

  public void setStandard(Double standard) {
    this.standard = standard;
  }

  public Double getForeignExchange() {
    return foreignExchange;
  }

  public void setForeignExchange(Double foreignExchange) {
    this.foreignExchange = foreignExchange;
  }

  public Double getExchangeRate() {
    return exchangeRate;
  }

  public void setExchangeRate(Double exchangeRate) {
    this.exchangeRate = exchangeRate;
  }

  public Double getRNBNum() {
    return RNBNum;
  }

  public void setRNBNum(Double rNBNum) {
    RNBNum = rNBNum;
  }

  public Application getApplication() {
    return application;
  }

  public void setApplication(Application application) {
    this.application = application;
  }
  
}
