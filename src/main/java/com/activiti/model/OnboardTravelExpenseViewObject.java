package com.activiti.model;

import java.util.List;

public class OnboardTravelExpenseViewObject {
  
  private List<Voucher> vouchers;
  private String department;
  private String cardnum;
  private String createtime;
  private String startTraveltime;
  private String endTraveltime;
  private String onboardReason;
  private List<AbroadItem> abroadItems;
  private AbroadOtherInfo abroadOtherInfo;
  private String onboardPaymode;
  private Payee payee;
  private Double total;
  private String manager;
  private String submitter;
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
  public String getStartTraveltime() {
    return startTraveltime;
  }
  public void setStartTraveltime(String startTraveltime) {
    this.startTraveltime = startTraveltime;
  }
  public String getEndTraveltime() {
    return endTraveltime;
  }
  public void setEndTraveltime(String endTraveltime) {
    this.endTraveltime = endTraveltime;
  }
  public String getOnboardReason() {
    return onboardReason;
  }
  public void setOnboardReason(String onboardReason) {
    this.onboardReason = onboardReason;
  }
  public List<AbroadItem> getAbroadItems() {
    return abroadItems;
  }
  public void setAbroadItems(List<AbroadItem> abroadItems) {
    this.abroadItems = abroadItems;
  }
  public AbroadOtherInfo getAbroadOtherInfo() {
    return abroadOtherInfo;
  }
  public void setAbroadOtherInfo(AbroadOtherInfo abroadOtherInfo) {
    this.abroadOtherInfo = abroadOtherInfo;
  }
  public String getOnboardPaymode() {
    return onboardPaymode;
  }
  public void setOnboardPaymode(String onboardPaymode) {
    this.onboardPaymode = onboardPaymode;
  }
  public Payee getPayee() {
    return payee;
  }
  public void setPayee(Payee payee) {
    this.payee = payee;
  }
  public Double getTotal() {
    return total;
  }
  public void setTotal(Double total) {
    this.total = total;
  }
  public Application_Type getApplication_Type() {
    return application_Type;
  }
  public void setApplication_Type(Application_Type application_Type) {
    this.application_Type = application_Type;
  }
  public String getManager() {
    return manager;
  }
  public void setManager(String manager) {
    this.manager = manager;
  }
  public String getSubmitter() {
    return submitter;
  }
  public void setSubmitter(String submitter) {
    this.submitter = submitter;
  }
  
}
