package com.activiti.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Application {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long apno;
  
  @Column(name = "application_type", nullable = false, length = 11)
  private String application_type;
  
  @ManyToOne
  private long application_sno;
  
  @ManyToOne
  private long application_tno;
  
  @ManyToOne
  private long pno;
  
  @ManyToOne
  private long ano;
  
  @ManyToOne(optional = false)
  private long vno;

  public Long getApno() {
    return apno;
  }

  public void setApno(Long apno) {
    this.apno = apno;
  }

  public String getApplication_type() {
    return application_type;
  }

  public void setApplication_type(String application_type) {
    this.application_type = application_type;
  }

  public long getApplication_sno() {
    return application_sno;
  }

  public void setApplication_sno(long application_sno) {
    this.application_sno = application_sno;
  }

  public long getApplication_tno() {
    return application_tno;
  }

  public void setApplication_tno(long application_tno) {
    this.application_tno = application_tno;
  }

  public long getPno() {
    return pno;
  }

  public void setPno(long pno) {
    this.pno = pno;
  }

  public long getAno() {
    return ano;
  }

  public void setAno(long ano) {
    this.ano = ano;
  }

  public long getVno() {
    return vno;
  }

  public void setVno(long vno) {
    this.vno = vno;
  }
  
}
