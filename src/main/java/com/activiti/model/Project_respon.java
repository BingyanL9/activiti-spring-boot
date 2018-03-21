package com.activiti.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
//@IdClass(Project_respon_id.class)
public class Project_respon {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
  private Project project;
  
  @OneToOne(mappedBy = "project_respon", fetch = FetchType.LAZY)
  private TeacherUser charge;
  
  @Column(name = "level", length =32) 
  private String level;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }
  
}
