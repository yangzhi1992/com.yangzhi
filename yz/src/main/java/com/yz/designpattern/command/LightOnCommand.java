package com.yz.designpattern.command;
public class LightOnCommand implements Command {  
    Light light;  
    int level;//level用于记录上次的灯光亮度  
    public LightOnCommand(Light light) {  
        this.light = light;  
    }  
   
    public void execute() {  
        level = light.getLevel();  
        light.on();  
    }  
   
    public void undo() {//将灯光的亮度调到前一次的水平实现撤销  
        light.dim(level);  
    }  
}  