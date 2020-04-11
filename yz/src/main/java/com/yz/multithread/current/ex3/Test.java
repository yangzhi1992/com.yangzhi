package com.yz.multithread.current.ex3;

import java.util.HashMap;
import java.util.Map;

public class Test {
	public static void main(String[] args) {
		Map<Integer,Integer> rates = new HashMap();
		for(int i=0;i<10000;i++){
			rates.put(i, i);
		}
		final Work work = new Work(rates);
		Runnable r1 = new Temp1(work);
		
		Thread t1 = new Thread(r1);
		
		t1.start();
	}
}
