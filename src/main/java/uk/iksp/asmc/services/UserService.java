package uk.iksp.asmc.services;

import java.util.ArrayList;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ksptooi.asmc.data.mapper.UserMapper;
import com.ksptooi.asmc.entity.user.User;
import com.ksptooi.asmc.main.Asmc;
import com.ksptooi.asmc.message.Logger;

import uk.iksp.asmc.event.type.ActiveUserChangeEvent;

public class UserService {

	Logger log = Asmc.getLogger();
	
	SqlSessionFactory ssf = null;
	
	User activeUser = null;
	
	public UserService(){
		log.info("初始化内部组件 - 用户访问服务");
		this.ssf = Asmc.getMysqlSerices().getSqlSessionFactory();
	}
	
	
	
	/**
	 * 获取用户
	 */
	public User getUser(String userName){
		
		try(SqlSession ss=ssf.openSession(true)){
			
			UserMapper map = ss.getMapper(UserMapper.class);
			
			return map.queryUserByName(userName).get(0);
		}
		
	}
	
	/**
	 * 判断用户是否存在
	 */
	public boolean isExistsUser(String userName){
		
		try(SqlSession ss=ssf.openSession(true)){
			
			UserMapper map = ss.getMapper(UserMapper.class);
			
			ArrayList<User> userList = map.queryUserByName(userName);
			
			if(userList.size()>0){
				return true;
			}
			
			return false;
			
		}
		
	}
	
	/**
	 * 切换当前活动用户
	 */
	public void changeActiveUser(User user){
		
		//调用事件
		boolean isSuccess = Asmc.getEventCreate().startActiveUserChangeEvent(new ActiveUserChangeEvent(activeUser,user));
		
		//如果事件被取消则不切换用户
		if( ! isSuccess){
			return;
		}
		
		//切换用户
		this.activeUser = user;
		
	}

	public User getActiveUser() {
		return activeUser;
	}

	public void setActiveUser(User activeUser) {
		this.activeUser = activeUser;
	}
	
	
	
}
