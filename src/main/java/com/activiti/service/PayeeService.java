package com.activiti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activiti.model.Payee;
import com.activiti.repository.PayeeRepository;

@Service
public class PayeeService {

  @Autowired
  private PayeeRepository payeeRepository;
  
  public void save(Payee payee) {
    payeeRepository.save(payee);
  }
}
