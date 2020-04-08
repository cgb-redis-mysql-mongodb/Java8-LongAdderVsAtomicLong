package examples;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class UsingAtomicLong {

	public static AtomicLong la = new AtomicLong();
	private static Long incrementValue;
	private static Long decrementValue;
	
	public static Runnable IncrementTask() {
		Runnable r = ()->{
			for(int i=0;i<10000;i++) {
				la.getAndIncrement();
			}
		};	
		return r;
	}
	
	public static Runnable DecrementTask() {
		Runnable r = ()->{
			for(int i=0;i<10000;i++) {
				la.getAndDecrement();
			}
		};	
		return r;
	}
	
	public static void main(String[] args) {
	    long startTime = System.currentTimeMillis();
		UsingAtomicLong.incrementValue = 5000L;
		UsingAtomicLong.decrementValue = 2000L;
		
		ExecutorService es = Executors.newFixedThreadPool(10);
		for(int i=0; i < UsingAtomicLong.incrementValue; i++) {
			es.submit(IncrementTask());
		}
		for(int i=0; i < UsingAtomicLong.decrementValue; i++) {
			es.submit(DecrementTask());
		}

		 es.shutdown();
	        try {
	        	es.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
	        } catch (InterruptedException e) {
	          
	        }
		System.out.println(la);
		long endTime = System.currentTimeMillis();
		System.out.println("It took " + (endTime - startTime) + " milliseconds");
		
	}

}
