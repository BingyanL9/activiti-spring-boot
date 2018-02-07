package com.activiti.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

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
  
  @Column(name = "item_name",nullable = false , length = 100)
  private String item_name;
  
  @Column(name = "item_money",nullable = false)
  private Long item_money;
  
  @Lob
  @Column(name = "enclosure",nullable = false, length = 4096)
  private String enclosure;

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

  public String getItem_name() {
    return item_name;
  }

  public void setItem_name(String item_name) {
    this.item_name = item_name;
  }

  public Long getItem_money() {
    return item_money;
  }

  public void setItem_money(Long item_money) {
    this.item_money = item_money;
  }

  public String getEnclosure() {
    return enclosure;
  }

  public void setEnclosure(String enclosure) {
    this.enclosure = enclosure;
  }
}
