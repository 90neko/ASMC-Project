package com.ksptooi.asmc.service.unit;

import org.junit.Test;

import com.ksptooi.asmc.entity.user.User;
import com.ksptooi.asmc.service.user.UserData;
import com.ksptooi.asmc.service.user.UserDataService;

public class UserDataServiceUnit {
	
	
	
	
	@Test
	public void UnitTest() throws ClassNotFoundException {
		
		
		Class.forName("com.ksptooi.asmc.main.Asmc");
		
		UserDataService uds = new UserData();
		
		User user = uds.getUser("TF801B");
		
		
		System.out.println(user);
	}

	
	
	
	
	
	
	
	
	
	
	
}