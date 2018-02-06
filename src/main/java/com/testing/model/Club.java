package com.testing.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Club {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long cno;
  
  @Column(name = "club_name", nullable = false, length = 100)
  private String club_name;
  
  @Column(name = "password", nullable = false, length = 16)
  private String password;
  
  @Column(name = "college", nullable = false, length = 50) 
  private String college;
  
  @ManyToOne
  private long leader_cno;

  public Long getCno() {
    return cno;
  }

  public void setCno(Long cno) {
    this.cno = cno;
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

  public long getLeader_cno() {
    return leader_cno;
  }

  public void setLeader_cno(long leader_cno) {
    this.leader_cno = leader_cno;
  }

}
