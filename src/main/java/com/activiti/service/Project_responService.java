package com.activiti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activiti.model.TeacherUser;
import com.activiti.repository.Project_responRepository;

@Service
public class Project_responService {

  @Autowired
  private Project_responRepository project_responRepository;
  
  public List<TeacherUser> getResponUsers(Long projectId) {
    return project_responRepository.getResponUsers(projectId);
  }
  
  
}
