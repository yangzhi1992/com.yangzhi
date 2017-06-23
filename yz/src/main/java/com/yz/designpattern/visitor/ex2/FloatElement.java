package com.yz.designpattern.visitor.ex2;
public class FloatElement implements Visitable{
	private Float value;
	public FloatElement(Float value) {
		this.value = value;
	}

	public Float getValue(){
		return value;
	}
	//定义accept的具体内容 这里是很简单的一句调用
	public void accept(Visitor visitor) {
		visitor.visitFloat(this);
	}
}