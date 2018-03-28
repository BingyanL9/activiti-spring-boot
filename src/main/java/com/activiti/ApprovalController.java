package com.activiti;

import java.util.Map;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.activiti.model.User;

@Controller
@ComponentScan("com.activiti.service")
public class ApprovalController {
  
//  @RequestMapping(value = "/approvals", method = RequestMethod.GET)
//  public String getApprovals(Map<String, Object> model) {
//    User user = userService.getCurrentUser();
//    model.put("user", user);
//  }

}
