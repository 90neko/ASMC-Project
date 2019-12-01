package uk.iksp.asmc.command.handler;

import java.io.BufferedReader;
import java.io.IOException;

import com.ksptooi.ASMC.Main.Asmc;
import com.ksptooi.ASMC.Message.MessageManager;
import uk.iksp.asmc.command.exception.CommandFormatException;
import uk.iksp.asmc.command.exception.UnknowCommandTypeException;
import uk.iksp.asmc.command.services.CommandFormat;
import uk.iksp.asmc.command.services.CommandService;
import uk.iksp.asmc.entity.command.AsmcCommand;
import uk.iksp.asmc.entity.command.InputCommand;
import uk.iksp.asmc.event.type.CommandEvent;

public class CommandHandler{
	
	
	MessageManager msg = Asmc.getMessageManager();
	
	CommandFormat cformat = null;
	
	public CommandHandler(){
		msg.sendSysMessage("初始化内部组件 - Asmc命令总线");
		this.cformat = new CommandFormat();
	}
	
	
	public void ExecuteCommand() throws IOException{
		
		
		BufferedReader br=Asmc.getConsoleInput();
		
		CommandService service = Asmc.getCommandService();
		
		while(true){
			
			try{
				

				System.out.println("");
			
				//开始预命令输入事件
				Asmc.getEventCreate().startPreCommandInput();
				
				String commandString = br.readLine();
			
				//格式化命令
				try {
				
					commandString = cformat.format(commandString);
				
				} catch (CommandFormatException e1) {
					continue;
				}
				
			
				//解析命令字符串
				InputCommand ic = cformat.parseAsInputCommand(commandString);
				
			
				//创建预命令事件
				if( ! Asmc.getEventCreate().startPreCommandEvent(ic)) {
					continue;
				}
			
						
				//检查命令是否存在
				if(!service.isExistsCommand(ic.getName())){				
					//创建事件 - 未知命令
					Asmc.getEventCreate().startUnknowCommandEvent(ic);	
					continue;
				}
			
			
				//获取Asmc命令
				AsmcCommand asmcCommand = null;
			
				try {	
					
					asmcCommand = service.getAsmcCommand(ic);
				
				} catch (UnknowCommandTypeException e) {
					//创建事件 - 未知命令
					Asmc.getEventCreate().startUnknowCommandEvent(ic);	
					continue;
				}
				

		
				//创建事件
				CommandEvent event=new CommandEvent(asmcCommand);
			
				//执行事件
				Asmc.getEventCreate().startCommandEvent(event);
			
			
			}catch (Exception e){
				e.printStackTrace();
				msg.sendErrorMessage("命令执行器出现错误!");
			}
			
		}
			
		
	}
	
	
	
	
}
