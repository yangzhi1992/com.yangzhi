package com.yz.visitor;
/**
 * 每个CarElement的实现类均通过accept()的方法来回调visitor并访问自身。
 * 接下来即可定义一个visitor类，比如Driver。为了使得visitor具有可扩展可延续性，可以将Driver抽象出一个接口
 * 来，之后可以根据不同的需求来定义不同的Driver。观察Driver类是如何将自己作为参数注入Car类结构的。
 * @author hy
 *
 */
interface CarElementVisitor {
	void visit(Wheel wheel);
	void visit(Engine engine);
	void visit(Car car);
}