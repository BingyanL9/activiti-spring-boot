package com.activiti.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;

@Entity
public class Application {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long apno;
  
  @Column(name = "application_type", nullable = false, length = 11)
  private String application_type;
  
  @ManyToOne(fetch = FetchType.LAZY, targetEntity = StudentInfo.class, cascade = CascadeType.REMOVE)
  private String application_sno;
  
  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Teacher.class, cascade = CascadeType.REMOVE)
  private String application_tno;
  
  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Project.class, cascade = CascadeType.REMOVE)
  private long pno;
  
  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Activity.class, cascade = CascadeType.REMOVE)
  private long ano;
  
  @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Voucher.class)
  private long vno;
  
  

  public Application(Long apno, String application_type, String application_sno,
      String application_tno, long pno, long ano, long vno) {
    super();
    this.apno = apno;
    this.application_type = application_type;
    this.application_sno = application_sno;
    this.application_tno = application_tno;
    this.pno = pno;
    this.ano = ano;
    this.vno = vno;
  }

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



  public String getApplication_sno() {
    return application_sno;
  }

  public void setApplication_sno(String application_sno) {
    this.application_sno = application_sno;
  }

  public String getApplication_tno() {
    return application_tno;
  }

  public void setApplication_tno(String application_tno) {
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
