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
  private double trafficFare;

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

  public double getTrafficFare() {
    return trafficFare;
  }

  public void setTrafficFare(double trafficFare) {
    this.trafficFare = trafficFare;
  }
}
