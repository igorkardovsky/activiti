package test3;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.delegate.TaskListener;

public class JavaExpression implements JavaDelegate,TaskListener {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
	System.out.println("=========execution========");
	System.out.println(	"ID:"+execution.getId());
	System.out.println(	"EventName:"+execution.getEventName());
	System.out.println(	"ActivityID:"+execution.getCurrentActivityId());
	System.out.println(	"ActivityName:"+execution.getCurrentActivityName());
	System.out.println(	"ProcessInstance:"+execution.getProcessInstanceId());
	
	}

	@Override
	public void notify(DelegateTask delegateTask) {
		System.out.println("===========delegate==========");
		System.out.println(	"ID:"+delegateTask.getId());
		System.out.println(	"EventName:"+delegateTask.getEventName());
		System.out.println(	"ExecutionId:"+delegateTask.getExecutionId());
		System.out.println(	"Name:"+delegateTask.getName());
		System.out.println(	"ActivityID:"+delegateTask.getExecution().getCurrentActivityId());
		System.out.println(	"ActivityName:"+delegateTask.getExecution().getCurrentActivityName());
		System.out.println(	"ProcessInstance:"+delegateTask.getProcessInstanceId());
		
		if(delegateTask.getEventName().equals("create") && delegateTask.getExecution().getCurrentActivityId().equals("VerifyOrder")){
			delegateTask.getExecution().getEngineServices().getTaskService().complete(delegateTask.getId());
			
		}
		
	}
	

}
