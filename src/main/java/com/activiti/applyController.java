package com.activiti;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.activiti.model.Activity;
import com.activiti.model.Application;
import com.activiti.model.Application_Type;
import com.activiti.model.ClubUser;
import com.activiti.model.DocumentExpenseViewObject;
import com.activiti.model.DocumentItem;
import com.activiti.model.Voucher;
import com.activiti.repository.ItemRepository;
import com.activiti.repository.VoucherRepository;
import com.activiti.service.ActivityService;
import com.activiti.service.ClubUserService;
import com.activiti.service.ItemService;
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
  
  @RequestMapping(value = "/apply/iteminput", method = RequestMethod.GET)
  public String getItemInput() {
    return "fragments/item_input :: item_input";
  }
  
  @RequestMapping(value = "/apply/trafficinput", method = RequestMethod.GET)
  public String getTrafficInput() {
    return "fragments/traffic_info_input :: traffic_info_input";
  }
  
//  @RequestMapping(value = "/apply/documentexpense", method = RequestMethod.POST)
//  public String createDocumentExpenseApplication( DocumentExpenseViewObject devo) {
//    logger.debug("Start create document expense application!");
//    Application application = new Application();
//    
//    Voucher voucher = new Voucher();
//    List<DocumentItem> items = new ArrayList<DocumentItem>();
//    DocumentItem item1 = new DocumentItem();
//    item1.setItem_name(devo.getItem1());
//    item1.setItem_money(Double.parseDouble(devo.getItemMoney1()));
//    items.add(item1);
//    DocumentItem item2 = new DocumentItem();
//    item2.setItem_name(devo.getItem2());
//    item2.setItem_money(Double.parseDouble(devo.getItemMoney2()));
//    items.add(item2);
//    DocumentItem item3 = new DocumentItem();
//    item3.setItem_name(devo.getItem3());
//    item3.setItem_money(Double.parseDouble(devo.getItemMoney3()));
//    items.add(item3);
//    itemRepository.save(items);
//    voucher.setItems(items);
//    List<String> enclosures = new ArrayList<String>();
//    enclosures.add(devo.getEnclosure1());
//    enclosures.add(devo.getEnclosure2());
//    enclosures.add(devo.getEnclosure3());
//    voucher.setEnclosure(enclosures);
//    Application_Type application_type =
//        studentUserService.getApplicationTypebyCardnum(devo.getCardnum());
//    application
//        .setApplication_type(application_type);
//    application.setCreatetime(devo.getCreatetime());
//    application.setPaymode(devo.getPaymode());
//    if (devo.getPaymode() != "cash") {
//    application.setPayee_account(devo.getPayee_account());
//    application.setPayee_account_opening_bank(devo.getPayee_account_opening_bank());
//    application.setPayee_name(devo.getPayee_name());
//    }
//    
//    if ( application_type.equals(Application_Type.ActivityExpense)) {
//      ClubUser clubUser = clubUserService.getCurrentUser();
//      Activity activity = activityService.findByNameAndChargeClub(devo.getActivityName(), clubUser);
//      application.setActivity(activity);
//    }else {
//      
//    }
//     
//    return null;
// }
  

}
 