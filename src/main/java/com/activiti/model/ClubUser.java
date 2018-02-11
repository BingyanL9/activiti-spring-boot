package com.activiti.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;


@Entity
public class ClubUser extends StudentUser{
  
  @Column(name = "college", nullable = false, length = 50) 
  private String college;
  
  @ManyToOne(fetch = FetchType.LAZY,optional = false)
  private ClubUser leaderClub;

  public String getCollege() {
    return college;
  }

  public void setCollege(String college) {
    this.college = college;
  }

  public ClubUser getLeaderClub() {
    return leaderClub;
  }

  public void setLeaderClub(ClubUser leaderClub) {
    this.leaderClub = leaderClub;
  }

  public ClubUser(String userName, String displayName, String password, String email, Role role,
      String college, ClubUser leaderClub) {
    super(userName, displayName, password, email, role);
    this.college = college;
    this.leaderClub = leaderClub;
  }

  public ClubUser() {
    super();
    // TODO Auto-generated constructor stub
  }

  public ClubUser(String userName, String displayName, String password, String email, Role role) {
    super(userName, displayName, password, email, role);
    // TODO Auto-generated constructor stub
  }
 
  
}
