package com.activiti.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class User{

  @Id
  private String userName;
  
  @Column(name = "displayName", nullable = false, length = 100)
  private String displayName;
  
  @Column(name = "password", nullable = false, length = 16)
  private String password;
  
  @Column(name = "email", nullable = false, length = 50)
  private String email;
  
  @Column(name = "cardNum", nullable = false, length = 32)
  private String cardNum;
  
  @Column(name = "role", nullable = false)
  @Enumerated(EnumType.STRING)
  private Role role;
  
  @Column(name = "college", nullable = false, length = 50) 
  private String college;
  
  @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Application> applications;
  
  @OneToMany(mappedBy = "approval_person", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Approval> approvals;
      
  public User() {
    super();
    // TODO Auto-generated constructor stub
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
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

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }

  public String getCollege() {
    return college;
  }

  public void setCollege(String college) {
    this.college = college;
  }

  public List<Application> getApplications() {
    return applications;
  }

  public void setApplications(List<Application> applications) {
    this.applications = applications;
  }

  public List<Approval> getApprovals() {
    return approvals;
  }

  public void setApprovals(List<Approval> approvals) {
    this.approvals = approvals;
  }

}
