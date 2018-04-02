package com.activiti.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class AbroadOtherInfo {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @Column(name = "remittanceFeeRNB")
  private Double remittanceFeeRNB;
  
  @Column(name = "remittancePLRNB")
  private Double remittancePLRNB;
  
  @Column(name = "airTicketFeeRNB")
  private Double airTicketFeeRNB;
  
  @Column(name = "trafficOrOtherFeeRNB")
  private Double trafficOrOtherFeeRNB;
  
  @Column(name = "description", length = 100)
  private String description;
  
  @OneToOne(mappedBy = "abroadOtherInfo")
  private Application application;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Double getRemittanceFeeRNB() {
    return remittanceFeeRNB;
  }

  public void setRemittanceFeeRNB(Double remittanceFeeRNB) {
    this.remittanceFeeRNB = remittanceFeeRNB;
  }

  public Double getRemittancePLRNB() {
    return remittancePLRNB;
  }

  public void setRemittancePLRNB(Double remittancePLRNB) {
    this.remittancePLRNB = remittancePLRNB;
  }

  public Double getAirTicketFeeRNB() {
    return airTicketFeeRNB;
  }

  public void setAirTicketFeeRNB(Double airTicketFeeRNB) {
    this.airTicketFeeRNB = airTicketFeeRNB;
  }

  public Double getTrafficOrOtherFeeRNB() {
    return trafficOrOtherFeeRNB;
  }

  public void setTrafficOrOtherFeeRNB(Double trafficOrOtherFeeRNB) {
    this.trafficOrOtherFeeRNB = trafficOrOtherFeeRNB;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Application getApplication() {
    return application;
  }

  public void setApplication(Application application) {
    this.application = application;
  }
  
  
}
