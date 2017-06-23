package com.yz.designpattern.visitor.ex1;
public class CarVisitorClient {
	public static void main(String args[]){
		Car car = new Car();
		car.accept(new Driver());
	}
}