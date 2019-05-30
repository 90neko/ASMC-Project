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
			msg.sendErrorMessage("ִ������ʱ��������:Ȩ�޲���.");
			return false;
		}	
		
		
		return true;
		
	}
	
	
	
	//�˻���¼
	public boolean userLogin(){
		
		String user=null;
		String pwd=null;
		UserEntity ue=null;
			
	
		msg.sendSysMessageNoLine("�˺�:");
		user=msg.getMessage();
		
		
		//�ж��Ƿ�Ϊnull
		if(user == null){
			msg.sendWarningMessage("��������.");
			return false;
		}
		
		
		pwd=msg.getMessageOfPWD();
		
		if(pwd == null){
			msg.sendWarningMessage("��������.");
			return false;
		}
		
		
		if(this.getUser(user) == null){
			msg.sendSysMessage("���ȴ����ݿ�...");
			msg.sendErrorMessage("��ʧ��.");
			msg.sendBr();
			return false;
		}
		
		ue = this.getUser(user);
		
		if(!AdvHash.md5(pwd).equals(ue.getPassword())){
			msg.sendSysMessage("���ȴ����ݿ�...");
			msg.sendErrorMessage("��ʧ��.");
			msg.sendBr();
			return false;
		}
		
		msg.sendSysMessage("�ȴ����ݿ�...");
		
		msg.sendBr();
		this.setActiveUser(user);
		
		return true;
		
	}
	
	

	//��ȡ�˻�
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
		msg.sendWarningMessage("!Ȩ�ޱ�����.");
		
		msg.sendWarningMessage("ActiveUser:"+ActiveUser);


	}
	
	
	
	
	
}
