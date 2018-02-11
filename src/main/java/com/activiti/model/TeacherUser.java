package com.activiti.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class TeacherUser extends StudentUser{
  
  @Column(name = "college", nullable = false, length = 50) 
  private String college;
  
  @Column(name = "title", length =32) 
  private String title;
  
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  private TeacherUser leader;

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

  public TeacherUser getLeader() {
    return leader;
  }

  public void setLeader(TeacherUser leader) {
    this.leader = leader;
  }

  public TeacherUser(String userName, String displayName, String password, String email, Role role,
      String college, String title, TeacherUser leader) {
    super(userName, displayName, password, email, role);
    this.college = college;
    this.title = title;
    this.leader = leader;
  }

  public TeacherUser() {
    super();
    // TODO Auto-generated constructor stub
  }

  public TeacherUser(String userName, String displayName, String password, String email,
      Role role) {
    super(userName, displayName, password, email, role);
    // TODO Auto-generated constructor stub
  }

}
