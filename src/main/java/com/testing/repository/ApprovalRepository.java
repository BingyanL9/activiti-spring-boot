package com.testing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testing.model.Approval;

public interface ApprovalRepository extends JpaRepository<Approval, Long> {

}
