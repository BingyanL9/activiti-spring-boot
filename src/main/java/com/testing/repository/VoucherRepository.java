package com.testing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testing.model.Voucher;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {

}
