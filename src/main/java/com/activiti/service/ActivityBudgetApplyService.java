package com.activiti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activiti.model.ActivityBudgetApply;
import com.activiti.repository.ActivityBudgetApplyRepository;

@Service
public class ActivityBudgetApplyService {
  
  @Autowired
  private ActivityBudgetApplyRepository activityBudgetApplyRepository;
  
  public void save(ActivityBudgetApply activityBudgetApply) {
    activityBudgetApplyRepository.save(activityBudgetApply);
  }
  
  public List<ActivityBudgetApply> getActivityBudgetByClubName(String clubUserName) {
    return activityBudgetApplyRepository.getActivityBudgetByClubName(clubUserName);
  }

  public List<ActivityBudgetApply> getActivityBudgetByApprovalUserName(String approvalUserName) {
    return activityBudgetApplyRepository.getActivityBudgetByApprovalUserName(approvalUserName);
  }

}
