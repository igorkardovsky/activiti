import java.util.HashMap;
import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;

public class Test3 {

	public static void main(String[] args) {
		System.setProperty("org.slf4j.simpleLogger.log.org.activiti.engine.impl.pvm.runtime","debug");
		ProcessEngine processEngine = ProcessEngineConfiguration
				.createStandaloneInMemProcessEngineConfiguration()
				.setDatabaseSchemaUpdate(
						ProcessEngineConfiguration.DB_SCHEMA_UPDATE_FALSE)
				.setJdbcUrl("jdbc:h2:mem:my-own-db;DB_CLOSE_DELAY=1000")
				.setDatabaseSchemaUpdate("true").setJobExecutorActivate(true)
				.buildProcessEngine();

		IdentityService is = processEngine.getIdentityService();

		List<User> users = is.createUserQuery().list();
		for (User u : users) {
			System.out.println(u.getId());
		}

		RepositoryService repositoryService = processEngine
				.getRepositoryService();
		repositoryService.createDeployment()
				.addClasspathResource("Test3.bpmn").deploy();
		List<Deployment> ds = repositoryService.createDeploymentQuery().list();
		for (Deployment d : ds) {
			System.out.println("======================");
			System.out.println("Name:" + d.getName());
			System.out.println("Id:" + d.getId());
			System.out.println("Category:" + d.getCategory());
		}
		;

		System.out.println("=======Processes============");

		List<ProcessDefinition> ps = repositoryService
				.createProcessDefinitionQuery().list();

		for (ProcessDefinition p : ps) {
			System.out.println("======================");
			System.out.println("Name:" + p.getName());
			System.out.println("Id:" + p.getId());
			System.out.println("Resource Name:" + p.getResourceName());
			System.out.println("Key:" + p.getKey());
			System.out.println("Version:" + p.getVersion());
		}
		;

		ProcessDefinition p = repositoryService.createProcessDefinitionQuery()
				.processDefinitionKey("test3processId").latestVersion()
				.singleResult();
		System.out.println("======================");
		System.out.println("Name:" + p.getName());
		System.out.println("Id:" + p.getId());
		System.out.println("Resource Name:" + p.getResourceName());
		System.out.println("Key:" + p.getKey());

		RuntimeService rs = processEngine.getRuntimeService();
		int iii = 10;
		HashMap hm = new HashMap();
		// hm.put("orderId", iii);
		// rs.startProcessInstanceByKey("myProcess", hm);
		rs.startProcessInstanceByKey("test3processId");
		System.out.println("=======Process Instance============");
		List<ProcessInstance> pis = rs.createProcessInstanceQuery().list();
		for (ProcessInstance pi : pis) {
			System.out.println("======================");
			System.out.println("ActivitiId:" + pi.getActivityId());
			System.out.println("Busines Key:" + pi.getBusinessKey());
			System.out.println("Id:" + pi.getId());
			//System.out.println(pi.get);
			System.out
					.println("ProcessInstanceId:" + pi.getProcessInstanceId());
		}
		;
		//processEngine.close();

	}
}
