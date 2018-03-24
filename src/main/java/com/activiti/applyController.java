package com.activiti;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.activiti.model.Activity;
import com.activiti.model.Application;
import com.activiti.model.Application_Type;
import com.activiti.model.DocumentExpenseViewObject;
import com.activiti.model.DocumentItem;
import com.activiti.model.Voucher;
import com.activiti.service.ActivityService;
import com.activiti.service.DocumentItemService;
@Controller
@ComponentScan("com.activiti.service")
public class applyController {
  
  @Autowired
  private DocumentItemService documentItemService;
  
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
  public String createDocumentExpenseApplication( DocumentExpenseViewObject devo,
      HttpServletRequest request) {
    logger.debug("Start create document expense application!");
    
    Application application = new Application();
    application.setDepartment(devo.getDepartment());
    application.setCreatetime(devo.getCreatetime());
    application.setCardNum(devo.getCardnum());
    List<DocumentItem> items = new ArrayList<DocumentItem>();
    for (DocumentItem item: devo.getItems()) {
      DocumentItem documentItem = new DocumentItem();
      documentItem.setItem_name(item.getItem_name());
      documentItem.setItem_money(item.getItem_money());
      documentItem.setItem_description(item.getItem_description());
      documentItem.setApplication(application);
      items.add(item);
    }
    documentItemService.saveDocumentItems(items);
    application.setDocumentItems(items);
    List<Voucher> vouchers = getVouchers(devo, application);
    application.setVouchers(vouchers);
    String expense_type = devo.getExpense_type();
    if (expense_type == Application_Type.ActivityExpense.toString()) {
      Activity activity =
          activityService.findByCardNumAndActivityName(devo.getActivityName(), devo.getCardnum());
      application.setActivity(activity);
    }else if (expense_type == Application_Type.MedicalExpense.toString()) {
      application.setHospitalName(devo.getHospitalName());
      application.setIllnessName(devo.getIllnessName());
    }
    return null;
 }

  private List<Voucher> getVouchers(DocumentExpenseViewObject devo, Application application) {
    List<Voucher> vouchers = new ArrayList<Voucher>();
    for (Voucher voucher : devo.getVouchers()) {
      Voucher documentVoucher = new Voucher();
      InputStream inputStream = null;
      try {
        inputStream = new FileInputStream(voucher.getEnclosure());
      } catch (FileNotFoundException e) {
        logger.error(voucher.getEnclosure() + " file not found! " + e.toString());
      }
      ByteArrayOutputStream result = new ByteArrayOutputStream();
      byte[] buffer = new byte[1024];
      int length;
      try {
        while ((length = inputStream.read(buffer)) != -1) {
          result.write(buffer, 0, length);
        }
      } catch (IOException e) {
        logger.error(voucher.getEnclosure() + " read exception! " + e.toString());
      }
      try {
        documentVoucher.setEnclosure( result.toString("UTF-8"));
      } catch (UnsupportedEncodingException e) {
        logger.error(e.toString());
      }
      documentVoucher.setApplication(application);
      vouchers.add(documentVoucher);
    }
    return vouchers;
  }
  

}
 