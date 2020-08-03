package uk.iksp.asmc.services;

import java.io.IOException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.ksptooi.asmc.main.Asmc;
import com.ksptooi.asmc.message.Logger;


@Deprecated
public class MysqlServices {

	
	private Logger msg = Asmc.getLogger();
	
	
	private SqlSessionFactory sqlSessionFactory = null;
	
	
	public MysqlServices(){
		
		msg.info("初始化内部组件 - 数据服务");
		msg.info("·数据服务 - 数据库状态:离线");
		msg.info("·数据服务 - 连接到数据库");
			
		SqlSessionFactoryBuilder ssfb=new SqlSessionFactoryBuilder();
		
		try {
			
			this.sqlSessionFactory = ssfb.build(Resources.getResourceAsReader("GeneralDataCore-Config.xml"));
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		msg.info("·数据服务 - 数据库状态:在线");
		
	}
	


	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}


	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	
	
}
