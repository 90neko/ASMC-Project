package uk.iksp.asmc.mysql;

import org.apache.ibatis.session.SqlSessionFactory;

import com.ksptooi.ASMC.Main.ASMC;
import com.ksptooi.ASMC.Message.MessageManager;

import uk.iksp.v7.FactoryBuilder.GeneralDataFactoryBuilder;

public class MysqlServices {

	
	private MessageManager msg = ASMC.getMessageManager();
	
	
	private SqlSessionFactory sqlSessionFactory = null;
	
	
	public MysqlServices(){
		
		msg.sendSysMessage("初始化内部组件 - 数据服务");
		msg.sendSysMessage("·数据服务 - 数据库状态:离线");
		msg.sendSysMessage("·数据服务 - 连接到数据库");
		GeneralDataFactoryBuilder gdfb = new GeneralDataFactoryBuilder();	
		this.sqlSessionFactory = gdfb.buildSqlSessionFactory(ASMC.getConfigManager().getConfigFile());
		
		msg.sendSysMessage("·数据服务 - 数据库状态:在线");
		
	}


	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}


	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	
	
}
