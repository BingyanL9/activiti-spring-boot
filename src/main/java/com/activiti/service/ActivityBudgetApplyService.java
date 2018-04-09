package com.activiti.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activiti.model.ActivityBudgetApply;
import com.activiti.repository.ActivityBudgetApplyRepository;

@Service
public class ActivityBudgetApplyService implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  @Autowired
  private ActivityBudgetApplyRepository activityBudgetApplyRepository;
  
  public void save(ActivityBudgetApply activityBudgetApply) {
    activityBudgetApplyRepository.save(activityBudgetApply);
  }
  
  public void delete(ActivityBudgetApply activityBudgetApply) {
    activityBudgetApplyRepository.delete(activityBudgetApply);
  }
  
  public ActivityBudgetApply getActivityBudgetApply(Long id) {
    return activityBudgetApplyRepository.findOne(id);
  }
  
  public List<ActivityBudgetApply> getActivityBudgetByClubName(String clubUserName) {
    return activityBudgetApplyRepository.getActivityBudgetByClubName(clubUserName);
  }

  public List<ActivityBudgetApply> getActivityBudgetByApprovalUserName(String approvalUserName) {
    return activityBudgetApplyRepository.getActivityBudgetByApprovalUserName(approvalUserName);
  }
  
}
