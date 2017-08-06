package com.yz.multithread.current.ex2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
	public static void main(String[] args) {
		Map<Integer,Integer> rates = new HashMap();
		rates.put(1, 10);
		rates.put(2, 20);
		final Work work = new Work(rates);
		Runnable r1 = new Temp1(work);
		Runnable r2 = new Temp2(work);
		
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		
		t1.start();
		t2.start();
	}
}
