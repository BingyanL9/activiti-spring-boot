package com.activiti.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activiti.model.Payee;
import com.activiti.repository.PayeeRepository;

@Service
public class PayeeService implements Serializable {

  private static final long serialVersionUID = 1L;
  
  @Autowired
  private PayeeRepository payeeRepository;
  
  public void save(Payee payee) {
    payeeRepository.save(payee);
  }
}
