package com.activiti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.activiti.service.TeacherUserService;
import com.activiti.service.UserService;
@Controller
@ComponentScan("com.activiti.service")
public class ApplyController {
  
  @Autowired
  private ActivityService activityService;
  
  @Autowired
  private ApplicationService applicationService;
  
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
  
  @RequestMapping(value = "/apply/trafficinput/{itemIndex}", method = RequestMethod.GET)
  public String getTrafficInput(@PathVariable("itemIndex")Long itemIndex, Map<String, Object> model) {
    model.put("itemIndex", itemIndex);
    return "fragments/city_traffic_expense_form :: traffic_info_input";
  }
  
  @RequestMapping(value = "/apply/documentexpense", method = RequestMethod.POST)
  public String createDocumentExpenseApplication( DocumentExpenseViewObject devo) {
    logger.debug("Start create document expense application!");
    Deployment dep = repositoryService.createDeployment().addClasspathResource("processes/DocumentExpense.bpmn")
        .deploy();
    Map<String, Object> variableMap = new HashMap<String, Object>();
    
    Application application = new Application();
    double total = 0.0;
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
      total += item.getItem_money();
      documentItem.setItem_description(item.getItem_description());
      documentItem.setApplication(application);
      items.add(documentItem);
    }
    application.setDocumentItems(items);
    application.setTotal(total);
    List<Voucher> vouchers = getVouchers(devo, application);
    application.setVouchers(vouchers);
    variableMap.put("Application_Type", devo.getApplication_Type());
    application.setApplication_type(devo.getApplication_Type());
    if (devo.getApplication_Type() == Application_Type.ActivityExpense) {
      Activity activity =
          activityService.findByCardNumAndActivityName(devo.getActivityName(), devo.getCardnum());
      application.setActivity(activity);
      approval.setApprocval_club(activity.getCharge_club());
      approval.setApproval_statu(Approval_status.level_1);
    }else if (devo.getApplication_Type() == Application_Type.MedicalExpense) {
      application.setHospitalName(devo.getHospitalName());
      application.setIllnessName(devo.getIllnessName());
      approval.setApproval_statu(Approval_status.level_1);
    }else if (devo.getApplication_Type() == Application_Type.DailyExpense) {
      TeacherUser teacher = teacherUserService.findCurrentUser();
      approval.setApproval_person(teacher.getLeader());
      approval.setApproval_statu(Approval_status.pending);
    }else {
      approval.setApproval_statu(Approval_status.pending);
    }
    
    String payMode = devo.getPaymode();
    application.setPaymode(payMode);
    Payee payee = new Payee();
    payee = devo.getPayee();
    ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
        .deploymentId(dep.getId()).singleResult();
    ProcessInstance processInstance = runtimeService.startProcessInstanceById(pd.getId());
    approval.setProcessInstanceId(processInstance.getId());
    Task t1 = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
    taskService.complete(t1.getId(), variableMap);
    approvalService.saveWhenCreate(payee, approval, application);
    return "redirect:/applylist";
 }

  private List<Voucher> getVouchers(DocumentExpenseViewObject devo, Application application) {
    List<Voucher> vouchers = new ArrayList<Voucher>();
    for (Voucher voucher : devo.getVouchers()) {
      Voucher documentVoucher = new Voucher();
      documentVoucher.setEnclosure(voucher.getEnclosure());
      documentVoucher.setApplication(application);
      vouchers.add(documentVoucher);
    }
    return vouchers;
  }
  
  @RequestMapping(value = "/applications/{applicationId}", method = RequestMethod.GET)
  public String getApplications(Map<String, Object> model, @PathVariable("applicationId") Long applicationId) {
    logger.debug("Start to get application by id : " + applicationId);
    Application application = applicationService.findById(applicationId);
    model.put("applicationObject", application);
    List<String> pictures = new ArrayList<String>();
    for (Voucher voucher : application.getVouchers()) {
      String picture = new String(voucher.getEnclosure());
      pictures.add(picture);
    }
    model.put("vouchers", pictures);
    model.put("user", userService.getCurrentUser());
    model.put("menu", "applyList");
    return "applyInfo";
  }


}
 