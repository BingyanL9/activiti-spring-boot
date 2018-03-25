package com.activiti.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Voucher {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @Lob
  @Column(name = "enclosure",nullable = false, length = 4096)
  private byte[] enclosure;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private Application application;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public byte[] getEnclosure() {
    return enclosure;
  }

  public void setEnclosure(byte[] enclosure) {
    this.enclosure = enclosure;
  }

  public Application getApplication() {
    return application;
  }

  public void setApplication(Application application) {
    this.application = application;
  }

  public Voucher(Long id, byte[] enclosure, Application application) {
    super();
    this.id = id;
    this.enclosure = enclosure;
    this.application = application;
  }

  public Voucher() {
    super();
    // TODO Auto-generated constructor stub
  }

}
