package com.yz.designpattern;

public class StrategyClient {
	public static void main(String args[]){
		int r1, r2, r3;
		StrategyContext sm = new StrategyContext(new StrategyAdd());
		r1 = sm.executeStrategy(2, 4);
		sm = new StrategyContext(new StrategySubstract());
		r2 = sm.executeStrategy(2, 4);
		sm = new StrategyContext(new StrategyMultiply());
		r3 = sm.executeStrategy(2, 4);
		System.out.println("r1=" + r1);
		System.out.println("r2=" + r2);
		System.out.println("r3=" + r3);
	}
}