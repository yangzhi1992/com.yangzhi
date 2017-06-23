package com.yz.designpattern.visitor.ex1;
public class Engine implements CarElement{
	public void accept(CarElementVisitor visitor) {
		visitor.visit(this);
	}
}