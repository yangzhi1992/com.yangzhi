package com.yz.designpattern.command;
public class Light {  
    String location;  
    int level;//灯光的亮度  
  
    public Light(String location) {  
        this.location = location;  
    }  
  
    public void on() {  
        level = 100;  
        System.out.println("Light is on");  
    }  
  
    public void off() {  
        level = 0;  
        System.out.println("Light is off");  
    }  
    //调整灯光的亮度  
    public void dim(int level) {  
        this.level = level;  
        if (level == 0) {  
            off();  
        }  
        else {  
            System.out.println("Light is dimmed to " + level + "%");  
        }  
    }  
   //获取灯光的亮度  
    public int getLevel() {  
        return level;  
    }  
}  