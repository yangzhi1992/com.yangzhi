package com.yz.multithread.current.ex3;

import java.util.HashMap;
import java.util.Map;

public class Work{
	private int type;
	private Map<Integer,Integer> rates;
	
	public Integer doWork(int j) throws InterruptedException{
		Integer rate = rates.get(j);
		
		return rate;
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
