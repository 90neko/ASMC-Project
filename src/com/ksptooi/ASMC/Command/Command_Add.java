package com.ksptooi.ASMC.Command;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.ksptooi.ASMC.Entity.CommandEntity;
import com.ksptooi.ASMC.Main.ASMC;
import com.ksptooi.ASMC.Message.MessageManager;

public class Command_Add extends Command_cmd{

	
	
	@Override
	public void ExecuteOfType(CommandEntity ce) {
		

		
		
		String Content="";
		
		String[] ContentM1=null;
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		MessageManager msg=ASMC.getMessageManager();
		
		
		msg.sendSysMessage("������:����,��ַ,����,����");
		
		
		try {
			CommandEntity addCE=new CommandEntity();
			
			Content=br.readLine();
			
			if(Content.trim().equals("")){
				return;
			}
			
			
			ContentM1=Content.split(",");
			
			
			addCE.setName(ContentM1[0]);
			addCE.setPath(ContentM1[1]);
			addCE.setType(ContentM1[2]);
			try{
				addCE.setTitle(ContentM1[3]);
			}catch(Exception e){
				addCE.setTitle("-");
			}
			
			addCE.setCCA(ASMC.getUserManager().getActiveUser().getAccount());
			
			if(ASMC.getCommandManager().isExistsCmd(addCE.getName())){
				msg.sendErrorMessage("���ʧ��,�Ѱ���������.");
				return;
			}
			
			if(CommandTools.getType(addCE.getType()) == null){
				return;
			}
			
			
			ASMC.getCommandManager().addCommand(addCE);
			
			
		} catch (Exception e) {
			msg.sendWarningMessage("������!");
		}
		
		
	}
	
	

	@Override
	public Command_cmd getThis() {
		// TODO �Զ����ɵķ������
		return this;
	}

}
