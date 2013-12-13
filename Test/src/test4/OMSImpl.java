package test4;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.task.Task;

public class OMSImpl implements OMS,JavaDelegate,TaskListener{
	
	private CRM crm = new CRMImpl(this);

	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub
		if (delegateTask.getExecution().getCurrentActivityId().equals("VerifyOrder")){
			Order o = (Order)delegateTask.getExecution().getVariable("order");
			crm.verifyOrder(o);
		};
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(execution.getCurrentActivityId());
		if(execution.getCurrentActivityId().equals("CheckNeedverification")){
			Order o = (Order)execution.getVariable("order");
			execution.setVariable("needVerification", orderNeedVerification(o));
		};
		
		if(execution.getCurrentActivityId().equals("FullfillOrder")){
			Order o = (Order)execution.getVariable("order");
			fulFillOrder(o);
		};

	}

	@Override
	public boolean orderNeedVerification(Order o) {
		// TODO Auto-generated method stub
		return o.getPrice()>100;
	}

	@Override
	public void orderVerificationResult(Order o, boolean res) {
		//String name = ProcessEngines.getProcessEngineInfos().get(0).getName();
		ProcessEngine pe= ProcessEngines.getDefaultProcessEngine();
		Task t = pe.getTaskService().createTaskQuery().processDefinitionKey("myProcess").processVariableValueEquals("orderId",o.getId()).singleResult();
		pe.getRuntimeService().setVariable(t.getExecutionId(), "verified", res);
		pe.getTaskService().complete(t.getId());
	}

	@Override
	public void fulFillOrder(Order o) {
		System.out.println("====fulfilling order=====");
		System.out.println(o.getId());
		
	}

}
