package com.activiti;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.activiti.model.Application;
import com.activiti.model.Feedback;
import com.activiti.model.FeedbackViewObject;
import com.activiti.model.Role;
import com.activiti.service.ApplicationService;
import com.activiti.service.FeedbackService;
import com.activiti.service.MailService;

import net.sf.json.JSONObject;

@Controller
@ComponentScan("com.activiti.service")
public class FeedbackController {

  private static final Logger logger = LoggerFactory.getLogger(FeedbackController.class);
  
  @Autowired
  private FeedbackService feedBackService;
  
  @Autowired
  private ApplicationService applicationService;
  
  @Autowired
  private IdentityService identityService;
  
  @Autowired
  private MailService mailService;
  
  @RequestMapping(value = "/feedbacks", method = RequestMethod.GET)
  public @ResponseBody List<FeedbackViewObject> getAllFeedback() {
    logger.debug("Start to get all feedbacks!");
    
    List<Feedback> feedbacks = feedBackService.getAllFeedback();
    List<FeedbackViewObject> feedbackViewObjects = new ArrayList<FeedbackViewObject>();
    for (Feedback feedback : feedbacks) {
      FeedbackViewObject feedbackViewObject = new FeedbackViewObject();
      feedbackViewObject.setId(feedback.getId());
      feedbackViewObject.setFeedback_time(feedback.getFeedback_time());
      feedbackViewObject.setSuggest(feedback.getSuggest());
      feedbackViewObject.setApplicationId(feedback.getApplication().getId());
      feedbackViewObjects.add(feedbackViewObject);
    }
    return feedbackViewObjects;
  }
  
  @ResponseBody
  @RequestMapping(value = "/feedbacks/{feedbackIds}", method = RequestMethod.DELETE)
  public String deleteFeedbacks(@PathVariable Long[] feedbackIds) {

    logger.debug("Start to delete applications! " + feedbackIds);
    JSONObject jsonObj = new JSONObject();
    List<Feedback> deletedFeedbacks = new ArrayList<Feedback>();
    for (int index = 0; index < feedbackIds.length; index++) {
      Feedback feedback = feedBackService.findById(feedbackIds[index]);
      deletedFeedbacks.add(feedback);
    }
    try {
      feedBackService.deleteFeedbacks(deletedFeedbacks);
      jsonObj.put("result", "Delete Success!");
    } catch (Exception e) {
      logger.error(e.getMessage());
      jsonObj.put("result", "Delete Failed!");
    }
    return jsonObj.toString();
  }
  
  @RequestMapping(value = "/feedbacks/{applicationId}", method = RequestMethod.POST)
  public String createMessage(@PathVariable Long applicationId, Feedback fd) {
    logger.debug("Start create a feedback!");
    Feedback feedback = new Feedback();
    Application allpication = applicationService.findById(applicationId);
    feedback.setApplication(allpication);
    feedback.setSuggest(fd.getSuggest());
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    Date day=new Date(); 
    feedback.setFeedback_time(df.format(day)); 
    feedBackService.saveFeedback(feedback);
    
    List<String> to = new ArrayList<String>();
    Group group = identityService.createGroupQuery().groupType(Role.finance_group.toString())
        .singleResult();
    List<User> groupUsers = identityService.createUserQuery().memberOfGroup(group.getId()).list();
    for (User user : groupUsers) {
      to.add(user.getEmail());
    }
    
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("message", "你有一份反馈需要处理。反馈内容" + fd.getSuggest() + ";" + "反馈人邮件：" + allpication.getOwner().getEmail());
    model.put("sendDate", "2018");
    logger.debug("strat to send emial.");
    mailService.mail(to.toArray(new String[0]), "报销系统提示", model, "fragments/Email");
    return "redirect:/applylist";
  }
}
