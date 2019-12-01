package uk.iksp.asmc.command.type;


import com.ksptooi.ASMC.Main.Asmc;
import com.ksptooi.ASMC.Message.MessageManager;
import uk.iksp.asmc.entity.command.CommandEntity;

public interface Command_cmd {

	MessageManager msg=Asmc.getMessageManager();

	
	public void ExecuteOfType(CommandEntity ce);
	
	//返回自身
	public Command_cmd getThis();
	
}
