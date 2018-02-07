package com.activiti.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Entity
public class Feedback {

  @Id
  private long fno;
  
  @Column(name = "feedback_time", nullable = false)
  private Date feedback_time;
  
  @Column(name = "iscorrect", nullable = false, length = 1)
  private byte isCorrect;
  
  @Lob
  @Column(name = "suggest", length = 1024)
  private String suggest;
  
  @OneToOne
  private long apno;

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

  public long getApno() {
    return apno;
  }

  public void setApno(long apno) {
    this.apno = apno;
  }
  
}
