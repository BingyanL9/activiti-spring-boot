package com.activiti.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StudentInfo {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long sno;

  @Column(name = "student_name", nullable = false, length = 100)
  private String student_name;
  
  @Column(name = "password", nullable = false, length = 16)
  private String password;
  
  @Column(name = "email", nullable = false, length = 50)
  private String email;

  public StudentInfo(Long sno, String student_name, String password, String email) {
    super();
    this.sno = sno;
    this.student_name = student_name;
    this.password = password;
    this.email = email;
  }

  public Long getSno() {
    return sno;
  }

  public void setSno(Long sno) {
    this.sno = sno;
  }

  public String getStudent_name() {
    return student_name;
  }

  public void setStudent_name(String student_name) {
    this.student_name = student_name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  
}
