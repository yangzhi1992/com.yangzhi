package com.yz.designpattern.visitor.ex2;

import java.util.Iterator;
import java.util.List;

public class ConcreteVisitor implements Visitor{
	//在本方法中,我们实现了对Collection的元素的成功访问
	public void visitCollection(List collection) {
		Iterator iterator = collection.iterator();
		while (iterator.hasNext()) {
			Object o = iterator.next();
			if (o instanceof Visitable)
			((Visitable)o).accept(this);
		} 
	}
	
	public void visitString(StringElement stringE) {
		System.out.println("'"+stringE.getValue()+"'");
	} 
	public void visitFloat(FloatElement floatE){
		System.out.println(floatE.getValue().toString()+"f");
	}
}