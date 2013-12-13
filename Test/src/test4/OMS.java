package test4;

public interface OMS {
	
	public boolean orderNeedVerification(Order o);
	public void orderVerificationResult(Order o, boolean res);
	public void fulFillOrder(Order o);
}
