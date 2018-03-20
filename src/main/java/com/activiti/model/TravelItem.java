package com.activiti.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TravelItem {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private Application application;
  
  @Column(name = "date", length = 32)
  private String date;
  
  @Column(name = "startendposition", length = 100)
  private String startEndPosition;
  
  @Column(name = "traffictool", length = 100)
  private String trafficTool;
  
  @Column(name = "trafficfare", length = 100)
  private String trafficFare;
  
  @Column(name = "accommodationfare", length = 100)
  private String accommodationFare;
  
  @Column(name = "otherdesription", length = 100)
  private String otherDesription;
  
  @Column(name = "otherfare", length = 100)
  private Double otherFare;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Application getApplication() {
    return application;
  }

  public void setApplication(Application application) {
    this.application = application;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getStartEndPosition() {
    return startEndPosition;
  }

  public void setStartEndPosition(String startEndPosition) {
    this.startEndPosition = startEndPosition;
  }

  public String getTrafficTool() {
    return trafficTool;
  }

  public void setTrafficTool(String trafficTool) {
    this.trafficTool = trafficTool;
  }

  public String getTrafficFare() {
    return trafficFare;
  }

  public void setTrafficFare(String trafficFare) {
    this.trafficFare = trafficFare;
  }

  public String getAccommodationFare() {
    return accommodationFare;
  }

  public void setAccommodationFare(String accommodationFare) {
    this.accommodationFare = accommodationFare;
  }

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
  
}
