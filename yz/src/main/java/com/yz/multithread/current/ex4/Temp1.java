package com.yz.multithread.current.ex4;

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
		for(int i=0;i<10000;i++){
			final int j = i;
			es1.execute(new Runnable(){
				@Override
				public void run() {
					try {
						int r = work.doWork(j);
						if(r != j){
							System.out.println(r+"->"+j);
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
	}
}
