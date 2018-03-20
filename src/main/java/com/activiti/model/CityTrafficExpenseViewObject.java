package com.activiti.model;

import java.util.List;

public class CityTrafficExpenseViewObject {
  
    private List<Voucher> vouchers;
    private List<CityTrafficItem> cityTrafficItems;
    private String trafficPaymode;
    private Payee payee;
    private String expenseType;
    private String payPerson;
    public List<Voucher> getVouchers() {
      return vouchers;
    }
    public void setVouchers(List<Voucher> vouchers) {
      this.vouchers = vouchers;
    }
    public List<CityTrafficItem> getCityTrafficItems() {
      return cityTrafficItems;
    }
    public void setCityTrafficItems(List<CityTrafficItem> cityTrafficItems) {
      this.cityTrafficItems = cityTrafficItems;
    }
    public String getTrafficPaymode() {
      return trafficPaymode;
    }
    public void setTrafficPaymode(String trafficPaymode) {
      this.trafficPaymode = trafficPaymode;
    }
    public Payee getPayee() {
      return payee;
    }
    public void setPayee(Payee payee) {
      this.payee = payee;
    }
    public String getExpenseType() {
      return expenseType;
    }
    public void setExpenseType(String expenseType) {
      this.expenseType = expenseType;
    }
    public String getPayPerson() {
      return payPerson;
    }
    public void setPayPerson(String payPerson) {
      this.payPerson = payPerson;
    }
    
}
