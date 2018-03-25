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
  
  @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Application> applications;
      
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

  public String getCardnum() {
    return cardNum;
  }

  public void setCardnum(String cardnum) {
    this.cardNum = cardnum;
  }

  public User(String userName, String displayName, String password, String email,
      String cardnum, Role role) {
    super();
    this.userName = userName;
    this.displayName = displayName;
    this.password = password;
    this.email = email;
    this.cardNum = cardnum;
    this.role = role;
  }

  
}
