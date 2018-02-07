package com.activiti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.activiti.model.Voucher;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {

}
