package com.activiti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.activiti.model.Voucher;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {

}
