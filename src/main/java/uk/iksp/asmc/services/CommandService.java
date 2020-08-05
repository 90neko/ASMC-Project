package uk.iksp.asmc.services;

import java.util.ArrayList;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ksptooi.asmc.data.mapper.CommandMapper;
import com.ksptooi.asmc.entity.commandType.Command_cmd;
import com.ksptooi.asmc.main.Asmc;
import com.ksptooi.asmc.message.Logger;
import com.ksptooi.asmc.service.command.CommandTypeScanner;

import uk.iksp.asmc.command.exception.UnknowCommandTypeException;
import uk.iksp.asmc.entity.command.AsmcCommand;
import uk.iksp.asmc.entity.command.CommandEntity;
import uk.iksp.asmc.entity.command.InputCommand;


@Deprecated
public class CommandService {

	private Logger log = Asmc.getLogger();
	
	private SqlSessionFactory ssf= null;
	
	
	public CommandService(){
		
		log.info("初始化内部组件 - 命令服务");
		
	}
	
	//更新命令
	public void updateCommand(CommandEntity ce) {
		
		try(SqlSession ss = ssf.openSession(true)){
			
			CommandMapper map = ss.getMapper(CommandMapper.class);

			
		}
		
	}
	
	
	//判断某条命令是否存在
	public boolean isExistsCommand(String name){
		
		
		try(SqlSession ss = ssf.openSession(true)){
			
			CommandMapper map = ss.getMapper(CommandMapper.class);
			
			
			
			
			
		}
			
		return false;	
		
	}
	
	//从数据库获取命令
	public CommandEntity getCommand(String name){
		
		
		try(SqlSession ss = ssf.openSession(true)){
			
			CommandMapper map = ss.getMapper(CommandMapper.class);
			
			return null;
				
		}
			
	}
	
	//从数据库获取命令
	public CommandEntity getCommand(InputCommand ic){
		return this.getCommand(ic.getName());
	}
	
	
	//获取Asmc命令
	public AsmcCommand getAsmcCommand(InputCommand ic) throws UnknowCommandTypeException{
		
		CommandEntity commandEntity = this.getCommand(ic);

//		Command_cmd commandType = CommandTypeScanner.getType(commandEntity.getCm_Type());
//		
//		AsmcCommand asmcCommand = commandEntity.getAsmcCommand(commandType);
		
//		asmcCommand.setInputCommand(ic);
//		
		return null;
	}
	
	
	//查询所有命令
	public ArrayList<CommandEntity> getAllCommand(){
		
		try(SqlSession ss = ssf.openSession(true)){
			
			CommandMapper map = ss.getMapper(CommandMapper.class);
			
			return null;
			
		}
		
	}
	
	//添加命令
	public void addCommand(CommandEntity ce){
		
		if(this.isExistsCommand(ce.getCm_Name())) {
			log.warn("尝试添加的命令已存在!");
			return;
		}
		
		try(SqlSession ss = ssf.openSession(true)){
			
			CommandMapper map = ss.getMapper(CommandMapper.class);
			
//			map.addCommand(ce);
			
		}
			
	}
	
	//删除命令
	public void delCommand(String name){
			
		if( ! this.isExistsCommand(name)) {
			log.warn("尝试删除的命令不存在!");
			return;
		}
		
		
		try(SqlSession ss = ssf.openSession(true)){
			
			CommandMapper map = ss.getMapper(CommandMapper.class);
			
			/* map.delCommand(name); */
			
		}
			
	}
	
	
	
	
}
