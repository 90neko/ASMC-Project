package com.ksptooi.ASMC.Plugins;

import com.ksptooi.ASMC.Command.Command_cmd;
import com.ksptooi.ASMC.Entity.CommandEntity;

public class ASMCPlugin implements Command_cmd{

	
	@Override
	public void ExecuteOfType(CommandEntity ce) {
		
		
	}

	
	
	//���ز������
	@Override
	public Command_cmd getThis() {
		return this;
	}

	
	//�������ʱ����ִ��һ��
	public void onEnable(){
		
	}
	
	
	
	
	
	
}
