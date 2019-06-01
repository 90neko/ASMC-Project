package com.ksptooi.ASMC.Command;

import com.ksptooi.ASMC.Entity.CommandEntity;
import com.ksptooi.ASMC.Main.ASMC;
import com.ksptooi.ASMC.Message.MessageManager;

public class Command_Link implements Command_cmd{

	@Override
	public void ExecuteOfType(CommandEntity ce) {

		if( ! ASMC.getUserManager().isHaveAccess(ce)){
			return;
		}
		
		
		MessageManager msg=ASMC.getMessageManager();

		try {

			msg.sendSysMessage("�ȴ����ݿ�..");

			Runtime.getRuntime().exec(ce.getPath());


		} catch (Exception e) {
			e.printStackTrace();
			msg.sendErrorMessage("Link����ʧ�� - δ֪����");
		}
		
		
	}

	@Override
	public Command_cmd getThis() {
		return this;
	}

}
