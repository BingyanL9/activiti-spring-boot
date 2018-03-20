package com.activiti.model;

import java.util.List;

public class TravelExpenseViewObject {
  
    private List<Voucher> vouchers;
    private String department;
    private String cardnum;
    private String createtime;
    private String startTraveltime;
    private String endTraveltime;
    private String travelReason;
    private String travelPersonName;
    private String travelPersonPosition;
    private List<TravelItem> travelItems;
    private List<TrafficAllowance> trafficAllowances;
    private String travelPaymode;
    private Payee payee;
    private String manager;
    private String submitter;
    private String submitterTell;
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
    public String getTravelReason() {
      return travelReason;
    }
    public void setTravelReason(String travelReason) {
      this.travelReason = travelReason;
    }
    public String getTravelPersonName() {
      return travelPersonName;
    }
    public void setTravelPersonName(String travelPersonName) {
      this.travelPersonName = travelPersonName;
    }
    public String getTravelPersonPosition() {
      return travelPersonPosition;
    }
    public void setTravelPersonPosition(String travelPersonPosition) {
      this.travelPersonPosition = travelPersonPosition;
    }
    public List<TravelItem> getTravelItems() {
      return travelItems;
    }
    public void setTravelItems(List<TravelItem> travelItems) {
      this.travelItems = travelItems;
    }
    public List<TrafficAllowance> getTrafficAllowances() {
      return trafficAllowances;
    }
    public void setTrafficAllowances(List<TrafficAllowance> trafficAllowances) {
      this.trafficAllowances = trafficAllowances;
    }
    public String getTravelPaymode() {
      return travelPaymode;
    }
    public void setTravelPaymode(String travelPaymode) {
      this.travelPaymode = travelPaymode;
    }
    public Payee getPayee() {
      return payee;
    }
    public void setPayee(Payee payee) {
      this.payee = payee;
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
    public String getSubmitterTell() {
      return submitterTell;
    }
    public void setSubmitterTell(String submitterTell) {
      this.submitterTell = submitterTell;
    }
    
}
