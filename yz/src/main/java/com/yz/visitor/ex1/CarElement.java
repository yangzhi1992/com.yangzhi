package com.yz.visitor.ex1;
/**
 * 假设定义一个汽车的复杂的类结构Car以及Car的组件Engine, Wheel，均作为CarElement的元素存在。
 * @author hy
 *
 */
public interface CarElement {
	void accept(CarElementVisitor visitor);
}