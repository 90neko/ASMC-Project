package com.ksptooi.asmc.service.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ksptooi.asmc.common.Project;
import com.ksptooi.asmc.main.Asmc;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringContainer implements SpringContainerService{

	
	ApplicationContext container = null;
	
	
	
	public SpringContainer() {
		
		Asmc.getLogger().info("初始化内部组件 - SpringContainerService[SCSM]");

		this.container = new ClassPathXmlApplicationContext(Project.springConfig);
		
	}
	
	
	@Override
	public ApplicationContext getContainer() {
		return this.container;
	}

	
	@Override
	public <T> T getBean(Class<T> requiredType){
		return this.container.getBean(requiredType);
	}

}
