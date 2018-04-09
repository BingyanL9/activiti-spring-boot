package com.activiti.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activiti.model.Application;
import com.activiti.model.DocumentItem;
import com.activiti.repository.ApplicationRepository;

@Service
public class ApplicationService implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  @Autowired
  private ApplicationRepository applicationRepository;
  
  public void save(Application application) {
    applicationRepository.save(application);
  }
  
  public List<Application> getApplicationsByUser(String userName){
    return applicationRepository.getApplicationsByUser(userName);
  }
  
  public Application findById(Long applicationId) {
    return applicationRepository.findById(applicationId);
  }
  
  public List<Application> getAllApplication(){
    return applicationRepository.findAll();
  }
  
  public void deleteApplications(List<Application> applications) {
    applicationRepository.delete(applications);
  }
  
  public boolean isNeedAssetProcessingOfficeApproval(Long applicationId) {
    Application application = findById(applicationId);
    List<DocumentItem> items = application.getDocumentItems();
    
    for (DocumentItem documentItem : items) {
      if (documentItem.getItem_description().contains("一般设备") && documentItem.getItem_money() > 1500) {
        return true;
      }
      
      if (documentItem.getItem_description().contains("特殊设备") && documentItem.getItem_money() > 1000) {
        return true;
      }
    }
    
    return false;
  }
  
  public boolean isNeedLibraryApproval(Long applicationId) {
    Application application = findById(applicationId);
    List<DocumentItem> items = application.getDocumentItems();
    
    for (DocumentItem documentItem : items) {
      if (documentItem.getItem_description().contains("图书") && documentItem.getItem_money() > 1000) {
        return true;
      }
    }
    
    return false;
  }
  
}
