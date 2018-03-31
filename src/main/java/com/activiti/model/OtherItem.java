package com.activiti.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OtherItem {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @Column(name = "otherdesription", length = 100)
  private String otherDesription;
  
  @Column(name = "otherfare", length = 100)
  private Double otherFare;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private Application application;

  public String getOtherDesription() {
    return otherDesription;
  }

  public void setOtherDesription(String otherDesription) {
    this.otherDesription = otherDesription;
  }

  public Double getOtherFare() {
    return otherFare;
  }

  public void setOtherFare(Double otherFare) {
    this.otherFare = otherFare;
  }

  public Application getApplication() {
    return application;
  }

  public void setApplication(Application application) {
    this.application = application;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
  
}
