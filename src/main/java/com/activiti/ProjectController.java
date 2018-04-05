package com.activiti;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.activiti.model.Project;
import com.activiti.service.ProjectService;

import net.sf.json.JSONObject;

@Controller
@ComponentScan("com.activiti.service")
public class ProjectController {

  private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);
  
  @Autowired
  private ProjectService projectService;
  
  @RequestMapping(value = "/projects", method = RequestMethod.GET)
  public @ResponseBody List<Project> getAllProject() {
    logger.debug("Start to get all projects!");
    
    return projectService.getAllProjects();
  }
  
  @ResponseBody
  @RequestMapping(value = "/projects/{projectIds}", method = RequestMethod.DELETE)
  public String deleteActivities(@PathVariable Long[] projectIds) {

    logger.debug("Start to delete activities! " + projectIds);
    JSONObject jsonObj = new JSONObject();
    List<Project> deletedProjects = new ArrayList<Project>();
    for (int index = 0; index < projectIds.length; index++) {
      Project project = projectService.findById(projectIds[index]);
      deletedProjects.add(project);
    }
    try {
      projectService.deleteProjects(deletedProjects);
      jsonObj.put("result", "Delete Success!");
    } catch (Exception e) {
      logger.error(e.getMessage());
      jsonObj.put("result", "Delete Failed!");
    }
    return jsonObj.toString();
  }
}
