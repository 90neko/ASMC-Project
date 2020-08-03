package com.ksptooi.asmc.service.unit;

import java.io.IOException;

import org.junit.Test;
import com.ksptooi.asmc.data.mapper.CommandMapper;
import com.ksptooi.asmc.entity.command.Command;
import com.ksptooi.asmc.main.Asmc;
import com.ksptooi.asmc.service.command.CommandData;
import com.ksptooi.asmc.service.command.CommandDataService;
import com.ksptooi.asmc.service.spring.SpringContainer;
import com.ksptooi.asmc.service.spring.SpringContainerService;

public class CommandServiceUnit {

	

    @Test
    public void UnitTest() throws IOException, InterruptedException{

    	
    	/**
    	 * 测试CommandDataService 模块功能
    	 */
    	
    	CommandDataService cds = new CommandData();
    	
    	boolean exist = cds.isExist("ff5");
    	
    	System.out.println(exist);
        
    }


}
