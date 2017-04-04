package wdsr.exercise2.startthread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BusinessServiceWithExecutor {
	private ExecutorService executorService;
	private NumericHelper helper;
	
	public BusinessServiceWithExecutor(NumericHelper helper) {
		this.executorService = Executors.newCachedThreadPool();
		this.helper = helper;
	}

	public void computeFibonacci(int n, FibonacciCallback callback) {
		
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				long value = helper.findFibonacciValue(n);
				callback.fibonacciComputed(value);
			}
		};
		
		this.executorService.execute(runnable);
	}
}
