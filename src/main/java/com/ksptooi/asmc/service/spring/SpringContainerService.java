package com.ksptooi.asmc.service.spring;

import org.springframework.context.ApplicationContext;

public interface SpringContainerService {




	
	/**
	 * 获取Spring容器
	 */
	public ApplicationContext getContainer();
	
	
	/**
	 * 获取bean
	 */
	public <T> T getBean(Class<T> requiredType);
	
	
}
