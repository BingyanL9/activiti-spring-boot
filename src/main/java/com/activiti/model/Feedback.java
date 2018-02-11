package com.activiti.model;

import java.util.Date;

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
  private long fno;
  
  @Column(name = "feedback_time", nullable = false)
  private Date feedback_time;
  
  @Column(name = "iscorrect", nullable = false, length = 1)
  private byte isCorrect;
  
  @Lob
  @Column(name = "suggest", length = 1024)
  private String suggest;
  
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  private Application application;

  public long getFno() {
    return fno;
  }

  public void setFno(long fno) {
    this.fno = fno;
  }

  public Date getFeedback_time() {
    return feedback_time;
  }

  public void setFeedback_time(Date feedback_time) {
    this.feedback_time = feedback_time;
  }

  public byte getIsCorrect() {
    return isCorrect;
  }

  public void setIsCorrect(byte isCorrect) {
    this.isCorrect = isCorrect;
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

  public Feedback(long fno, Date feedback_time, byte isCorrect, String suggest,
      Application application) {
    super();
    this.fno = fno;
    this.feedback_time = feedback_time;
    this.isCorrect = isCorrect;
    this.suggest = suggest;
    this.application = application;
  }

}
