package com.yz.designpattern.command;
public class DimmerLightOnCommand implements Command {  
    Light light;  
    int prevLevel;//记录以前的灯光亮度，撤销操作时使用  
  
    public DimmerLightOnCommand(Light light) {  
        this.light = light;  
    }  
  
    public void execute() {  
        prevLevel = light.getLevel();  
        light.dim(75);//将灯光亮度调至75%实现暗光  
    }  
  
    public void undo() {  
        light.dim(prevLevel);  
    }  
}  