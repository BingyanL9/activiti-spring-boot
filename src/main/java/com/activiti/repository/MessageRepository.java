package com.activiti.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.activiti.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long>{
  
  @Query("select m from Message m order by m.issue_time desc ")
  List<Message> findMessage(Pageable pageable);

}
