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

import com.activiti.model.Application;
import com.activiti.model.ApplicationViewObject;
import com.activiti.model.Feedback;
import com.activiti.model.FeedbackViewObject;
import com.activiti.service.FeedbackService;

import net.sf.json.JSONObject;

@Controller
@ComponentScan("com.activiti.service")
public class FeedbackController {

  private static final Logger logger = LoggerFactory.getLogger(FeedbackController.class);
  
  @Autowired
  private FeedbackService feedBackService;
  
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
      feedbackViewObject.setCorrect(feedback.isCorrect());
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
}
