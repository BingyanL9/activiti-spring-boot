package com.activiti.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.activiti.model.Application;
import com.activiti.model.Approval;
import com.activiti.model.ClubUser;
import com.activiti.model.Payee;
import com.activiti.model.TeacherUser;
import com.activiti.repository.ApprovalRepository;

@Service
public class ApprovalService {

  @Autowired
  private ApprovalRepository approvalRepository;
  
  @Autowired
  private ApplicationService applicationService;
  
  @Autowired
  private PayeeService payeeService;
  
  private static final Logger logger = LoggerFactory.getLogger(ApplicationService.class);
  
  public void save(Approval approval) {
    approvalRepository.save(approval);
  }
  
  @Transactional
  public void saveWhenCreate(Payee payee, Approval approval, Application application) {
    
    logger.debug("Start to save applicaiton!");
    applicationService.save(application);
    application.setPayee(payee);
    application.setApproval(approval);
    applicationService.save(application);
    payee.setApplication(application);
    approval.setApplication(application);
    payeeService.save(payee);
    save(approval);
  }
  
  public List<Approval> getApprovalByTeacherUser(String teacherUserName) {
    return approvalRepository.getApprovalByTeacherUser(teacherUserName);
  }
  
  public List<Approval> getApprovalByClubUser(String clubUserName) {
    return approvalRepository.getApprovalByClubUser(clubUserName);
  }
  
  public List<Approval> getApprovals(TeacherUser teacherUser, ClubUser clubUser) {
    if (teacherUser != null ) {
      return getApprovalByTeacherUser(teacherUser.getUserName());
    }else {
      return getApprovalByClubUser(clubUser.getUserName());
    }
  }
  
}
