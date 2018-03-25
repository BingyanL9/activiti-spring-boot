package com.activiti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.activiti.model.Application;
import com.activiti.model.Approval;
import com.activiti.repository.ApprovalRepository;

@Service
public class ApprovalService {

  @Autowired
  private ApprovalRepository approvalRepository;
  
  @Autowired
  private ApplicationService applicationService;
  
  public void save(Approval approval) {
    approvalRepository.save(approval);
  }
  
  @Transactional
  public void saveWhenCreate(Approval approval, Application application) {
    applicationService.save(application);
    application.setApproval(approval);
    applicationService.save(application);
    approval.setApplication(application);
    save(approval);
  }
}
