package com.activiti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activiti.repository.Project_responRepository;

@Service
public class Project_responService {

  @Autowired
  private Project_responRepository project_responRepository;
}
