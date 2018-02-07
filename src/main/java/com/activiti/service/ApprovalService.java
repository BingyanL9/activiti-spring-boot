package com.activiti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activiti.repository.ApprovalRepository;

@Service
public class ApprovalService {

  @Autowired
  private ApprovalRepository approvalRepository;
}
