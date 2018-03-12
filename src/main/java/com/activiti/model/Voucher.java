package com.activiti.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Voucher {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long vno;
  
  @Column(name = "invoice_code", length = 32)
  private String invoice_code;
  
  @Column(name = "invoice_no", length = 32)
  private String invoice_no;
  
  @Column(name = "voucher_type",nullable = false , length = 100)
  private String voucher_type;
  
  @Column(name = "expense_money",nullable = false)
  private double expense_money;
  
  @Lob
  @Column(name = "enclosure",nullable = false, length = 4096)
  private List<String> enclosure;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private Application application;
  
  @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Item> items = new ArrayList<Item>();

  public Long getVno() {
    return vno;
  }

  public void setVno(Long vno) {
    this.vno = vno;
  }

  public String getInvoice_code() {
    return invoice_code;
  }

  public void setInvoice_code(String invoice_code) {
    this.invoice_code = invoice_code;
  }

  public String getInvoice_no() {
    return invoice_no;
  }

  public void setInvoice_no(String invoice_no) {
    this.invoice_no = invoice_no;
  }

  public String getVoucher_type() {
    return voucher_type;
  }

  public void setVoucher_type(String voucher_type) {
    this.voucher_type = voucher_type;
  }

  public double getExpense_money() {
    return expense_money;
  }

  public void setExpense_money(double expense_money) {
    this.expense_money = expense_money;
  }

  public List<String> getEnclosure() {
    return enclosure;
  }

  public void setEnclosure(List<String> enclosure) {
    this.enclosure = enclosure;
  }

  public Application getApplication() {
    return application;
  }

  public void setApplication(Application application) {
    this.application = application;
  }

  public List<Item> getItems() {
    return items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }
  

  public Voucher(Long vno, String invoice_code, String invoice_no, String voucher_type,
      double expense_money, List<String> enclosure, Application application, List<Item> items) {
    super();
    this.vno = vno;
    this.invoice_code = invoice_code;
    this.invoice_no = invoice_no;
    this.voucher_type = voucher_type;
    this.expense_money = expense_money;
    this.enclosure = enclosure;
    this.application = application;
    this.items = items;
  }

  public Voucher() {
    super();
    // TODO Auto-generated constructor stub
  }

}
