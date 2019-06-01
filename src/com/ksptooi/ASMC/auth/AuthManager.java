package com.ksptooi.ASMC.auth;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ksptooi.ASMC.Data.SqlDAO;
import com.ksptooi.ASMC.Entity.UserEntity;
import com.ksptooi.ASMC.Main.ASMC;
import com.ksptooi.ASMC.Message.MessageManager;
import com.ksptooi.ASMC.event.ActiveUserChangeEvent;

public class AuthManager {

	
	private UserEntity ActiveUser=null;
	
	private String Table_Main = null;
	private String Field_AQSCPAccount = null;
	private String Field_AQSCPPassword = null;
	private SqlDAO dao = null;
	private MessageManager msg=null;
	
	public AuthManager(){
		
		Table_Main = ASMC.getConfigEntity().getTable_Main();
		Field_AQSCPAccount = ASMC.getConfigEntity().getField_AQSCPAccount();
		Field_AQSCPPassword = ASMC.getConfigEntity().getField_AQSCPPassword();
		dao = ASMC.getSqlmanager().getDAO();
		msg = ASMC.getMessageManager();
		
	}
	
		
//	public boolean isHaveAccess(CommandEntity ce){
//		
//				
//		if(!ce.getCCA().equals(ASMC.getUserManager().getActiveUser().getAccount())){
//			msg.sendErrorMessage("执行命令时发生错误:权限不足.");
//			return false;
//		}		
//		
//		return true;
//		
//	}
	
	
	
	

	//获取账户
	public UserEntity getUser(String name){
		
		String sql= "select * from "+Table_Main+" where "+Field_AQSCPAccount+"='"+name+"'";
		
		ResultSet rs=dao.query(sql);
		
		UserEntity ue=new UserEntity();
		
		try {
			
			while(rs.next()){
				
				ue.setAccount(rs.getString(Field_AQSCPAccount));
				ue.setPassword(rs.getString(Field_AQSCPPassword));
				
			}
			
			return ue;
			
		} catch (SQLException e) {
			return null;
		}
		
	}





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
			msg.sendWarningMessage("!权限被更新.");
			
			msg.sendWarningMessage("ActiveUser:"+ActiveUser.getAccount());
		}
		
		
	}
	
	
	
	//切换用户(不经过事件)
	public void changeActiveUser(UserEntity activeUser) {		
		this.ActiveUser = activeUser;	
	}
	
	
	
	
	
}
