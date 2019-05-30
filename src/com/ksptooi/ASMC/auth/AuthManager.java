package com.ksptooi.ASMC.auth;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ksptooi.ASMC.Data.SqlDAO;
import com.ksptooi.ASMC.Entity.CommandEntity;
import com.ksptooi.ASMC.Entity.UserEntity;
import com.ksptooi.ASMC.Main.ASMC;
import com.ksptooi.ASMC.Message.MessageManager;
import com.ksptooi.ASMC.Util.AdvHash;

public class AuthManager {

	
	private String ActiveUser=null;
	
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
	
	
	
	public boolean isHaveAccess(CommandEntity ce){
		
				
		if(!ce.getCCA().equals(ASMC.getUsermanager().getActiveUser())){
			msg.sendErrorMessage("执行命令时发生错误:权限不足.");
			return false;
		}	
		
		
		return true;
		
	}
	
	
	
	//账户登录
	public boolean userLogin(){
		
		String user=null;
		String pwd=null;
		UserEntity ue=null;
			
	
		msg.sendSysMessageNoLine("账号:");
		user=msg.getMessage();
		
		
		//判断是否为null
		if(user == null){
			msg.sendWarningMessage("放弃操作.");
			return false;
		}
		
		
		pwd=msg.getMessageOfPWD();
		
		if(pwd == null){
			msg.sendWarningMessage("放弃操作.");
			return false;
		}
		
		
		if(this.getUser(user) == null){
			msg.sendSysMessage("·等待数据库...");
			msg.sendErrorMessage("·失败.");
			msg.sendBr();
			return false;
		}
		
		ue = this.getUser(user);
		
		if(!AdvHash.md5(pwd).equals(ue.getPassword())){
			msg.sendSysMessage("·等待数据库...");
			msg.sendErrorMessage("·失败.");
			msg.sendBr();
			return false;
		}
		
		msg.sendSysMessage("等待数据库...");
		
		msg.sendBr();
		this.setActiveUser(user);
		
		return true;
		
	}
	
	

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





	public String getActiveUser() {
		return ActiveUser;
	}

	

	public void setActiveUser(String activeUser) {
		ActiveUser = activeUser;
		msg.sendBr();
		msg.sendWarningMessage("!权限被更新.");
		
		msg.sendWarningMessage("ActiveUser:"+ActiveUser);


	}
	
	
	
	
	
}
