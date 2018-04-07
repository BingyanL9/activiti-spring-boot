package com.activiti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.activiti.model.ActivityBudgetApply;

@Repository
public interface ActivityBudgetApplyRepository extends JpaRepository<ActivityBudgetApply, Long> {

  @Query("select a from ActivityBudgetApply a where a.chargeUserName = ?1")
  List<ActivityBudgetApply> getActivityBudgetByClubName(String clubUserName);
  
  @Query("select a from ActivityBudgetApply a where a.approvalUserName = ?1")
  List<ActivityBudgetApply> getActivityBudgetByApprovalUserName(String approvalUserName);
  
}
