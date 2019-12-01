package uk.iksp.asmc.user.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ksptooi.ASMC.Main.Asmc;
import com.ksptooi.ASMC.Message.MessageManager;

import uk.iksp.asmc.entity.user.UserEntity;
import uk.iksp.asmc.event.type.ActiveUserChangeEvent;
import uk.iksp.asmc.inteface.user.UserData;

public class UserService {

	MessageManager msg = Asmc.getMessageManager();
	
	SqlSessionFactory ssf = null;
	
	UserEntity activeUser = null;
	
	public UserService(){
		msg.sendSysMessage("初始化内部组件 - 用户访问服务");
		this.ssf = Asmc.getMysqlSerices().getSqlSessionFactory();
	}
	
	
	
	/**
	 * 获取用户
	 */
	public UserEntity getUser(String userName){
		
		try(SqlSession ss=ssf.openSession(true)){
			
			UserData map = ss.getMapper(UserData.class);
			
			return map.queryUserByName(userName).get(0);
		}
		
	}
	
	/**
	 * 判断用户是否存在
	 */
	public boolean isExistsUser(String userName){
		
		try(SqlSession ss=ssf.openSession(true)){
			
			UserData map = ss.getMapper(UserData.class);
			
			ArrayList<UserEntity> userList = map.queryUserByName(userName);
			
			if(userList.size()>0){
				return true;
			}
			
			return false;
			
		}
		
	}
	
	/**
	 * 切换当前活动用户
	 */
	public void changeActiveUser(UserEntity user){
		
		//调用事件
		boolean isSuccess = Asmc.getEventCreate().startActiveUserChangeEvent(new ActiveUserChangeEvent(activeUser,user));
		
		//如果事件被取消则不切换用户
		if( ! isSuccess){
			return;
		}
		
		//切换用户
		this.activeUser = user;
		
	}

	public UserEntity getActiveUser() {
		return activeUser;
	}

	public void setActiveUser(UserEntity activeUser) {
		this.activeUser = activeUser;
	}
	
	
	
}
