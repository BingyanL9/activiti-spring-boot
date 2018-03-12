package com.activiti.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Item {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @Column(name = "item_name", nullable = false, length = 100)
  private String item_name;
  
  @Column(name = "item_money")
  private double item_money;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private Voucher voucher;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getItem_name() {
    return item_name;
  }

  public void setItem_name(String item_name) {
    this.item_name = item_name;
  }

  public double getItem_money() {
    return item_money;
  }

  public void setItem_money(double item_money) {
    this.item_money = item_money;
  }

  public Voucher getVoucher() {
    return voucher;
  }

  public void setVoucher(Voucher voucher) {
    this.voucher = voucher;
  }

  public Item(Long id, String item_name, double item_money, Voucher voucher) {
    super();
    this.id = id;
    this.item_name = item_name;
    this.item_money = item_money;
    this.voucher = voucher;
  }

  public Item() {
    super();
    // TODO Auto-generated constructor stub
  }

}
