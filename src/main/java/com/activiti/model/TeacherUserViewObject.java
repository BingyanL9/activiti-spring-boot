package com.activiti.model;

public class TeacherUserViewObject extends User {
  private String title;
  private double budget;
  private String leader_userName;
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public double getBudget() {
    return budget;
  }
  public void setBudget(double budget) {
    this.budget = budget;
  }
  public String getLeader_userName() {
    return leader_userName;
  }
  public void setLeader_userName(String leader_userName) {
    this.leader_userName = leader_userName;
  }

}
