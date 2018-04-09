package com.activiti.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.activiti.model.Project_respon;
import com.activiti.model.TeacherUser;
import com.activiti.repository.Project_responRepository;

@Service
public class Project_responService {

  @Autowired
  private Project_responRepository project_responRepository;
  
  public List<TeacherUser> getResponUsers(Long projectId) {
    return project_responRepository.getResponUsers(projectId);
  }
  
  public List<Project_respon> getAll(){
    return project_responRepository.findAll();
  }
  
  public Project_respon findByid(Long id) {
    return project_responRepository.getOne(id);
  }
  
  @Transactional
  public void deleteProject_respons(List<Project_respon> project_respons) {
//    for(Project_respon project_respon : project_respons) {
//      project_respon.setCharge(null);
//      project_respon.setProject(null);
//    }
//    project_responRepository.save(project_respons);
    project_responRepository.delete(project_respons);
  }
  
  public int getMaxLevel(Long projectId) {
    List<String> levels = project_responRepository.getLevels(projectId);
    int max = 0;
    if(!levels.isEmpty()) {
      max = Integer.valueOf(Collections.max(levels));
    }
    return max;
  }
  
  public void save(Project_respon project_respon) {
    project_responRepository.save(project_respon);
  }
  
  public TeacherUser getResponUser(Long projectId, String level) {
    return project_responRepository.getResponUser(projectId, level);
  }
  
  public int getResponUserSize(Long projectId) {
    return getResponUsers(projectId).size();
  }
  
}
