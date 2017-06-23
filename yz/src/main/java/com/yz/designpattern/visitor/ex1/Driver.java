package com.yz.designpattern.visitor.ex1;
public class Driver implements CarElementVisitor{
	public void visit(Wheel wheel) {
		System.out.println("Rolling " + wheel.getName()); 
	}
	public void visit(Engine engine) {
		System.out.println("Starting engine");
	}
	public void visit(Car car) {
		System.out.println("I am driving");
		for (CarElement ele:car.getElements()){
			ele.accept(this);
		} 
		System.out.println("going...");
	}
}