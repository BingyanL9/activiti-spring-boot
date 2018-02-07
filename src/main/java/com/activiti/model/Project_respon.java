package com.activiti.model;


import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity
@IdClass(Project_respon_id.class)
public class Project_respon implements Serializable{
  

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Id
  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Project.class, cascade = CascadeType.REMOVE)
  private long pno;
  
  @Id
  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Teacher.class)
  private String charge;
  
  @Id
  private int level;

  
  public Project_respon(long pno, String charge, int level) {
    super();
    this.pno = pno;
    this.charge = charge;
    this.level = level;
  }

  public long getPno() {
    return pno;
  }

  public void setPno(long pno) {
    this.pno = pno;
  }

  public String getCharge() {
    return charge;
  }

  public void setCharge(String charge) {
    this.charge = charge;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }
  
}
