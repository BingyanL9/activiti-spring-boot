package com.activiti;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.activiti.model.Project;
import com.activiti.model.ProjectViewObject;
import com.activiti.model.Project_respon;
import com.activiti.model.Project_responViewObject;
import com.activiti.model.StudentUser;
import com.activiti.model.TeacherUser;
import com.activiti.service.ProjectService;
import com.activiti.service.Project_responService;
import com.activiti.service.TeacherUserService;

import net.sf.json.JSONObject;

@Controller
@ComponentScan("com.activiti.service")
public class ProjectController {

  private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);
  
  @Autowired
  private ProjectService projectService;
  
  @Autowired
  private TeacherUserService teacherService;
  
  @Autowired
  private Project_responService project_responService;
  
  @RequestMapping(value = "/projects", method = RequestMethod.GET)
  public @ResponseBody List<ProjectViewObject> getAllProject() {
    logger.debug("Start to get all projects!");
    List<ProjectViewObject>  projectViewObjects = new ArrayList<ProjectViewObject>();
    List<Project> projects  = projectService.getAllProjects();
    for (Project project : projects) {
      ProjectViewObject projectViewObject = new ProjectViewObject();
      projectViewObject.setBudget(project.getBudget());
      projectViewObject.setCardNum(project.getCardNum());
      projectViewObject.setId(project.getId());
      projectViewObject.setProject_name(project.getProject_name());
      projectViewObject.setCash(project.getCash());
      projectViewObject.setStarting_date(project.getStarting_date());
      projectViewObject.setEnd_time(project.getEnd_time());
      projectViewObjects.add(projectViewObject);
    }
    return projectViewObjects;
  }
  
  @ResponseBody
  @RequestMapping(value = "/projects/{projectIds}", method = RequestMethod.DELETE)
  public String deleteActivities(@PathVariable("projectIds") Long[] projectIds) {

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
  
  @RequestMapping(value = "/projects", method = RequestMethod.POST)
  public @ResponseBody String addProjects(@ModelAttribute("projectViewObject") ProjectViewObject projectViewObject) {
    logger.debug("Add a project!");
    JSONObject jsonObj = new JSONObject();
    if (isNullProject(projectViewObject)) {
      jsonObj.put("result", "Add Failed! Something is null");
    } else {
      try {
        Project project = new Project();
        project.setBudget(projectViewObject.getBudget());
        project.setCardNum(projectViewObject.getCardNum());
        project.setEnd_time(projectViewObject.getEnd_time());
        project.setStarting_date(projectViewObject.getStarting_date());
        project.setProject_name(projectViewObject.getProject_name());
        projectService.save(project);
        jsonObj.put("result", "Add Success!");
      } catch (Exception e) {
        logger.error(e.getMessage());
        jsonObj.put("result", "Add Failed!");
      }

    }
    return jsonObj.toString();
  }

  private boolean isNullProject(ProjectViewObject project) {
    if (project.getBudget() == 0 || project.getCardNum() == "" || project.getEnd_time() == ""
        || project.getStarting_date() == "" || project.getProject_name() == "" ) {
      return true;
    }
    return false;
  }

  @ResponseBody
  @RequestMapping(value = "/projects/{projectId}", method = RequestMethod.PUT)
  public String editProjects(@ModelAttribute("projectViewObject") ProjectViewObject projectViewObject,
      @PathVariable("projectId") Long projectId) {
    logger.debug("Edit a project by projectId:" + projectId);
    JSONObject jsonObj = new JSONObject();
    if (isNullProject(projectViewObject)) {
      jsonObj.put("result", "Edit Failed! Something is null");
    } else {
      try {
        Project newProject = projectService.findById(projectId);
        newProject.setBudget(projectViewObject.getBudget());
        newProject.setCardNum(projectViewObject.getCardNum());
        newProject.setEnd_time(projectViewObject.getEnd_time());
        newProject.setCash(projectViewObject.getCash());
        newProject.setStarting_date(projectViewObject.getStarting_date());
        newProject.setProject_name(projectViewObject.getProject_name());
        newProject.setId(projectId);
        projectService.save(newProject);
        jsonObj.put("result", "Edit Success!");
      } catch (Exception e) {
        logger.error(e.getMessage());
        jsonObj.put("result", "Edit Failed!");
      }

    }
    return jsonObj.toString();
  }
  
  
  @RequestMapping(value = "/projectrespons", method = RequestMethod.GET)
  public @ResponseBody List<Project_responViewObject> getAllProject_respon() {
    logger.debug("Start to get all project respon!");
    List<Project_respon> project_respons = project_responService.getAll();
    List<Project_responViewObject> project_responViewObjects = new ArrayList<Project_responViewObject>();
   for (Project_respon project_respon : project_respons) {
     Project_responViewObject project_responViewObject = new Project_responViewObject();
     project_responViewObject.setId(project_respon.getId());
     project_responViewObject.setCharge(project_respon.getCharge().getCollege()+project_respon.getCharge().getDisplayName());
     project_responViewObject.setLevel(project_respon.getLevel());
     project_responViewObject.setProjectName(project_respon.getProject().getProject_name());
     project_responViewObjects.add(project_responViewObject);
   }
    return project_responViewObjects;
  }
  
  @RequestMapping(value = "/projectrespons", method = RequestMethod.POST)
  public @ResponseBody String addProject_respon(@ModelAttribute("project_responVO") Project_responViewObject project_responVO) {
    logger.debug("Add a project_respon!");
    JSONObject jsonObj = new JSONObject();
    if (checkCharge(project_responVO)) {
      jsonObj.put("result", "Add failed! This project has already charged by this teacher!");
    } else if(checkLevelWhenAdd(project_responVO)){
      jsonObj.put("result", "Add failed! You choose a wrong level! You have to increase in turn!");
    }else {
      try {
        Project_respon project_respon= new Project_respon();
        TeacherUser charge = teacherService.findByName(project_responVO.getCharge());
        project_respon.setCharge(charge);
        project_respon.setLevel(project_responVO.getLevel());
        Project project = projectService.findById(Long.valueOf(project_responVO.getProjectName()));
        project_respon.setProject(project);
        project_responService.save(project_respon);
        jsonObj.put("result", "Add Success!");
      } catch (Exception e) {
        logger.error(e.getMessage());
        jsonObj.put("result", "Add Failed!");
      }

    }
    return jsonObj.toString();
  }
  
  @RequestMapping(value = "/projectrespons/{projectresponId}", method = RequestMethod.PUT)
  public @ResponseBody String editProject_respon(@ModelAttribute("project_responVO") Project_responViewObject project_responVO,
      @PathVariable("projectresponId") Long projectresponId) {
    logger.debug("update a project_respon by id : " + projectresponId);
    JSONObject jsonObj = new JSONObject();
    if (checkChargeWhenUpdate(project_responVO)) {
      jsonObj.put("result", "Add failed! This project has already charged by this teacher!");
    }else {
      try {
        Project_respon project_respon= project_responService.findByid(projectresponId);
        TeacherUser charge = teacherService.findByName(project_responVO.getCharge());
        project_respon.setCharge(charge);
        project_respon.setLevel(project_responVO.getLevel());
        Project project = projectService.findById(Long.valueOf(project_responVO.getProjectName()));
        project_respon.setProject(project);
        project_responService.save(project_respon);
        jsonObj.put("result", "Update Success!");
      } catch (Exception e) {
        logger.error(e.getMessage());
        jsonObj.put("result", "Update Failed!");
      }

    }
    return jsonObj.toString();
  }
  
  @ResponseBody
  @RequestMapping(value = "/projectrespons/{projectresponIds}", method = RequestMethod.DELETE)
  public String deleteProject_respon(@PathVariable Long[] projectresponIds) {

    logger.debug("Start to delete Project_respons ! " + projectresponIds);

    JSONObject jsonObj = new JSONObject();
    List<Project_respon> deletedProject_respons = new ArrayList<Project_respon>();
    for (int index = 0; index < projectresponIds.length; index++) {
      Project_respon project_respon = project_responService.findByid(projectresponIds[index]);
      deletedProject_respons.add(project_respon);
    }
    try {
      project_responService.deleteProject_respons(deletedProject_respons);
      jsonObj.put("result", "Delete Success!");
    } catch (Exception e) {
      logger.error(e.getMessage());
      jsonObj.put("result", "Delete Failed!");
    }
    return jsonObj.toString();
  }

  private boolean checkChargeWhenUpdate(Project_responViewObject project_responVO) {
    Long projectId = Long.getLong(project_responVO.getProjectName());
    List<TeacherUser> charges = project_responService.getResponUsers(projectId);
    TeacherUser charge = teacherService.findByName(project_responVO.getCharge());
    int lastView = charges.lastIndexOf(charge);
    int firstView = charges.indexOf(charge);
    if(lastView == firstView) {
      return false;
    }
    return true;
  }

  private boolean checkLevelWhenAdd(Project_responViewObject project_responVO) {
    Long projectId = Long.valueOf(project_responVO.getProjectName());
    int maxLevel = project_responService.getMaxLevel(projectId);
    if (maxLevel + 1 == Integer.valueOf(project_responVO.getLevel())) {
      return false;
    }
    return true;
  }

  private boolean checkCharge(Project_responViewObject project_responVO) {
    Long projectId = Long.getLong(project_responVO.getProjectName());
    List<TeacherUser> charges = project_responService.getResponUsers(projectId);
    TeacherUser charge = teacherService.findByName(project_responVO.getCharge());
    if (charges.contains(charge)) {
      return true;
    }
    return false;
  }
  
}
