package com.activiti.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AccommodationItem {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @Column(name = "accommodationfare", length = 100)
  private String accommodationFare;

  @ManyToOne(fetch = FetchType.LAZY)
  private Application application;

  public String getAccommodationFare() {
    return accommodationFare;
  }

  public void setAccommodationFare(String accommodationFare) {
    this.accommodationFare = accommodationFare;
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
