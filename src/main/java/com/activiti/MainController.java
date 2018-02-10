package com.activiti;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@ComponentScan("com.activiti.service")
public class MainController {
  
  @RequestMapping(value = {"/"})
  public String home() {
    return "login";
  }

}
