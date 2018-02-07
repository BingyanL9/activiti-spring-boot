package com.activiti.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Club {
  @Id
  private String cno;
  
  @Column(name = "club_name", nullable = false, length = 100)
  private String club_name;
  
  @Column(name = "password", nullable = false, length = 16)
  private String password;
  
  @Column(name = "email", nullable = false, length = 50)
  private String email;
  
  @Column(name = "role", nullable = false)
  @Enumerated(EnumType.STRING)
  private Role role;
  
  @Column(name = "college", nullable = false, length = 50) 
  private String college;
  
  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Club.class)
  private String leader_cno;

  
  public Club(String cno, String club_name, String password, String email, Role role,
      String college, String leader_cno) {
    super();
    this.cno = cno;
    this.club_name = club_name;
    this.password = password;
    this.email = email;
    this.role = role;
    this.college = college;
    this.leader_cno = leader_cno;
  }

  public String getCno() {
    return cno;
  }

  public void setCno(String cno) {
    this.cno = cno;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public String getLeader_cno() {
    return leader_cno;
  }

  public void setLeader_cno(String leader_cno) {
    this.leader_cno = leader_cno;
  }

  public String getClub_name() {
    return club_name;
  }

  public void setClub_name(String club_name) {
    this.club_name = club_name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getCollege() {
    return college;
  }

  public void setCollege(String college) {
    this.college = college;
  }

}
