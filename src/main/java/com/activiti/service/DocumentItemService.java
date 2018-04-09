package com.activiti.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activiti.model.DocumentItem;
import com.activiti.repository.DocumentItemRepository;

@Service
public class DocumentItemService implements Serializable {

  private static final long serialVersionUID = 1L;
  
  @Autowired
  private DocumentItemRepository itemRepository;
  
  public void saveDocumentItems(List<DocumentItem> documentItems) {
    itemRepository.save(documentItems);
  }
  
}