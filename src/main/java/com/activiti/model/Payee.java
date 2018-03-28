package com.activiti.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Payee {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @Column(name = " payee_name", length = 32)
  private String payee_name;
  
  @Column(name = " payee_account", length = 32)
  private String payee_account;
  
  @Column(name = " payee_account_opening_bank", length = 32)
  private String payee_account_opening_bank;
  
  @OneToOne(mappedBy = "payee")
  private Application application;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPayee_name() {
    return payee_name;
  }

  public void setPayee_name(String payee_name) {
    this.payee_name = payee_name;
  }

  public String getPayee_account() {
    return payee_account;
  }

  public void setPayee_account(String payee_account) {
    this.payee_account = payee_account;
  }

  public String getPayee_account_opening_bank() {
    return payee_account_opening_bank;
  }

  public void setPayee_account_opening_bank(String payee_account_opening_bank) {
    this.payee_account_opening_bank = payee_account_opening_bank;
  }

  public Application getApplication() {
    return application;
  }

  public void setApplication(Application application) {
    this.application = application;
  }

  public Payee(Long id, String payee_name, String payee_account, String payee_account_opening_bank,
      Application application) {
    super();
    this.id = id;
    this.payee_name = payee_name;
    this.payee_account = payee_account;
    this.payee_account_opening_bank = payee_account_opening_bank;
    this.application = application;
  }

  public Payee() {
    super();
    // TODO Auto-generated constructor stub
  }
  
  
}
