package com.ksptooi.ASMC.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.ksptooi.ASMC.Command.CommandTools;
import com.ksptooi.ASMC.Command.Command_cmd;
import com.ksptooi.ASMC.Data.CommandManager;
import com.ksptooi.ASMC.Entity.CommandEntity;
import com.ksptooi.ASMC.Message.MessageManager;
import com.ksptooi.ASMC.event.CommandEvent;

public class CommandHandler{
	
	
	
	public void ExecuteCommand() throws IOException{
		
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		CommandManager cm=ASMC.getCommandManager();
		MessageManager msg=new MessageManager();
		
		while(true){
			
			
			System.out.println("");
			
			System.out.print("ASMC@Normal:");
			
			String PreCommand=br.readLine();
			
			String Command=null;
			
			CommandEntity ce=null;
			
			//��������Ԥ����	
			if(PreCommand.contains(" ")|PreCommand.equals("")){
				continue;
			}
			
			Command = PreCommand.trim().split(">")[0];
			
			
			//Ԥ���� - end
			
			
			//��������Ƿ����
			if(!cm.isExistsCmd(Command)){
				msg.sendWarningMessage("'"+Command+"'"+"������Ч��ASMC����");
				continue;
			}
			
			ce=cm.getCommandByName(Command);
			
			
			//��ѯ��������
			Command_cmd CT= CommandTools.getType(ce.getType());

			
			if(CT == null){
				msg.sendWarningMessage("'"+Command+"'"+"������Ч��ASMC����");
				continue;
			}		


			ce.setPreCommand(PreCommand);
			
//			//������������ִ������
//			CT.ExecuteOfType(ce);
			
			//�����¼�
			
			CommandEvent cee=new CommandEvent();
			
			cee.setCommandType(CT);
			cee.setCommandEntity(ce);
			
			ASMC.getEventmanager().startCommandEvent(cee);

			
		}
			
		
	}
	
	
	
	
}
