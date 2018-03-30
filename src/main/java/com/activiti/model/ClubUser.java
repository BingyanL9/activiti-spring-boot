package com.activiti.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class ClubUser extends User{
  
  @Column(name = "college", nullable = false, length = 50) 
  private String college;
  
  @ManyToOne(fetch = FetchType.LAZY,optional = false)
  private ClubUser leaderClub;
  
  @OneToMany(mappedBy = "chargeClub", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Activity> activities;
  
  @OneToMany(mappedBy = "approval_club", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Approval> approvals;
  
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

  public ClubUser() {
    super();
    // TODO Auto-generated constructor stub
  }

  public ClubUser(String college, ClubUser leaderClub) {
    super();
    this.college = college;
    this.leaderClub = leaderClub;
  }

  public ClubUser(String userName, String displayName, String password, String email,
      String cardnum, Role role) {
    super(userName, displayName, password, email, cardnum, role);
    // TODO Auto-generated constructor stub
  }

}
