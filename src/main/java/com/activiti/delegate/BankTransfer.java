package com.activiti.delegate;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.activiti.model.Activity;
import com.activiti.model.Application;
import com.activiti.model.Application_Type;
import com.activiti.model.Approval;
import com.activiti.model.Approval_status;
import com.activiti.model.Project;
import com.activiti.model.StudentUser;
import com.activiti.model.TeacherUser;
import com.activiti.service.ActivityService;
import com.activiti.service.ApprovalService;
import com.activiti.service.ProjectService;
import com.activiti.service.StudentUserService;
import com.activiti.service.TeacherUserService;

public class BankTransfer implements JavaDelegate {
  private Expression application;

  @Autowired
  private TaskService taskService;
  
  @Autowired
  private RuntimeService runtimeService;
  
  @Autowired
  private ActivityService activityService;
  
  @Autowired
  private TeacherUserService teacherUserService;
  
  @Autowired
  private StudentUserService studentUserService;

  @Autowired
  private ProjectService projectService;
  
  @Autowired
  private ApprovalService approvalService;
  
  @Transactional
  @Override
  public void execute(DelegateExecution execution) {
    Application app = (Application) application.getValue(execution);
    Approval approval = app.getApproval();
    double balance = 0;
    if (app.getApplication_type().equals(Application_Type.ActivityExpense)) {
      Activity activity = app.getActivity();
      balance = activity.getBudget() - app.getTotal();
      if (balance >= 0) {
        activity.setBudget(balance);
        activityService.save(activity);
      } else {
        approval.setApproval_statu(Approval_status.rejected);
        approval.setDisapproval_reason("您没有足够的预算！");
      }
    }else if (app.getApplication_type().equals(Application_Type.DailyExpense)) {
      TeacherUser teacherUser = app.getApplication_teacher();
      balance = teacherUser.getBudget() - app.getTotal();
      if (balance >= 0) {
        teacherUser.setBudget(balance);
        teacherUserService.save(teacherUser);
      }else {
        approval.setApproval_statu(Approval_status.rejected);
        approval.setDisapproval_reason("您没有足够的预算！");
      }
    }else if (app.getApplication_type().equals(Application_Type.ProjectExpense)) {
      Project project = app.getProject();
      balance = project.getBudget() - app.getTotal();
      if (balance >= 0) {
        project.setBudget(balance);
        projectService.save(project);
      }else {
        approval.setApproval_statu(Approval_status.rejected);
        approval.setDisapproval_reason("您没有足够的预算！");
      }
    }else {
      StudentUser studentUser = app.getApplication_student();
      balance = studentUser.getBudget() - app.getTotal();
      if (balance >= 0) {
        studentUser.setBudget(balance);
        studentUserService.save(studentUser);
      }else {
        approval.setApproval_statu(Approval_status.rejected);
        approval.setDisapproval_reason("您没有足够的预算！");
      }
    }
    approvalService.save(approval);
    Task task = taskService.createTaskQuery().processInstanceId(approval.getProcessInstanceId())
        .singleResult();
    taskService.complete(task.getId());
    runtimeService.deleteProcessInstance(approval.getProcessInstanceId(), "finish");
  }

}
