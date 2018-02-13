package com.activiti;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.activiti.service.StudentUserService;


@Controller
@ComponentScan("com.activiti.service")
public class MainController {
  
  private static final Logger logger = LoggerFactory.getLogger(MainController.class);
  
  @Autowired
  private StudentUserService studentUserService;
  
  @RequestMapping(value = {"/", "/home"})
  public String home(Map<String, Object> model) {
    model.put("username",studentUserService.getCurrentUserDisplayName());
    logger.debug("Welcome to home page.");
    return "home";
  }

}
