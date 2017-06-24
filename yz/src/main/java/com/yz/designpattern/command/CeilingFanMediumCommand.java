package com.yz.designpattern.command;
public class CeilingFanMediumCommand implements Command {  
    CeilingFan ceilingFan;  
    int prevSpeed;  
    
    public CeilingFanMediumCommand(CeilingFan ceilingFan) {  
        this.ceilingFan = ceilingFan;  
    }  
   
    public void execute() {  
        prevSpeed = ceilingFan.getSpeed();  
        ceilingFan.medium();//将行为的真正执行委托给接收者，此处即ceilingFan风扇对象  
    }  
   
    public void undo() {  
        if (prevSpeed == CeilingFan.HIGH) {  
            ceilingFan.high();  
        } else if (prevSpeed == CeilingFan.MEDIUM) {  
            ceilingFan.medium();  
        } else if (prevSpeed == CeilingFan.LOW) {  
            ceilingFan.low();  
        } else if (prevSpeed == CeilingFan.OFF) {  
            ceilingFan.off();  
        }  
    }  
}  