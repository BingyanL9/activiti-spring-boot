package com.activiti.model;

import java.util.List;

public class DocumentExpenseViewObject {
  
  private List<Voucher> vouchers;
  private String department;
  private String cardnum;
  private String createtime;
  private String activityName;
  private List<DocumentItem> items;
  private Payee payee;
  private String paymode;
  private String expense_type;
  
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
  public String getExpense_type() {
    return expense_type;
  }
  public void setExpense_type(String expense_type) {
    this.expense_type = expense_type;
  }
  
}
