package com.yz.multithread.current.ex2;

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
					Param p = new Param();
					p.setType(2);
					try {
						Integer str = work.doWork("es2",p);
						if(str != 2){
							System.out.println("false");
						}
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
}
