package com.yz.designpattern;
public class StrategySubstract implements Strategy{
	public int execute(int arg1, int arg2) {
		return arg1 - arg2;
	}
}