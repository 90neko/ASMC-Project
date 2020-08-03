package com.ksptooi.asmc.service.commandHandler;

import java.io.BufferedReader;
import java.io.IOException;

import com.ksptooi.asmc.entity.command.Command;
import com.ksptooi.asmc.main.Asmc;
import com.ksptooi.asmc.message.Logger;
import com.ksptooi.asmc.service.command.CommandDataService;
import uk.iksp.asmc.command.exception.CommandFormatException;
import uk.iksp.asmc.command.exception.UnknowCommandTypeException;
import uk.iksp.asmc.entity.command.AsmcCommand;
import uk.iksp.asmc.entity.command.InputCommand;
import uk.iksp.asmc.event.type.CommandEvent;

public class CommandHandler implements CommandHandlerService{
	
	
	
	CommandDataService dataService = Asmc.getCommandDataService();
	
	CommandParserService parserService = Asmc.getCommandParserService();
	
	CommandTypeScannerService typeScannerSevice = Asmc.getCommandTypeScannerService();

	
	
	Logger log = Asmc.getLogger();
	
	
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
				
				if(failedCount<1) {
					
					//开始预命令输入事件
//					Asmc.getEventCreate().startPreCommandInput();
					
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
				
				
				//创建预命令事件
				if( ! Asmc.getEventCreate().startPreCommandEvent(ic)) {
					continue;
				}
				
				
				
				cmd = dataService.query(cmd);
				
				
								
				//检查命令是否存在
				if(cmd == null){				
					//创建事件 - 未知命令
					Asmc.getEventCreate().startUnknowCommandEvent(ic);	
					continue;
				}
			
			
				//获取可执行命令
				Command executeType = typeScannerSevice.getExecuteType(cmd);
				
				if(executeType == null) {
					log.error("可执行命令类型不存在!");
				}
				
				
				
				
				
				
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
