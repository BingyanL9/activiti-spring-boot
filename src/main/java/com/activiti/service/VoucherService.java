package com.activiti.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activiti.repository.VoucherRepository;

@Service
public class VoucherService {
   
  @Autowired
  private VoucherRepository voucherRepository;
}
