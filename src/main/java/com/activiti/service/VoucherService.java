package com.activiti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activiti.repository.VoucherRepository;

@Service
public class VoucherService {

  @Autowired
  private VoucherRepository voucherRepository;
}
