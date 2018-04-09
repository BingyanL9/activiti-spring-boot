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
  
  @ManyToOne(fetch = FetchType.LAZY,optional = true)
  private ClubUser leaderClub;
  
  @OneToMany(mappedBy = "chargeClub", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Activity> activities;
  
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

}
