package com.activiti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activiti.model.Project;
import com.activiti.repository.ProjectRepository;

@Service
public class ProjectService {

  @Autowired
  private ProjectRepository projectRepository;
  
  public Project findByCardNum(String cardNum) {
    return projectRepository.findByCardNum(cardNum);
  }
  
  public void save(Project project) {
    projectRepository.save(project);
  }
  
}
