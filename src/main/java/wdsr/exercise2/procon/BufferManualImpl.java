package wdsr.exercise2.procon;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BufferManualImpl implements Buffer {
	private static final int ORDERS_CAPACITY = 10000;
	private final Lock lock = new ReentrantLock();
	private final Condition notFull  = lock.newCondition();
	private final Condition notEmpty = lock.newCondition();
	
	private List<Order> orders = new ArrayList<>(ORDERS_CAPACITY);
	
	public void submitOrder(Order order) throws InterruptedException {
		this.lock.lock();
		try{
			while(this.orders.size() == ORDERS_CAPACITY){
				this.notFull.await();
			}
			this.orders.add(order);
		    this.notEmpty.signal();
		} finally {
			this.lock.unlock();
		}
	}
	
	public Order consumeNextOrder() throws InterruptedException {
		Order newConsumeOrder = null;
		this.lock.lock();
		try{
			while(this.orders.isEmpty()){
				this.notEmpty.await();
			}
			newConsumeOrder = this.orders.remove(0);
		    this.notFull.signal();
		    return newConsumeOrder;
		} finally {
			this.lock.unlock();
		}
	}
}
