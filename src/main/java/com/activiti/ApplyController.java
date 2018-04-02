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

import com.activiti.model.AbroadItem;
import com.activiti.model.AbroadOtherInfo;
import com.activiti.model.AccommodationItem;
import com.activiti.model.Activity;
import com.activiti.model.Application;
import com.activiti.model.Application_Type;
import com.activiti.model.Approval;
import com.activiti.model.Approval_status;
import com.activiti.model.CityTrafficExpenseViewObject;
import com.activiti.model.CityTrafficItem;
import com.activiti.model.DocumentExpenseViewObject;
import com.activiti.model.DocumentItem;
import com.activiti.model.OnboardTravelExpenseViewObject;
import com.activiti.model.OtherItem;
import com.activiti.model.Payee;
import com.activiti.model.Project;
import com.activiti.model.StudentUser;
import com.activiti.model.TeacherUser;
import com.activiti.model.TrafficAllowance;
import com.activiti.model.TravelExpenseViewObject;
import com.activiti.model.TravelItem;
import com.activiti.model.Voucher;
import com.activiti.service.ActivityService;
import com.activiti.service.ApplicationService;
import com.activiti.service.ApprovalService;
import com.activiti.service.ProjectService;
import com.activiti.service.StudentUserService;
import com.activiti.service.TeacherUserService;
import com.activiti.service.UserService;
@Controller
@ComponentScan("com.activiti.service")
public class ApplyController {
  
  @Autowired
  private ActivityService activityService;
  
  @Autowired
  private ProjectService projectService;
  
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
  private StudentUserService studentUserService;
  
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
  
  @RequestMapping(value = "/apply/travelinput/{travelItemIndex}", method = RequestMethod.GET)
  public String getTravelInput(@PathVariable("travelItemIndex")Long travelItemIndex, Map<String, Object> model) {
    model.put("travelItemIndex", travelItemIndex);
    return "fragments/travel_expense_form :: travel_item_input";
  }
  
  @RequestMapping(value = "/apply/accommodationinput/{accommodationItemIndex}", method = RequestMethod.GET)
  public String getAccommodationInput(@PathVariable("accommodationItemIndex")Long accommodationItemIndex, Map<String, Object> model) {
    model.put("accommodationItemIndex", accommodationItemIndex);
    return "fragments/travel_expense_form :: accommodation_item_input";
  }
  
  @RequestMapping(value = "/apply/otherinput/{otherItemIndex}", method = RequestMethod.GET)
  public String getOtherInput(@PathVariable("otherItemIndex")Long otherItemIndex, Map<String, Object> model) {
    model.put("otherItemIndex", otherItemIndex);
    return "fragments/travel_expense_form :: other_item_input";
  }
  
  @RequestMapping(value = "/apply/allowanceinput/{allowanceItemIndex}", method = RequestMethod.GET)
  public String getAllowanceInput(@PathVariable("allowanceItemIndex")Long allowanceItemIndex, Map<String, Object> model) {
    model.put("allowanceItemIndex", allowanceItemIndex);
    return "fragments/travel_expense_form :: allowance_item_input";
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
      documentItem = item;
      total += item.getItem_money();
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
      StudentUser studentUser = studentUserService.getCurrentUser();
      application.setApplication_student(studentUser);
      application.setHospitalName(devo.getHospitalName());
      application.setIllnessName(devo.getIllnessName());
      approval.setApproval_statu(Approval_status.level_1);
    }else if (devo.getApplication_Type() == Application_Type.DailyExpense) {
      TeacherUser teacher = teacherUserService.findCurrentUser();
      application.setApplication_teacher(teacher);
      approval.setApproval_person(teacher.getLeader());
      approval.setApproval_statu(Approval_status.pending);
    }else {
      Project project = projectService.findByCardNum(devo.getCardnum());
      application.setProject(project);
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
  
  @RequestMapping(value = "/apply/citytrafficexpense", method = RequestMethod.POST)
  public String createCityTrafficExpenseApplication( CityTrafficExpenseViewObject ctevo) {
    Deployment dep = repositoryService.createDeployment().addClasspathResource("processes/CityTrafficExpense.bpmn")
        .deploy();
    Map<String, Object> variableMap = new HashMap<String, Object>();
    
    Application application = new Application();
    double total = 0.0;
    application.setOwner(userService.getCurrentUser());
    Approval approval = new Approval();
    application.setDepartment(ctevo.getDepartment());
    application.setCreatetime(ctevo.getCreatetime());
    application.setCardNum(ctevo.getCardnum());
    List<CityTrafficItem> cityTrafficItems = new ArrayList<CityTrafficItem>();
    for (CityTrafficItem cityTrafficItem : ctevo.getCityTrafficItems()) {
      CityTrafficItem newCityTrafficItem = new CityTrafficItem();
      newCityTrafficItem = cityTrafficItem;
      newCityTrafficItem.setApplication(application);
      total += newCityTrafficItem.getTotal();
      cityTrafficItems.add(newCityTrafficItem);
    }
    application.setCityTrafficItems(cityTrafficItems);
    application.setTotal(total);
    
    List<Voucher> vouchers = new ArrayList<Voucher>();
    for (Voucher voucher : ctevo.getVouchers()) {
      Voucher documentVoucher = new Voucher();
      documentVoucher.setEnclosure(voucher.getEnclosure());
      documentVoucher.setApplication(application);
      vouchers.add(documentVoucher);
    }
    application.setVouchers(vouchers);
    variableMap.put("Application_Type", ctevo.getApplication_Type());
    application.setApplication_type(ctevo.getApplication_Type());
    if (ctevo.getApplication_Type() == Application_Type.DailyExpense) {
      TeacherUser teacher = teacherUserService.findCurrentUser();
      application.setApplication_teacher(teacher);
      approval.setApproval_person(teacher.getLeader());
      approval.setApproval_statu(Approval_status.pending);
    }else {
      Project project = projectService.findByCardNum(ctevo.getCardnum());
      application.setProject(project);
      approval.setApproval_statu(Approval_status.pending);
    }
    
    String payMode = ctevo.getPaymode();
    application.setPaymode(payMode);
    Payee payee = new Payee();
    payee = ctevo.getPayee();
    ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
        .deploymentId(dep.getId()).singleResult();
    ProcessInstance processInstance = runtimeService.startProcessInstanceById(pd.getId());
    approval.setProcessInstanceId(processInstance.getId());
    Task t1 = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
    taskService.complete(t1.getId(), variableMap);
    approvalService.saveWhenCreate(payee, approval, application);
    return "redirect:/applylist";
    
  }
  
  @RequestMapping(value = "/apply/travelexpense", method = RequestMethod.POST)
  public String createTravelExpenseApplication(TravelExpenseViewObject tevo) {
    Deployment dep = repositoryService.createDeployment().addClasspathResource("processes/CityTrafficExpense.bpmn")
        .deploy();
    Map<String, Object> variableMap = new HashMap<String, Object>();
    
    Application application = new Application();
    double total = 0.0;
    application.setOwner(userService.getCurrentUser());
    Approval approval = new Approval();
    application.setDepartment(tevo.getDepartment());
    application.setCreatetime(tevo.getCreatetime());
    application.setCardNum(tevo.getCardnum());
    application.setTravelStartDate(tevo.getStartTraveltime());
    application.setTravelEndDate(tevo.getEndTraveltime());
    application.setTravelReason(tevo.getTravelReason());
    application.setTravelPersonName(tevo.getTravelPersonName());
    application.setTravelPersonPosition(tevo.getTravelPersonPosition());
    List<TravelItem> travelItems = new ArrayList<TravelItem>();
    for (TravelItem travelItem : tevo.getTravelItems()) {
      TravelItem newTravelItem = new TravelItem();
      newTravelItem = travelItem;
      newTravelItem.setApplication(application);
      total += travelItem.getTrafficFare();
      travelItems.add(newTravelItem);
    }
    application.setTravelItems(travelItems);
    
    List<AccommodationItem> accommodationItems = new ArrayList<AccommodationItem>();
    for (AccommodationItem accommodationItem : tevo.getAccommodationItems()) {
      AccommodationItem newAccommodationItem = new AccommodationItem();
      newAccommodationItem = accommodationItem;
      total += accommodationItem.getAccommodationFare();
      newAccommodationItem.setApplication(application);
      accommodationItems.add(newAccommodationItem);
    }
    application.setAccommodationItems(accommodationItems);
    
    List<OtherItem> otherItems = new ArrayList<OtherItem>();
    for (OtherItem otherItem : tevo.getOtherItems()) {
      OtherItem newOtherItem = new OtherItem();
      newOtherItem = otherItem;
      total += otherItem.getOtherFare();
      newOtherItem.setApplication(application);
      otherItems.add(newOtherItem);
    }
    application.setOtherItems(otherItems);
    
    List<TrafficAllowance> trafficAllowances = new ArrayList<TrafficAllowance>();
    for (TrafficAllowance trafficAllowance : tevo.getTrafficAllowances()) {
      TrafficAllowance newTrafficAllowance = new TrafficAllowance();
      newTrafficAllowance = trafficAllowance;
      newTrafficAllowance.setApplication(application);
      trafficAllowances.add(newTrafficAllowance);
    }
    application.setTrafficAllowances(trafficAllowances);
    application.setTotal(total);
    
    List<Voucher> vouchers = new ArrayList<Voucher>();
    for (Voucher voucher : tevo.getVouchers()) {
      Voucher documentVoucher = new Voucher();
      documentVoucher.setEnclosure(voucher.getEnclosure());
      documentVoucher.setApplication(application);
      vouchers.add(documentVoucher);
    }
    application.setVouchers(vouchers);
    variableMap.put("Application_Type", tevo.getApplication_Type());
    application.setApplication_type(tevo.getApplication_Type());
    if (tevo.getApplication_Type() == Application_Type.DailyExpense) {
      TeacherUser teacher = teacherUserService.findCurrentUser();
      application.setApplication_teacher(teacher);
      approval.setApproval_person(teacher.getLeader());
      approval.setApproval_statu(Approval_status.pending);
    }else {
      Project project = projectService.findByCardNum(tevo.getCardnum());
      application.setProject(project);
      approval.setApproval_statu(Approval_status.pending);
    }
    
    String payMode = tevo.getPaymode();
    application.setPaymode(payMode);
    Payee payee = tevo.getPayee();
    application.setManager(tevo.getManager());
    application.setSubmitter(tevo.getSubmitter());
    application.setSubmitterTell(tevo.getSubmitterTell());
    ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
        .deploymentId(dep.getId()).singleResult();
    ProcessInstance processInstance = runtimeService.startProcessInstanceById(pd.getId());
    approval.setProcessInstanceId(processInstance.getId());
    Task t1 = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
    taskService.complete(t1.getId(), variableMap);
    approvalService.saveWhenCreate(payee, approval, application);
    return "redirect:/applylist";
  }
  
  @RequestMapping(value = "/apply/onbroadtravelexpense", method = RequestMethod.POST)
  public String createOnbroadTravelExpenseApplication( OnboardTravelExpenseViewObject otevo) {
    Deployment dep = repositoryService.createDeployment().addClasspathResource("processes/CityTrafficExpense.bpmn")
        .deploy();
    Map<String, Object> variableMap = new HashMap<String, Object>();
    
    Application application = new Application();
    application.setOwner(userService.getCurrentUser());
    Approval approval = new Approval();
    application.setDepartment(otevo.getDepartment());
    application.setCreatetime(otevo.getCreatetime());
    application.setCardNum(otevo.getCardnum());
    application.setTravelStartDate(otevo.getStartTraveltime());
    application.setTravelEndDate(otevo.getEndTraveltime());
    application.setTravelReason(otevo.getOnboardReason());
    
    List<AbroadItem> abroadItems = new ArrayList<AbroadItem>();
    for (AbroadItem abroadItem : otevo.getAbroadItems()) {
      AbroadItem newAbroadItem = new AbroadItem();
      newAbroadItem = abroadItem;
      newAbroadItem.setApplication(application);
      abroadItems.add(newAbroadItem);
    }
    application.setAbroadItems(abroadItems);
    
    List<Voucher> vouchers = new ArrayList<Voucher>();
    for (Voucher voucher : otevo.getVouchers()) {
      Voucher documentVoucher = new Voucher();
      documentVoucher.setEnclosure(voucher.getEnclosure());
      documentVoucher.setApplication(application);
      vouchers.add(documentVoucher);
    }
    application.setVouchers(vouchers);
    variableMap.put("Application_Type", otevo.getApplication_Type());
    application.setApplication_type(otevo.getApplication_Type());
    if (otevo.getApplication_Type() == Application_Type.DailyExpense) {
      TeacherUser teacher = teacherUserService.findCurrentUser();
      application.setApplication_teacher(teacher);
      approval.setApproval_person(teacher.getLeader());
      approval.setApproval_statu(Approval_status.pending);
    }else {
      Project project = projectService.findByCardNum(otevo.getCardnum());
      application.setProject(project);
      approval.setApproval_statu(Approval_status.pending);
    }
    
    String payMode = otevo.getOnboardPaymode();
    application.setPaymode(payMode);
    Payee payee = otevo.getPayee();
    
    AbroadOtherInfo abroadOtherInfo = otevo.getAbroadOtherInfo();
    application.setTotal(otevo.getTotal());
    ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
        .deploymentId(dep.getId()).singleResult();
    ProcessInstance processInstance = runtimeService.startProcessInstanceById(pd.getId());
    approval.setProcessInstanceId(processInstance.getId());
    Task t1 = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
    taskService.complete(t1.getId(), variableMap);
    approvalService.saveWhenCreate(payee, approval, application, abroadOtherInfo);
    return "redirect:/applylist";
    
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
//    model.put("user", userService.getCurrentUser());
//    model.put("menu", "applyList");
    return "fragments/applyDetail :: applyinfo";
  }


}
 