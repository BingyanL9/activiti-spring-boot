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
  @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
  private Project project;
  
  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  private TeacherUser charge;
  
  @Id
  private int level;

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public TeacherUser getCharge() {
    return charge;
  }

  public void setCharge(TeacherUser charge) {
    this.charge = charge;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public Project_respon(Project project, TeacherUser charge, int level) {
    super();
    this.project = project;
    this.charge = charge;
    this.level = level;
  }

  
}
