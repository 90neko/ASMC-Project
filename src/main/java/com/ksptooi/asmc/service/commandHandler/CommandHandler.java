package com.ksptooi.asmc.service.commandHandler;

import java.io.BufferedReader;
import java.io.IOException;
import com.ksptooi.asmc.entity.command.Command;
import com.ksptooi.asmc.entity.command.InputCommand;
import com.ksptooi.asmc.entity.event.CommandEvent;
import com.ksptooi.asmc.entity.event.UnknowCommandEvent;
import com.ksptooi.asmc.main.Asmc;
import com.ksptooi.asmc.message.Logger;
import com.ksptooi.asmc.service.command.CommandDataService;
import com.ksptooi.asmc.service.command.CommandParserService;
import com.ksptooi.asmc.service.command.CommandTypeScannerService;
import com.ksptooi.asmc.service.event.EventBusService;
import uk.iksp.asmc.command.exception.CommandFormatException;

public class CommandHandler implements CommandHandlerService{
	
	
	
	private CommandDataService dataService = Asmc.getCommandDataService();
	
	private CommandParserService parserService = Asmc.getCommandParserService();
	
	private CommandTypeScannerService typeScannerSevice = Asmc.getCommandTypeScannerService();

	private EventBusService eventBusService = Asmc.getEventBusService();
	
	private Logger log = Asmc.getLogger();
	
	
	public CommandHandler(){
		log.info("初始化内部组件 - Asmc命令总线");
	}
	
	
	public void commandHandler(){
			
		
		
		BufferedReader br=Asmc.getConsoleInput();
	
		String commandString = null;
		
		int failedCount = 0;
		
		
		while(true){
			
			
			if(failedCount > 3) {
				log.error("无法从数据库取到所需命令的数据.请检查数据库连接.");
				break;
			}
			
			
			try{
				
				System.out.println("");
				
				System.out.print("Terminal@"+Asmc.getUserPermissionService().getActiveUser().getUserName()+":");
				
				if(failedCount<1){
											
					commandString = br.readLine();
				
					//格式化命令
					try {
					
						commandString = parserService.format(commandString);
					
					} catch (CommandFormatException e1) {
						continue;
					}
					
				}
				
				
				
				//解析命令字符串
				InputCommand ic = parserService.parseAsInputCommand(commandString);
				
				
				Command cmd = new Command();		
				cmd.setName(ic.getName());
				
					
				cmd = dataService.query(cmd);
				
				
				
								
				//检查命令是否存在
				if(cmd == null){				
					//创建事件 - 未知命令
					
					UnknowCommandEvent unknowCommand = new UnknowCommandEvent(ic);				
					eventBusService.event(unknowCommand);				
					
					continue;
				}
			
			
				//获取可执行命令
				Command executeType = typeScannerSevice.getExecuteType(cmd);
				
				if(executeType == null) {
					log.error("已找到该命令,但其命令类型没有注册!");
					continue;
				}
				
				
				//创建事件
				CommandEvent cmdEvent = new CommandEvent(executeType);
				eventBusService.event(cmdEvent);
				
				
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
