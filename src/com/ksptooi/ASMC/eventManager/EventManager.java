package com.ksptooi.ASMC.eventManager;

import java.util.ArrayList;
import com.ksptooi.ASMC.Plugins.ASMCPlugin;
import com.ksptooi.ASMC.event.CommandEvent;

public class EventManager {

	//�Դ���eventHandler
	private EventHandler eh=null;
	
	private ArrayList<EventHandler> eventHandler =new ArrayList<EventHandler>();
	
	public EventManager(){
		eh = new EventHandler();
	}
	
	public void startCommandEvent(CommandEvent ce){
		
		//ִ���Դ����¼�������
		CommandEvent event=eh.event_onCommand(ce);
		
		
		//ִ�в�����¼�������
		for(EventHandler ch:eventHandler){
			
			event = ch.event_onCommand(event);
				
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
		
		
		//ע���¼�
		
		
		eventHandler.add(eveh);
		
	}



	
	
	
	
	
}
