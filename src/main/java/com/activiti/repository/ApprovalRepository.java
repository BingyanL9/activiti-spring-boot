package com.activiti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.activiti.model.Approval;
@Repository
public interface ApprovalRepository extends JpaRepository<Approval, Long> {
  
  @Query("select a from Approval a where a.approval_person.userName = ?1")
  List<Approval> getApprovalByUser(String teacherUserName);
  
  @Query("select a from Approval a where a.approval_group_id = ?1")
  List<Approval> getApprovalByGroupId(String groupId);

}
