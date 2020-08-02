package uk.iksp.asmc.command.handler;

import java.io.BufferedReader;
import java.io.IOException;
import com.ksptooi.ASMC.Main.Asmc;
import com.ksptooi.ASMC.Message.Logger;
import uk.iksp.asmc.command.exception.CommandFormatException;
import uk.iksp.asmc.command.exception.UnknowCommandTypeException;
import uk.iksp.asmc.entity.command.AsmcCommand;
import uk.iksp.asmc.entity.command.InputCommand;
import uk.iksp.asmc.event.type.CommandEvent;
import uk.iksp.asmc.services.CommandService;

public class CommandHandler{
	
	
	CommandFormat cformat = null;
	
	Logger log = Asmc.getLogger();
	
	
	public CommandHandler(){
		log.info("初始化内部组件 - Asmc命令总线");
		this.cformat = new CommandFormat();
	}
	
	
	public void ExecuteCommand() throws IOException{
			
		BufferedReader br=Asmc.getConsoleInput();
		
		CommandService service = Asmc.getCommandService();
		
		String commandString = null;
		
		int failedCount = 0;
		
		
		while(true){
			
			
			if(failedCount > 3) {
				log.error("无法从数据库取到所需命令的数据.请检查数据库连接.");
				break;
			}
			
			
			try{
				
				System.out.println("");
				
				if(failedCount<1) {
					
					//开始预命令输入事件
					Asmc.getEventCreate().startPreCommandInput();
					
					commandString = br.readLine();
				
					//格式化命令
					try {
					
						commandString = cformat.format(commandString);
					
					} catch (CommandFormatException e1) {
						continue;
					}
					
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
				
				failedCount = 0;
			
			}catch(IOException fnfe) {
				log.error("执行命令时发生错误,目标文件已经不存在或不可执行.!");
			}catch (Exception e){
//				e.printStackTrace();
				log.warn("命令执行失败.");
				failedCount ++;
			}
			
		}
			
		
	}
	
	
	
	
}
