package com.activiti.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Dedicated_budget {
  
  @Id
  @ManyToOne
  private Long tno;
  
  @Id
  private String item_name;
  
  @Column(name = "budget", nullable = false)
  private long budget;

  public Long getTno() {
    return tno;
  }

  public void setTno(Long tno) {
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
