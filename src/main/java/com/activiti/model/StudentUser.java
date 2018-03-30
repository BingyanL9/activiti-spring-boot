package com.activiti.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class StudentUser extends User{

  @OneToMany(mappedBy = "application_student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Application> documentApplications;
  
  @Column(name = "budget")
  private double budget;

  public List<Application> getDocumentApplications() {
    return documentApplications;
  }

  public void setDocumentApplications(List<Application> documentApplications) {
    this.documentApplications = documentApplications;
  }

  public double getBudget() {
    return budget;
  }

  public void setBudget(double budget) {
    this.budget = budget;
  }
  
  
}
