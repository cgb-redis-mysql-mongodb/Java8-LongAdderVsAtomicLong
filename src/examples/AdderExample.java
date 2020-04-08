package examples;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

public class AdderExample {

	public static LongAdder la = new LongAdder();
	private static Long incrementValue;
	private static Long decrementValue;
	
	public static Runnable IncrementTask() {
		Runnable r = ()->{
			for(int i=0;i<10000;i++) {
				la.increment();
			}
		};	
		return r;
	}
	
	public static Runnable DecrementTask() {
		Runnable r = ()->{
			for(int i=0;i<10000;i++) {
				la.decrement();
			}
		};	
		return r;
	}
	
	public static void main(String[] args) {
	    long startTime = System.currentTimeMillis();
		AdderExample.incrementValue = 5000L;
		AdderExample.decrementValue = 2000L;
		
		ExecutorService es = Executors.newFixedThreadPool(10);
		for(int i=0; i < AdderExample.incrementValue; i++) {
			es.submit(IncrementTask());
		}
		for(int i=0; i < AdderExample.decrementValue; i++) {
			es.submit(DecrementTask());
		}

		 es.shutdown();
	        try {
	        	es.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
	        } catch (InterruptedException e) {
	          
	        }
		System.out.println(la.sum());
		long endTime = System.currentTimeMillis();
		System.out.println("It took " + (endTime - startTime) + " milliseconds");
		
	}

}
