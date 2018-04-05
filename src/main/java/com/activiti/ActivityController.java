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

import com.activiti.model.Activity;
import com.activiti.model.ActivityViewObject;
import com.activiti.service.ActivityService;

import net.sf.json.JSONObject;

@Controller
@ComponentScan("com.activiti.service")
public class ActivityController {

  @Autowired
  private ActivityService activityService;
  
  private static final Logger logger = LoggerFactory.getLogger(ActivityController.class);
  
  @RequestMapping(value = "/activities", method = RequestMethod.GET)
  public @ResponseBody List<ActivityViewObject> getAllActivityUser() {
    List<Activity> activitys = activityService.getAllActivity();
    List<ActivityViewObject> activityViewObjects = new ArrayList<ActivityViewObject>();
    for(Activity activity : activitys) {
      ActivityViewObject activityViewObject = new ActivityViewObject();
      activityViewObject.setId(activity.getId());
      activityViewObject.setActivityName(activity.getActivityName());
      activityViewObject.setBudget(activity.getBudget());
      activityViewObject.setStarting_date(activity.getStarting_date());
      activityViewObject.setEnd_time(activity.getEnd_time());
      String chargeClub = activity.getChargeClub().getCollege();
      chargeClub += activity.getCharge_club().getDisplayName();
      activityViewObject.setChargeClub(chargeClub);
      activityViewObjects.add(activityViewObject);
    }
    return activityViewObjects;
  }
  
  @ResponseBody
  @RequestMapping(value = "/activities/{activityIds}", method = RequestMethod.DELETE)
  public String deleteActivities(@PathVariable Long[] activityIds) {

    logger.debug("Start to delete activities! " + activityIds);
    JSONObject jsonObj = new JSONObject();
    List<Activity> deletedActivities = new ArrayList<Activity>();
    for (int index = 0; index < activityIds.length; index++) {
      Activity activity = activityService.findById(activityIds[index]);
      deletedActivities.add(activity);
    }
    try {
      activityService.deletes(deletedActivities);
      jsonObj.put("result", "Delete Success!");
    } catch (Exception e) {
      logger.error(e.getMessage());
      jsonObj.put("result", "Delete Failed!");
    }
    return jsonObj.toString();
  }

}
