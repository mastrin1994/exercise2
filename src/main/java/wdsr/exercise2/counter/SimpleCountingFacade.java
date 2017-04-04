package wdsr.exercise2.counter;

public class SimpleCountingFacade implements CountingFacade {
	private final BusinessService businessService;
	
	private int invocationCounter;
	
	public SimpleCountingFacade(BusinessService businessService) {
		this.businessService = businessService;
	}
	
	public void countAndInvoke() {
		synchronized(this){
			invocationCounter++;
		}
		businessService.executeAction();
	}
	
	public int getCount() {
		return invocationCounter;
	}
}
