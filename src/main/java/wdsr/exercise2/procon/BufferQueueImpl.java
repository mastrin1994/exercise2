package wdsr.exercise2.procon;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BufferQueueImpl implements Buffer {
	private static final int QUEUE_CAPACITY = 10000;
	private BlockingQueue<Order> queue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
	
	public void submitOrder(Order order) throws InterruptedException {
		this.queue.put(order);
	}
	
	public Order consumeNextOrder() throws InterruptedException {
		return this.queue.take();
	}
}
