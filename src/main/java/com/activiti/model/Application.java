package com.activiti.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
  @Enumerated(EnumType.STRING)
  private Application_Type application_type;
  
  @Column(name = " createtime", length = 32)
  private String createtime;
  
  @Column(name = " payee_name", length = 32)
  private String payee_name;
  
  @Column(name = " payee_account", length = 32)
  private String payee_account;
  
  @Column(name = " payee_account_opening_bank", length = 32)
  private String payee_account_opening_bank;
  
  @Column(name = "paymode", length = 32)
  private String paymode;
  
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

  public Application_Type getApplication_type() {
    return application_type;
  }

  public void setApplication_type(Application_Type application_type) {
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

  public String getCreatetime() {
    return createtime;
  }

  public void setCreatetime(String createtime) {
    this.createtime = createtime;
  }

  public String getPayee_name() {
    return payee_name;
  }

  public void setPayee_name(String payee_name) {
    this.payee_name = payee_name;
  }

  public String getPayee_account() {
    return payee_account;
  }

  public void setPayee_account(String payee_account) {
    this.payee_account = payee_account;
  }

  public String getPayee_account_opening_bank() {
    return payee_account_opening_bank;
  }

  public void setPayee_account_opening_bank(String payee_account_opening_bank) {
    this.payee_account_opening_bank = payee_account_opening_bank;
  }

  public String getPaymode() {
    return paymode;
  }

  public void setPaymode(String paymode) {
    this.paymode = paymode;
  }

  public Application(Long apno, Application_Type application_type, String createtime,
      String payee_name, String payee_account, String payee_account_opening_bank, String paymode,
      StudentUser application_student, TeacherUser application_teacher, Project project,
      Activity activity) {
    super();
    this.apno = apno;
    this.application_type = application_type;
    this.createtime = createtime;
    this.payee_name = payee_name;
    this.payee_account = payee_account;
    this.payee_account_opening_bank = payee_account_opening_bank;
    this.paymode = paymode;
    this.application_student = application_student;
    this.application_teacher = application_teacher;
    this.project = project;
    this.activity = activity;
  }

  public Application() {
    super();
    // TODO Auto-generated constructor stub
  }
  
}
