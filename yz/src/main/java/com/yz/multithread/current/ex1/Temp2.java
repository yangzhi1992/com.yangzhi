package com.yz.multithread.current.ex1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Temp2 implements Runnable{
	private Work work;
	
	public Temp2(Work work) {
		super();
		this.work = work;
	}

	@Override
	public void run() {
		final ExecutorService es2 = Executors.newFixedThreadPool(100);
		for(int i=0;i<10000000;i++){
			es2.execute(new Runnable(){
				@Override
				public void run() {
					work.setType(2);
					
					try {
						work.doWork("es2");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
}