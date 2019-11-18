package uk.iksp.asmc.event.type;

public class UnknowCommandEvent extends BasicEvent{

	
	/**
	 * 执行一个未知的ASMC命令,或未知的ASMC插件命令,将会唤醒这个事件
	 */
	private String PreCommand = null;
	
	private String Message = null;
	
	public UnknowCommandEvent(String PreCommand){
			
		this.PreCommand=PreCommand;		
		
		this.Message = "不是有效的asmc命令";
		
	}
	
	//获取输入的字符串
	public String getPreCommand(){
		return this.PreCommand;
	}
	
	
	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}
	
	
	
	
	
	
	
	
}
