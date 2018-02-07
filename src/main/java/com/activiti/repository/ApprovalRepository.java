package com.activiti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.activiti.model.Approval;
@Repository
public interface ApprovalRepository extends JpaRepository<Approval, Long> {

}
