package com.activiti.model;

import java.util.List;

public class DocumentExpenseViewObject {
  
  private List<Voucher> vouchers;
  private String department;
  private String cardnum;
  private String createtime;
  private String activityName;
  private String hospitalName;
  private String illnessName;
  private List<DocumentItem> items;
  private Payee payee;
  private String paymode;
  private Application_Type application_Type;
  
  public List<Voucher> getVouchers() {
    return vouchers;
  }
  public void setVouchers(List<Voucher> vouchers) {
    this.vouchers = vouchers;
  }
  public String getDepartment() {
    return department;
  }
  public void setDepartment(String department) {
    this.department = department;
  }
  public String getCardnum() {
    return cardnum;
  }
  public void setCardnum(String cardnum) {
    this.cardnum = cardnum;
  }
  public String getCreatetime() {
    return createtime;
  }
  public void setCreatetime(String createtime) {
    this.createtime = createtime;
  }
  public String getActivityName() {
    return activityName;
  }
  public void setActivityName(String activityName) {
    this.activityName = activityName;
  }
  public List<DocumentItem> getItems() {
    return items;
  }
  public void setItems(List<DocumentItem> items) {
    this.items = items;
  }
  public Payee getPayee() {
    return payee;
  }
  public void setPayee(Payee payee) {
    this.payee = payee;
  }
  public String getPaymode() {
    return paymode;
  }
  public void setPaymode(String paymode) {
    this.paymode = paymode;
  }
  public String getHospitalName() {
    return hospitalName;
  }
  public void setHospitalName(String hospitalName) {
    this.hospitalName = hospitalName;
  }
  public String getIllnessName() {
    return illnessName;
  }
  public void setIllnessName(String illnessName) {
    this.illnessName = illnessName;
  }
  public Application_Type getApplication_Type() {
    return application_Type;
  }
  public void setApplication_Type(Application_Type application_Type) {
    this.application_Type = application_Type;
  }
  
}
