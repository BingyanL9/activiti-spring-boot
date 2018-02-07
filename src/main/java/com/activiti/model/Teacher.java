package com.activiti.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Teacher {
  
  @Id
  private String tno;
  
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
  
  @Column(name = "role", nullable = false)
  @Enumerated(EnumType.STRING)
  private Role role;
  
  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Teacher.class)
  private String leader_tno;

  public Teacher(String tno, String teacher_name, String password, String email, String college,
      String title, Role role, String leader_tno) {
    super();
    this.tno = tno;
    this.teacher_name = teacher_name;
    this.password = password;
    this.email = email;
    this.college = college;
    this.title = title;
    this.role = role;
    this.leader_tno = leader_tno;
  }

  public String getTno() {
    return tno;
  }

  public void setTno(String tno) {
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

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public String getLeader_tno() {
    return leader_tno;
  }

  public void setLeader_tno(String leader_tno) {
    this.leader_tno = leader_tno;
  }

}
