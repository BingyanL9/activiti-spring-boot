package com.activiti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activiti.repository.ItemRepository;

@Service
public class ItemService {

  @Autowired
  private ItemRepository itemRepository;
  
}