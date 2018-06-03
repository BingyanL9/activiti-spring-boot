package com.activiti.model;

public class FeedbackViewObject {

  private long id;
  private String feedback_time;
  private String suggest;
  private long applicationId;
  public long getId() {
    return id;
  }
  public void setId(long id) {
    this.id = id;
  }
  public String getFeedback_time() {
    return feedback_time;
  }
  public void setFeedback_time(String feedback_time) {
    this.feedback_time = feedback_time;
  }
  public String getSuggest() {
    return suggest;
  }
  public void setSuggest(String suggest) {
    this.suggest = suggest;
  }
  public long getApplicationId() {
    return applicationId;
  }
  public void setApplicationId(long applicationId) {
    this.applicationId = applicationId;
  }
  
}
