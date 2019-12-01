package uk.iksp.asmc.event.type;

import com.ksptooi.ASMC.Main.Asmc;

import uk.iksp.asmc.entity.command.InputCommand;
import uk.iksp.asmc.event.basic.AsmcEvent;

public class UnknowCommandEvent extends AsmcEvent{

	
	/**
	 * 执行一个未知的ASMC命令,或未知的ASMC插件命令,将会唤醒这个事件
	 */
	private InputCommand inputCommand = null;
	
	private String Message = null;
	
	public UnknowCommandEvent(InputCommand PreCommand){
			
		this.inputCommand=PreCommand;		
		
		this.Message = "不是有效的asmc命令";
		
	}
	
	//获取输入的字符串
	
	
	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	@Override
	public void commit() {
		
		//判断是否取消
		if(this.isCancel()){		
			return;		
		}
		
		
		//发送一条错误消息
		Asmc.getMessageManager().sendWarningMessage("'"+this.inputCommand.getName()+"'"+this.getMessage());
		
	}
	
	public InputCommand getInputCommand() {
		return inputCommand;
	}
	
	
	
}
