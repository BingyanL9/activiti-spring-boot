package com.activiti.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Feedback {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "feedback_time", nullable = false)
  private String feedback_time;
  @Lob
  @Column(name = "suggest", length = 1024)
  private String suggest;

  @ManyToOne(fetch = FetchType.LAZY)
  private Application application;

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

  public Application getApplication() {
    return application;
  }

  public void setApplication(Application application) {
    this.application = application;
  }

  public Feedback() {
    super();
  }

}
