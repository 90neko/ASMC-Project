package com.ksptooi.ASMC.eventManager;

import java.util.ArrayList;

import com.ksptooi.ASMC.Main.ASMC;
import com.ksptooi.ASMC.Message.MessageManager;
import com.ksptooi.ASMC.Plugins.ASMCPlugin;
import com.ksptooi.ASMC.event.ActiveUserChangeEvent;
import com.ksptooi.ASMC.event.CommandEvent;

public class EventManager {

	//�Դ���eventHandler
	private EventHandler eh=null;
	
	MessageManager msg = ASMC.getMessageManager();
	
	
	//��ע���ȫ���¼�
	private ArrayList<EventHandler> eventHandler =new ArrayList<EventHandler>();
	

	
	
	public EventManager(){
		eh = new EventHandler();
		
	}
	
	//��ʼһ��Command�¼�
	public void startCommandEvent(CommandEvent ce){
		
		//ִ���Դ����¼�������
		CommandEvent event=eh.onCommand(ce);
		
		
		//ִ�в�����¼�������
		for(EventHandler ch:eventHandler){		
			event = ch.onCommand(event);				
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

	
	
	//��ʼһ��ActiveUserChange�¼�
	public boolean startActiveUserChangeEvent(ActiveUserChangeEvent event) {
		
		ActiveUserChangeEvent AUCE = eh.onActiveUserChange(event);
		
		for(EventHandler ch:eventHandler){		
			event = ch.onActiveUserChange(AUCE);				
		}
		
		if(event.isCancel()){
			return false;
		}
		
		//�ύ�¼�
		
		ASMC.getUserManager().changeActiveUser(AUCE.getChangeToUser());
		return true;
		
	}
	
	
	

	
	//ע���¼�
	public void regEventHandler(ASMCPlugin plugin,EventHandler eveh) {
		
		msg.sendSysMessage("ע���¼�������:"+plugin.getPluginName());
		
		//ע���¼�		
		eventHandler.add(eveh);
		
	}



	
	
	
	
	
}
