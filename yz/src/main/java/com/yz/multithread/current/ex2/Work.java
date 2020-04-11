package com.yz.multithread.current.ex2;

import java.util.Map;

public class Work{
	private int type;
	private Map<Integer,Integer> rates;
	
	public Integer doWork(String threadPoolName,Param p) throws InterruptedException{
		Integer rate = rates.get(p.getType());
		if(rate == 10 && threadPoolName.equals("es2")){
			System.out.println("1");
		}
		return p.getType();
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
