package wdsr.exercise2.procon;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Task: implement Exchange interface without using any *Queue classes from java.util.concurrent package.
 * Any combination of "synchronized", *Lock, *Semaphore, *Condition, *Barrier, *Latch is allowed.
 */
public class BufferManualImpl implements Buffer {
	
	   final Lock lock = new ReentrantLock();
	   final Condition notFull  = lock.newCondition(); 
	   final Condition notEmpty = lock.newCondition(); 
	   
	   private List<Order> orders = new ArrayList<>(1000);
	  
	   int putptr, takeptr, count;
	
	public void submitOrder(Order order) throws InterruptedException {
		lock.lock();
	     try {
	       while (orders.size() == 1000)
	         notFull.await();
	       
	       orders.add(order);
	       notEmpty.signal();
	     } finally {
	       lock.unlock();
	     }
	}
	
	public Order consumeNextOrder() throws InterruptedException {
		 Order order = null;
		 lock.lock();
	     try {
	       while (orders.isEmpty())
	         notEmpty.await();
	       
	       order = orders.remove(0);
	       notFull.signal();
	       
	       return order;
	     } finally {
	       lock.unlock();
	     }
	}
	

		 
}
