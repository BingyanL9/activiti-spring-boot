package com.activiti.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity
@IdClass(Dedicated_budget_id.class)
public class Dedicated_budget implements Serializable{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Id
  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Teacher.class, cascade = CascadeType.REMOVE)
  private String tno;
  
  @Id
  private String item_name;
  
  @Column(name = "budget", nullable = false)
  private long budget;

  public Dedicated_budget(String tno, String item_name, long budget) {
    super();
    this.tno = tno;
    this.item_name = item_name;
    this.budget = budget;
  }

  public String getTno() {
    return tno;
  }

  public void setTno(String tno) {
    this.tno = tno;
  }

  public String getItem_name() {
    return item_name;
  }

  public void setItem_name(String item_name) {
    this.item_name = item_name;
  }

  public long getBudget() {
    return budget;
  }

  public void setBudget(long budget) {
    this.budget = budget;
  }
  
}
