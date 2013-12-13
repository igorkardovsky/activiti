import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.identity.User;

// Pure Java Start
public class Test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("org.slf4j.simpleLogger.log.org.activiti.engine","debug");
	    ProcessEngine processEngine = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration()
	    	      .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_FALSE)
	    	      .setJdbcUrl("jdbc:h2:mem:my-own-db;DB_CLOSE_DELAY=1000").setDatabaseSchemaUpdate("true")
	    	      .setJobExecutorActivate(true)
	    	      .buildProcessEngine();
	    
	    IdentityService is = processEngine.getIdentityService();
	    
	    List<User> users = is.createUserQuery().list();
	    for(User u:users){
	    	System.out.println(u.getId());
	    }
	    
	    RepositoryService repositoryService = processEngine.getRepositoryService();
	    repositoryService.createDeployment()
	      .addClasspathResource("MyProcess.bpmn")
	      .deploy();
	    //List<> repositoryService.createDeploymentQuery().list();      
	    //System.out.println("Number of process definitions: " + repositoryService.createProcessDefinitionQuery().count());
	    
	    System.out.println(processEngine.getRuntimeService().startProcessInstanceByKey("TestProcess1", "test1").getId());
	    System.out.println(processEngine.getTaskService().createTaskQuery().processInstanceBusinessKey("test1").singleResult().getName());
	    processEngine.close();
	}

}
