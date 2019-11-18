package com.ksptooi.ASMC.auth;

import com.ksptooi.ASMC.Main.ASMC;
import com.ksptooi.ASMC.Message.MessageManager;
import com.ksptooi.ASMC.Util.AdvHash;
import com.ksptooi.ASMC.event.ActiveUserChangeEvent;

import uk.iksp.asmc.entity.command.CommandEntity;
import uk.iksp.asmc.entity.user.UserEntity;

public class AuthManager {

	
	private UserEntity ActiveUser=null;
	
	private MessageManager msg=null;
	
	public AuthManager(){
		

		msg = ASMC.getMessageManager();
		
	}
	
		
	public boolean isHaveAccess(CommandEntity ce){
		
				
		if(!ce.getCm_CCA().equals(ASMC.getUserManager().getActiveUser().getAccount())){
			msg.sendErrorMessage("执行命令时发生错误:权限不足.");
			return false;
		}		
		
		return true;
		
	}
	
	
	
	//账户登录
	public boolean userLogin(){
		
		String userName=null;
		String pwd=null;
		
		UserEntity ue=null;
			
	
		msg.sendSysMessageNoLine("账号:");
		userName=msg.getMessage();
		
		
		//判断是否为null
		if(userName == null){
			msg.sendWarningMessage("放弃操作.");
			return false;
		}
		
		
		pwd=msg.getMessageOfPWD();
		
		if(pwd == null){
			msg.sendWarningMessage("放弃操作.");
			return false;
		}
		
		
//		//调用方法取用户实例
//		ue = this.getUser(userName);
		
		if(ue == null){
			msg.sendSysMessage("·等待数据库...");
			msg.sendErrorMessage("·失败.");
			msg.sendBr();
			return false;
		}
		
		
		if(!AdvHash.md5(pwd).equals(ue.getPassword())){
			msg.sendSysMessage("·等待数据库...");
			msg.sendErrorMessage("·失败.");
			msg.sendBr();
			return false;
		}
		
		msg.sendSysMessage("等待数据库...");
		
		msg.sendBr();
		
		this.setActiveUser(ue);
		
		return true;
		
	}
	
	

	//获取账户
//	public UserEntity getUser(String name){
//		
//		String sql= "select * from "+Table_Main+" where "+Field_AQSCPAccount+"='"+name+"'";
//		
////		ResultSet rs=dao.query(sql);
//		
//		UserEntity ue=new UserEntity();
//		
//		try {
//			
//			while(rs.next()){
//				
//				ue.setAccount(rs.getString(Field_AQSCPAccount));
//				ue.setPassword(rs.getString(Field_AQSCPPassword));
//				
//			}
//			
//			return ue;
//			
//		} catch (SQLException e) {
//			return null;
//		}
//		
//	}





	public UserEntity getActiveUser() {
		return ActiveUser;
	}

	

	/**
	 * 设置当前活跃用户
	 * @param activeUser
	 */
	public void setActiveUser(UserEntity activeUser) {
		
		//创建事件
		
		ActiveUserChangeEvent AUCE = new ActiveUserChangeEvent(this.getActiveUser(), activeUser);
		
		
		boolean isSuccess=ASMC.getEventmanager().startActiveUserChangeEvent(AUCE);
		
		if(isSuccess) {
			msg.sendBr();
			msg.successMessage("!权限被更新.");
			
			msg.successMessage("ActiveUser:"+ActiveUser.getAccount());
		}
		
		
	}
	
	
	
	//切换用户(不经过事件)
	public void changeActiveUser(UserEntity activeUser) {		
		this.ActiveUser = activeUser;	
	}
	
	
	
	
	
}
