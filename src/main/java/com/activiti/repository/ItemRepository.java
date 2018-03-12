package com.activiti.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.activiti.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
