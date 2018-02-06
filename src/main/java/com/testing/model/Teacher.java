package com.testing.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Teacher {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long tno;
  
  @Column(name = "teacher_name", nullable = false, length = 100)
  private String teacher_name;
  
  @Column(name = "password", nullable = false, length = 16)
  private String password;
  
  @Column(name = "email", nullable = false, length = 50)
  private String email;
  
  @Column(name = "college", nullable = false, length = 50) 
  private String college;
  
  @Column(name = "title", length =32) 
  private String title;
  
  @Column(name = "role", nullable = true, length = 10)
  @Enumerated(EnumType.STRING)
  private String role;
  
  @ManyToOne
  private long leader_tno;

  public Long getTno() {
    return tno;
  }

  public void setTno(Long tno) {
    this.tno = tno;
  }

  public String getTeacher_name() {
    return teacher_name;
  }

  public void setTeacher_name(String teacher_name) {
    this.teacher_name = teacher_name;
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

  public String getCollege() {
    return college;
  }

  public void setCollege(String college) {
    this.college = college;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public long getLeader_tno() {
    return leader_tno;
  }

  public void setLeader_tno(long leader_tno) {
    this.leader_tno = leader_tno;
  }
  
   
}
