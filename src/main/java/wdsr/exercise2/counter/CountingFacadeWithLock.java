package wdsr.exercise2.counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CountingFacadeWithLock implements CountingFacade {
	private final BusinessService businessService;
	private final Lock lock = new ReentrantLock();
	private int invocationCounter;
	
	public CountingFacadeWithLock(BusinessService businessService) {
		this.businessService = businessService;
	}
		
	public void countAndInvoke() {
		this.lock.lock();
		try{
			invocationCounter++;
			businessService.executeAction();
		}
        finally { 
        	lock.unlock(); 
        }
	}
	
	public int getCount() {
		return invocationCounter;
	}
}
