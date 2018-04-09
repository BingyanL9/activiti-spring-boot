package com.activiti.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.activiti.model.Activity;
import com.activiti.model.Project;
import com.activiti.repository.ProjectRepository;

@Service
public class ProjectService {

  @Autowired
  private ProjectRepository projectRepository;
  
  public static final int PAZESIZE = 10;
  
  public Project findByCardNum(String cardNum) {
    return projectRepository.findByCardNum(cardNum);
  }
  
  public void save(Project project) {
    projectRepository.save(project);
  }
  
  public List<Project> getAllProjects(){
    return projectRepository.findAll();
  }
  
  public Project findById(Long id) {
    return projectRepository.findOne(id);
  }
  
  public void deleteProjects(List<Project> projects) {
    projectRepository.delete(projects);
  }
  
}
