package com.activiti;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
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
import com.activiti.model.Approval;
import com.activiti.model.Approval_status;
import com.activiti.model.DocumentExpenseViewObject;
import com.activiti.model.DocumentItem;
import com.activiti.model.Payee;
import com.activiti.model.TeacherUser;
import com.activiti.model.Voucher;
import com.activiti.service.ActivityService;
import com.activiti.service.ApplicationService;
import com.activiti.service.ApprovalService;
import com.activiti.service.DocumentItemService;
import com.activiti.service.TeacherUserService;
import com.activiti.service.UserService;
@Controller
@ComponentScan("com.activiti.service")
public class applyController {
  
  @Autowired
  private DocumentItemService documentItemService;
  
  @Autowired
  private ActivityService activityService;
  
  @Autowired
  private RepositoryService repositoryService;
  
  @Autowired
  private RuntimeService runtimeService;
  
  @Autowired
  private ApprovalService approvalService;
  
  @Autowired
  private TeacherUserService teacherUserService;
  
  @Autowired
  private UserService userService;
  
  @Autowired
  private TaskService taskService;
  
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
    
    String filename = "processes/DocumentExpense.bpmn";
    Deployment dep = repositoryService.createDeployment().addClasspathResource(filename)
    .addClasspathResource("templates/fragments/document_expense_form.html").deploy();
    
    Map<String, Object> variableMap = new HashMap<String, Object>();
    
    Application application = new Application();
    application.setOwner(userService.getCurrentUser());
    Approval approval = new Approval();
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
    variableMap.put("Application_Type", expense_type);
    if (expense_type == Application_Type.ActivityExpense.toString()) {
      Activity activity =
          activityService.findByCardNumAndActivityName(devo.getActivityName(), devo.getCardnum());
      application.setActivity(activity);
      approval.setApprocval_club(activity.getCharge_club());
      approval.setApproval_statu(Approval_status.level_1);
    }else if (expense_type == Application_Type.MedicalExpense.toString()) {
      application.setHospitalName(devo.getHospitalName());
      application.setIllnessName(devo.getIllnessName());
      approval.setApproval_statu(Approval_status.level_1);
    }else if (expense_type == Application_Type.DailyExpense.toString()) {
      TeacherUser teacher = teacherUserService.findCurrentUser();
      approval.setApproval_person(teacher.getLeader());
    }
    approval.setApproval_statu(Approval_status.pending);
    String payMode = devo.getPaymode();
    application.setPaymode(payMode);
    if (payMode != "cash") {
      Payee payee = new Payee();
      payee = devo.getPayee();
      payee.setApplication(application);
      application.setPayee(payee);
    }
    
    ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
        .deploymentId(dep.getId()).singleResult();
    ProcessInstance processInstance = runtimeService.startProcessInstanceById(pd.getId());
    approval.setProcessInstanceId(processInstance.getId());
    Task t1 = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
    taskService.complete(t1.getId(), variableMap);
    approvalService.saveWhenCreate(approval, application);
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
 