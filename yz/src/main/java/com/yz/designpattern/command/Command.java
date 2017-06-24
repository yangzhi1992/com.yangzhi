package com.yz.designpattern.command;
public interface Command {  
    public void execute();  
    public void undo();//实现撤销  
}  