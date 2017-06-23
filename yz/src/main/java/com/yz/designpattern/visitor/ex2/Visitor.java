package com.yz.designpattern.visitor.ex2;

import java.util.List;

public interface Visitor{
	public void visitString(StringElement stringE);
	public void visitFloat(FloatElement floatE);
	public void visitCollection(List collection);
}