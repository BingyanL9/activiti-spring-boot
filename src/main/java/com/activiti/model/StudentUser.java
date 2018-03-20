package com.activiti.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class StudentUser extends User{

  @OneToMany(mappedBy = "application_student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Application> documentApplications;
}
