package com.yz.designpattern.command;
public class RemoteLoader {  
   
    public static void main(String[] args) {  
        RemoteControlWithUndo remoteControl = new RemoteControlWithUndo();  
   
        Light livingRoomLight = new Light("Living Room");  
   
        LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);  
        LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);   
        DimmerLightOnCommand dimmerLightOnCommand=new DimmerLightOnCommand(livingRoomLight);  
        DimmerLightOffCommand dimmerLightOffCommand=new DimmerLightOffCommand(livingRoomLight);  
          
        CeilingFan ceilingFan = new CeilingFan("Living Room");  
     
        CeilingFanHighCommand ceilingFanHigh = new CeilingFanHighCommand(ceilingFan);  
        CeilingFanMediumCommand ceilingFanMedium = new CeilingFanMediumCommand(ceilingFan);  
        CeilingFanLowCommand ceilingFanLow =new CeilingFanLowCommand(ceilingFan);  
        CeilingFanOffCommand ceilingFanOff = new CeilingFanOffCommand(ceilingFan);  
    
        remoteControl.setCommand(0, livingRoomLightOn, livingRoomLightOff);  
        remoteControl.setCommand(1, dimmerLightOnCommand, dimmerLightOffCommand);  
        remoteControl.setCommand(2, ceilingFanHigh, ceilingFanOff);  
        remoteControl.setCommand(3, ceilingFanMedium, ceilingFanOff);  
        remoteControl.setCommand(4, ceilingFanLow, ceilingFanOff);  
  
        remoteControl.onButtonWasPushed(1);  
        remoteControl.onButtonWasPushed(3);  
        remoteControl.onButtonWasPushed(2);  
        remoteControl.offButtonWasPushed(3);  
          
        remoteControl.undoButtonWasPushed();  
    }  
}  