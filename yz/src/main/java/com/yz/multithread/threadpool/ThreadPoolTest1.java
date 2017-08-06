package com.yz.multithread.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 三个线程池方法一样，只写一个
 * @author hy
 *
 */
public class ThreadPoolTest1 implements Callable<String>{
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		final ThreadPoolTest1 tpt = new ThreadPoolTest1();
		ExecutorService es1 = Executors.newFixedThreadPool(100);
		ExecutorService es2 = Executors.newCachedThreadPool();
		ExecutorService es3 = Executors.newSingleThreadExecutor();
		
		//execute 表示往线程池添加线程，有可能会立即运行，也有可能不会。无法预知线程何时开始，何时线束
//		for(int i = 0;i<100;i++){
//			final int j = i;
//			Runnable r = new Runnable(){
//
//				@Override
//				public void run() {
//					tpt.say(j);
//				}
//				
//			};
//			es1.execute(r);
//		}
		
		//submit
		for(int i = 0;i<1;i++){
			final int j = i;
			Runnable r = new Runnable(){

				@Override
				public void run() {
					tpt.say(j);
				}
				
			};
			Future f = es1.submit(tpt);
			System.out.println(f.get());
		}
	}
	
	private int say(Integer j){
		System.out.println(Thread.currentThread().getName()+"----->"+j);
		return j;
	}

	@Override
	public String call() throws Exception {
		System.out.println(Thread.currentThread().getName());
		return "ss";
	}
}
