package com.activiti.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Project_respon {
  
  @Id
  private long pno;
  
  @Id
  @OneToMany
  private long charge;
  
  @Id
  private int level;

  public long getPno() {
    return pno;
  }

  public void setPno(long pno) {
    this.pno = pno;
  }

  public long getCharge() {
    return charge;
  }

  public void setCharge(long charge) {
    this.charge = charge;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }
  
}
