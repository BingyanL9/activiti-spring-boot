package com.activiti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activiti.model.Application;
import com.activiti.repository.ApplicationRepository;

@Service
public class ApplicationService {

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
}
