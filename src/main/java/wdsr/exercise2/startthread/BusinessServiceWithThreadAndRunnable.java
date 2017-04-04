package wdsr.exercise2.startthread;

public class BusinessServiceWithThreadAndRunnable {
	private NumericHelper helper;
	
	public BusinessServiceWithThreadAndRunnable(NumericHelper helper) {
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
		
		Thread thread = new Thread(runnable);
		thread.start();
	}
}