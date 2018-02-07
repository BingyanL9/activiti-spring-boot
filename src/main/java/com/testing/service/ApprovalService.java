package com.testing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testing.repository.ApprovalRepository;

@Service
public class ApprovalService {

  @Autowired
  private ApprovalRepository approvalRepository;
}
