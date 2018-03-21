package com.activiti;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.activiti.model.DocumentExpenseViewObject;
import com.activiti.repository.ItemRepository;
import com.activiti.service.ActivityService;
import com.activiti.service.ClubUserService;
import com.activiti.service.StudentUserService;

@Controller
@ComponentScan("com.activiti.service")
public class applyController {
  
  @Autowired
  private ItemRepository itemRepository;
  
  @Autowired
  private StudentUserService studentUserService;
  
  @Autowired
  private ClubUserService clubUserService;
  
  @Autowired
  private ActivityService activityService;
  
  private static final Logger logger = LoggerFactory.getLogger(MainController.class);
  
  @RequestMapping(value = "/apply/iteminput/{itemIndex}", method = RequestMethod.GET)
  public String getItemInput( @PathVariable("itemIndex")Long itemIndex, Map<String, Object> model) {
    model.put("itemIndex", itemIndex);
    return "fragments/document_expense_form :: item_input";
  }
  
  @RequestMapping(value = "/apply/voucherinput/{voucherIndex}", method = RequestMethod.GET)
  public String getVoucherInput( @PathVariable("voucherIndex")Long voucherIndex, Map<String, Object> model) {
    model.put("itemIndex", voucherIndex);
    return "fragments/document_expense_form :: voucher_input";
  }
  
  @RequestMapping(value = "/apply/trafficinput", method = RequestMethod.GET)
  public String getTrafficInput() {
    return "fragments/traffic_info_input :: traffic_info_input";
  }
  
  @RequestMapping(value = "/apply/documentexpense", method = RequestMethod.POST)
  public String createDocumentExpenseApplication( DocumentExpenseViewObject devo) {
    logger.debug("Start create document expense application!");
    
    return null;
 }
  

}
 