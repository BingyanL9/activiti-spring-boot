package com.activiti.model;

import java.util.List;

public class CityTrafficExpenseViewObject {
  
    private List<Voucher> vouchers;
    private String department;
    private String cardnum;
    private String createtime;
    private List<CityTrafficItem> cityTrafficItems;
    private String paymode;
    private Payee payee;
    private Application_Type application_Type;
    private String payPerson;
    
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
    public List<CityTrafficItem> getCityTrafficItems() {
      return cityTrafficItems;
    }
    public void setCityTrafficItems(List<CityTrafficItem> cityTrafficItems) {
      this.cityTrafficItems = cityTrafficItems;
    }
    public String getTrafficPaymode() {
      return paymode;
    }
    public void setTrafficPaymode(String trafficPaymode) {
      this.paymode = trafficPaymode;
    }
    public Payee getPayee() {
      return payee;
    }
    public void setPayee(Payee payee) {
      this.payee = payee;
    }
    public String getPayPerson() {
      return payPerson;
    }
    public void setPayPerson(String payPerson) {
      this.payPerson = payPerson;
    }
    public String getPaymode() {
      return paymode;
    }
    public void setPaymode(String paymode) {
      this.paymode = paymode;
    }
    public Application_Type getApplication_Type() {
      return application_Type;
    }
    public void setApplication_Type(Application_Type application_Type) {
      this.application_Type = application_Type;
    }
    public CityTrafficExpenseViewObject() {
      super();
      // TODO Auto-generated constructor stub
    }
    
    
}
