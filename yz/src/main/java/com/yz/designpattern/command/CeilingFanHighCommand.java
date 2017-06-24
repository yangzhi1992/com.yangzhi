package com.yz.designpattern.command;
public class CeilingFanHighCommand implements Command {  
    CeilingFan ceilingFan;  
    int prevSpeed;//记录以前的转速，用于撤销操作（0时表示以前的状态是：关）  
    
    public CeilingFanHighCommand(CeilingFan ceilingFan) {  
        this.ceilingFan = ceilingFan;  
    }  
   
    public void execute() {  
        prevSpeed = ceilingFan.getSpeed();  
        ceilingFan.high();  
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