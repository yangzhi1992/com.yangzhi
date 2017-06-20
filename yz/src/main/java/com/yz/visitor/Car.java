package com.yz.visitor;
public class Car implements CarElement{
	CarElement[] elements;
	public CarElement[] getElements(){
		return elements.clone();
	}
	public void accept(CarElementVisitor visitor) {
		visitor.visit(this);
	}
	Car(){
		this.elements = new CarElement[]{
			new Engine(), 
			new Wheel("front two wheels"), 
			new Wheel("back two wheels")
		};
	}
}