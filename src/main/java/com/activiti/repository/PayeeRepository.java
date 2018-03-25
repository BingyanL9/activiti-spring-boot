package com.activiti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.activiti.model.Payee;

@Repository
public interface PayeeRepository extends JpaRepository<Payee, Long> {

}
