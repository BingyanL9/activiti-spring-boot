package com.activiti.service;

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
}
