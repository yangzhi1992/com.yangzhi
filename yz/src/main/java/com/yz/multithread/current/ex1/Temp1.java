package com.yz.multithread.current.ex1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Temp1 implements Runnable{
	private Work work;
	
	public Temp1(Work work) {
		super();
		this.work = work;
	}

	@Override
	public void run() {
		final ExecutorService es1 = Executors.newFixedThreadPool(100);
		for(int i=0;i<10000000;i++){
			es1.execute(new Runnable(){
				@Override
				public void run() {
					work.setType(1);
					
					try {
						work.doWork("es1");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
	}
}
