package com.activiti.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

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
  
  @Column(name = "apno", nullable = false, length = 20)
  private long apno;

  public Feedback(long fno, Date feedback_time, byte isCorrect, String suggest, long apno) {
    super();
    this.fno = fno;
    this.feedback_time = feedback_time;
    this.isCorrect = isCorrect;
    this.suggest = suggest;
    this.apno = apno;
  }

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
