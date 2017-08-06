package com.yz.multithread.current.ex1;

import java.util.HashMap;
import java.util.Map;

public class Work{
	private int type;
	private Map<Integer,Integer> rates;
	
	public void doWork(String threadPoolName) throws InterruptedException{
		Integer rate = rates.get(type);
//		System.out.println(threadPoolName+"->"+rate);
		if(rate == 10 && threadPoolName.equals("es2")){
			System.out.println("1");
		}
	}
	 
	public Work(Map<Integer,Integer> rates) {
		super();
		this.rates = rates;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	public Map<Integer, Integer> getRates() {
		return rates;
	}

	public void setRates(Map<Integer, Integer> rates) {
		this.rates = rates;
	}
	
}
