package com.activiti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activiti.model.DocumentItem;
import com.activiti.repository.DocumentItemRepository;

@Service
public class DocumentItemService {

  @Autowired
  private DocumentItemRepository itemRepository;
  
  public void saveDocumentItems(List<DocumentItem> documentItems) {
    itemRepository.save(documentItems);
  }
  
}