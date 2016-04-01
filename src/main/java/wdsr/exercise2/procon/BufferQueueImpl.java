package wdsr.exercise2.procon;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Task: implement Exchange interface using one of *Queue classes from java.util.concurrent package.
 */
public class BufferQueueImpl implements Buffer {

	private BlockingQueue<Order> queue = new ArrayBlockingQueue<>(20000);
	
	
	public void submitOrder(Order order) throws InterruptedException {	
		queue.put(order);
		 	
	}
	
	public Order consumeNextOrder() throws InterruptedException {
		
		return queue.take();
	}

	
}
