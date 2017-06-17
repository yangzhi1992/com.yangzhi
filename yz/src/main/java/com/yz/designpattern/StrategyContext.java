package com.yz.designpattern;
public class StrategyContext {
	private Strategy strategy;
	public StrategyContext(Strategy s){
		strategy = s;
	}
	public int executeStrategy(int arg1, int arg2){
		return strategy.execute(arg1, arg2);
	}
}