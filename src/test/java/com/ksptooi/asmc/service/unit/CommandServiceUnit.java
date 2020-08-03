package com.ksptooi.asmc.service.unit;

import org.junit.Test;
import com.ksptooi.asmc.data.mapper.CommandMapper;
import com.ksptooi.asmc.entity.command.Command;
import com.ksptooi.asmc.service.spring.SpringContainer;
import com.ksptooi.asmc.service.spring.SpringContainerService;

public class CommandServiceUnit {

	

    @Test
    public void UT19(){

        SpringContainerService scs = new SpringContainer();

        CommandMapper bean = scs.getBean(CommandMapper.class);
        
        Command cmd = new Command();
        
        cmd.setName("ff");
        
        Command query = bean.query(cmd);
        
        System.out.println(query.toString());
        
    }


}
