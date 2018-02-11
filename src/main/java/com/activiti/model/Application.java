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
  
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  private StudentUser application_student;
  
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  private TeacherUser application_teacher;
  
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  private Project project;
  
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  private Activity activity;

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

  public StudentUser getApplication_student() {
    return application_student;
  }

  public void setApplication_student(StudentUser application_student) {
    this.application_student = application_student;
  }

  public TeacherUser getApplication_teacher() {
    return application_teacher;
  }

  public void setApplication_teacher(TeacherUser application_teacher) {
    this.application_teacher = application_teacher;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public Activity getActivity() {
    return activity;
  }

  public void setActivity(Activity activity) {
    this.activity = activity;
  }

  public Application(Long apno, String application_type, StudentUser application_student,
      TeacherUser application_teacher, Project project, Activity activity) {
    super();
    this.apno = apno;
    this.application_type = application_type;
    this.application_student = application_student;
    this.application_teacher = application_teacher;
    this.project = project;
    this.activity = activity;
  }
  
  
}
