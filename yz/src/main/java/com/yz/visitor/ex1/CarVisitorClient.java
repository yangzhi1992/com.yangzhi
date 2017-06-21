package com.yz.visitor.ex1;
public class CarVisitorClient {
	public static void main(String args[]){
		Car car = new Car();
		car.accept(new Driver());
	}
}