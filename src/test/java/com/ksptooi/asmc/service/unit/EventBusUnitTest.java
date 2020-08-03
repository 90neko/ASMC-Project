package com.ksptooi.asmc.service.unit;

import org.junit.Test;

import com.ksptooi.asmc.entity.event.UserChangeEvent;
import com.ksptooi.asmc.eventHandler.TestEventHandler;
import com.ksptooi.asmc.eventHandler.TestEventHandler2;
import com.ksptooi.asmc.main.Asmc;
import com.ksptooi.asmc.service.event.EventBusService;

public class EventBusUnitTest {
	
	
	
	
	@Test
	public void EventTest() {
		
		
		EventBusService eveBus = Asmc.getEventBusService();
		
	
		eveBus.regListener(new TestEventHandler());
		eveBus.regListener(new TestEventHandler2());
		
		UserChangeEvent event = new UserChangeEvent(null, null);
		
		eveBus.event(event);
		
		
	}
	

}
