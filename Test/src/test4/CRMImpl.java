package test4;

public class CRMImpl implements CRM,Runnable {
	
	private OMS oms;
	private volatile Order oo;
	
	public CRMImpl(OMS oo){
		oms = oo;
	}
	@Override
	public void verifyOrder(Order o) {
		// TODO Auto-generated method stub
		oo = o;
		Thread t = new Thread(this);
		t.start();
		
	}
	@Override
	public void run()  {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1000);
			oms.orderVerificationResult(oo, oo.getPrice()>1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public OMS getOms() {
		return oms;
	}
	public void setOms(OMS oms) {
		this.oms = oms;
	}
	
	

}
