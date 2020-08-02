package uk.iksp.asmc.event.type;

import uk.iksp.asmc.entity.command.InputCommand;
import uk.iksp.asmc.event.basic.AsmcEvent;

public class PreCommandEvent extends AsmcEvent{
	
	

	private final String eventName = "PreCommandEvent";
	
	
	private InputCommand inputCommand = null;
	

	private boolean isCancel = false;
	
	public PreCommandEvent(InputCommand ic){
		this.inputCommand = ic;
	}
	
	
	public boolean isCancel() {
		return isCancel;
	}

	public void setCancel(boolean isCancel) {
		
		if(isCancel == false) {
			return;
		}
		
		
		this.isCancel = isCancel;
	}

	
	public String getEventName() {
		return eventName;
	}



	@Override
	public void commit() {
		
	}

	public InputCommand getInputCommand() {
		return inputCommand;
	}
	
	
}
