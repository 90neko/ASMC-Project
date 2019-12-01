package uk.iksp.asmc.event.type;

import uk.iksp.asmc.entity.command.AsmcCommand;
import uk.iksp.asmc.event.basic.AsmcEvent;

public class CommandEvent extends AsmcEvent{

	
	private final String eventName = "CommandEvent";
	
	private AsmcCommand asmcCommand=null;

	private boolean isCancel = false;
	
	
	public CommandEvent(AsmcCommand CommandType){
		this.asmcCommand=CommandType;
	}
	
	
//	//立即完成事件
//	public void commitEvent(){
//			
//		//如果此事件已经被前一个插件完成 则不执行任何操作
//		if(isCommit == true){
//			return;
//		}
//		
//		commandType.ExecuteOfType(commandEntity);
//		
//		this.isCommit = true;
//		
//	}
//	
//	
//	public boolean isCommit(){
//		return isCommit;
//	}
	

	public boolean isCancel() {
		return isCancel;
	}

	public void setCancel(boolean isCancel) {
		
		if(isCancel == false) {
			return;
		}
		
		
		this.isCancel = isCancel;
	}

	
	
	public String getEventName() {
		return eventName;
	}


	@Override
	public void commit() {
		
		//判断是否被取消
		if(this.isCancel()){
			return;
		}
		
		//提交事件
		this.getAsmcCommand().getCommandType().ExecuteOfType(this.asmcCommand);
		
	}


	public AsmcCommand getAsmcCommand() {
		return asmcCommand;
	}

	
	
}
