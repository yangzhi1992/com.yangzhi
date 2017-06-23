package com.yz.designpattern.visitor.ex1;
public class Wheel implements CarElement{
	private String name;
	Wheel(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void accept(CarElementVisitor visitor) {
		visitor.visit(this);
	}
}