package com.yz.designpattern.command;
public class RemoteControlWithUndo {  
    Command[] onCommands;//对应多个开按钮  
    Command[] offCommands;//对应多个关按钮  
    Command undoCommand;//对应撤销按钮  
   
    public RemoteControlWithUndo() {  
        onCommands = new Command[7];  
        offCommands = new Command[7];  
   
        Command noCommand = new NoCommand();  
        for(int i=0;i<7;i++) {  
            onCommands[i] = noCommand;//默认赋值为空命令，什么事也不做  
            offCommands[i] = noCommand;  
        }  
        undoCommand = noCommand;  
    }  
    
    public void setCommand(int slot, Command onCommand, Command offCommand) { 
        onCommands[slot] = onCommand;  
        offCommands[slot] = offCommand;  
    }  
   
    //当编号为第slot的开On按钮按下时执行命令  
    public void onButtonWasPushed(int slot) {  
        onCommands[slot].execute();  
        undoCommand = onCommands[slot];//记录最后执行的命令  
    }  
   
    public void offButtonWasPushed(int slot) {  
        offCommands[slot].execute();  
        undoCommand = offCommands[slot];  
    }  
   
    public void undoButtonWasPushed() {  
        undoCommand.undo();  
    }  
    
    public String toString() {  
        String sb = new String();  
        sb = "\n------ Remote Control -------\n";  
        for (int i = 0; i < onCommands.length; i++) {  
        	sb = sb +"[slot " + i + "] " + onCommands[i].getClass().getName()  
                + "    " + offCommands[i].getClass().getName() + "\n";  
        }  
          
        sb = sb +"[undo] " + undoCommand.getClass().getName() + "\n";  
        return sb;  
    }  
}  