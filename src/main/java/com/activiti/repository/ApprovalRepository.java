package com.activiti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.activiti.model.Approval;

public interface ApprovalRepository extends JpaRepository<Approval, Long> {

}
