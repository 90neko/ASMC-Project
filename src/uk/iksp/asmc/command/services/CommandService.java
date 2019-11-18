package uk.iksp.asmc.command.services;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ksptooi.ASMC.Main.ASMC;
import com.ksptooi.ASMC.Message.MessageManager;

import uk.iksp.asmc.Mysql.Inteface.CommandData;
import uk.iksp.asmc.entity.command.CommandEntity;

public class CommandService {

	private MessageManager msg = ASMC.getMessageManager();
	
	private SqlSessionFactory ssf= ASMC.getMysqlSerices().getSqlSessionFactory();
	
	
	public CommandService(){
		
		msg.sendSysMessage("初始化内部组件 - 命令服务");
		
	}
	
	
	//判断某条命令是否存在
	public boolean isExistsCommand(String name){
		
		
		try(SqlSession ss = ssf.openSession(true)){
			
			CommandData map = ss.getMapper(CommandData.class);
			
			ArrayList<CommandEntity> ceList = map.queryCommandByName(name);
			
			if(ceList.size()>0){
				return true;
			}
			
			
		}
			
		return false;	
		
	}
	
	//从数据库获取命令
	public CommandEntity getCommand(String name){
		
		
		try(SqlSession ss = ssf.openSession(true)){
			
			CommandData map = ss.getMapper(CommandData.class);
			
			return map.queryCommandByName(name).get(0);
				
		}
			
	}
	
	//查询所有命令
	public ArrayList<CommandEntity> getAllCommand(){
		
		try(SqlSession ss = ssf.openSession(true)){
			
			CommandData map = ss.getMapper(CommandData.class);
			
			return map.queryCommand();
			
		}
		
	}
	
	//添加命令
	public void addCommand(CommandEntity ce){
			
		try(SqlSession ss = ssf.openSession(true)){
			
			CommandData map = ss.getMapper(CommandData.class);
			
			map.addCommand(ce);
			
		}
			
	}
	
	//删除命令
	public void delCommand(String name){
			
		try(SqlSession ss = ssf.openSession(true)){
			
			CommandData map = ss.getMapper(CommandData.class);
			
			map.delCommand(name);
			
		}
			
	}
	
	
	
	
}
