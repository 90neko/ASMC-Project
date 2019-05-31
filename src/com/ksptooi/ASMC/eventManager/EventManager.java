package com.ksptooi.ASMC.eventManager;

import java.util.HashMap;
import com.ksptooi.ASMC.Main.ASMC;
import com.ksptooi.ASMC.Plugins.ASMCPlugin;
import com.ksptooi.ASMC.event.CommandEvent;

public class EventManager {

	
	private EventHandler eh=null;
	
	private HashMap<String,EventHandler> eventHandler =new HashMap<String,EventHandler>();
	
	public EventManager(){
		eh = new EventHandler();
	}
	
	
	public void startCommandEvent(CommandEvent ce){
		
		//ִ���Դ����¼�������
		CommandEvent event=eh.event_onCommand(ce);
		
		
		//ִ�в�����¼�������
		for(String str:ASMC.getPluginManager().getRegCommandTypeList()){
			
			
			
			//ͨ������������ͻ�ȡ�������
			ASMCPlugin asmcp=(ASMCPlugin)ASMC.getPluginManager().getInstallPlugin().get(str);
			
			//ͨ����������ȡ����¼�������
			EventHandler em=eventHandler.get(asmcp);
			
			
			if(em != null){
				
				System.out.println("ִ�в���¼�:"+str);
				event=em.event_onCommand(ce);
				
				
			}
			
			
			
			
		}
		
		
		
		//�ж��Ƿ�ȡ��
		if(event.isCancel()){
			return;
		}
		
		//�ж��Ƿ��ѱ������ύ
		if(event.isCommit()){
			return;
		}
		
		
		//�ύ�¼�
		event.getCommandType().ExecuteOfType(event.getCommandEntity());
		
	}


	
	//ע���¼�
	public void regEventHandler(ASMCPlugin plugin,EventHandler eveh) {
		
		//ͨ��������� �ҳ���������
		String CommandType=ASMC.getPluginManager().getInstallCommandType().get(plugin);
		
		//ע���¼�
		
		eventHandler.put(CommandType, eveh);
		
	}



	
	
	
	
	
}
