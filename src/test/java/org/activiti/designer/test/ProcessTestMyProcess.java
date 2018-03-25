package org.activiti.designer.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.io.FileInputStream;

import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.activiti.DemoApplication;
import com.activiti.model.Application;
import com.activiti.model.DocumentItem;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@ActiveProfiles("test")
public class ProcessTestMyProcess {
  
    @Autowired
    private RepositoryService repositoryService;
    
    @Autowired
    private RuntimeService runtimeService;
    
    @Autowired
    private FormService formService;
    
    @Autowired
    private TaskService taskService;

	private String filename = "src/main/resources/processes/MyProcess.bpmn";


	@Test
	public void startProcess() {
//		repositoryService.createDeployment().addInputStream("myProcess.bpmn20.xml",
//				new FileInputStream(filename)).deploy();
//	  Deployment dep = repositoryService.createDeployment().addClasspathResource("processes/MyProcess.bpmn")
//	  .addClasspathResource("templates/fragments/item_input.html").deploy();
	  Deployment dep = repositoryService.createDeployment().addClasspathResource("processes/DocumentExpense.bpmn")
	      .deploy();
	  ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
	      .deploymentId(dep.getId()).singleResult();
	  Map<String, Object> variableMap = new HashMap<String, Object>();
	  ProcessInstance processInstance = runtimeService.startProcessInstanceById(pd.getId());
      variableMap.put("item_name", "name");
	  Task t1 = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
	  Object obj = formService.getRenderedTaskForm(t1.getId());
	  System.out.println(formService.getTaskFormData(t1.getId()));
	  taskService.complete(t1.getId(), variableMap);
	  Task t2 = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
	  assertNotNull(processInstance.getId());
	  System.out.println(obj);
	  System.out.println(t2.getName());
	}
}