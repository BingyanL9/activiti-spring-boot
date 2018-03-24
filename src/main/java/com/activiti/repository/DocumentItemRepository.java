package com.activiti.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.activiti.model.DocumentItem;

@Repository
public interface DocumentItemRepository extends JpaRepository<DocumentItem, Long> {

}
