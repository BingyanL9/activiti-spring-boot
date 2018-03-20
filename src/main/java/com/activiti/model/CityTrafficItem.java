package com.activiti.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CityTrafficItem {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @Column(name = "name", length = 32)
  private String name;
  
  @Column(name = "date", length = 32)
  private String date;
  
  @Column(name = "travelreason", length = 100)
  private String travelReason;
  
  @Column(name = "fare")
  private Double fare;
  
  @Column(name = "otherfare")
  private Double otherFare;
  
  @Column(name = "total")
  private Double total;
  
  @ManyToOne
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

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getTravelReason() {
    return travelReason;
  }

  public void setTravelReason(String travelReason) {
    this.travelReason = travelReason;
  }

  public Double getFare() {
    return fare;
  }

  public void setFare(Double fare) {
    this.fare = fare;
  }

  public Double getOtherFare() {
    return otherFare;
  }

  public void setOtherFare(Double otherFare) {
    this.otherFare = otherFare;
  }

  public Double getTotal() {
    return total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }
}