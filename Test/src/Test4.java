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



public class Test4 {

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



		RepositoryService repositoryService = processEngine
				.getRepositoryService();
		repositoryService.createDeployment()
				.addClasspathResource("Test4.bpmn").deploy();


		RuntimeService rs = processEngine.getRuntimeService();
		int iii = 10;
		HashMap hm = new HashMap();
		test4.Order oo = new test4.Order();
		oo.setId(10);
		oo.setPrice(5010);
		hm.put("orderId", oo.getId());
		hm.put("order", oo);
		rs.startProcessInstanceByKey("myProcess", hm);

		;
		//processEngine.close();

	}

}
