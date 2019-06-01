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
//			msg.sendErrorMessage("ִ������ʱ��������:Ȩ�޲���.");
//			return false;
//		}		
//		
//		return true;
//		
//	}
	
	
	
	

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





	public UserEntity getActiveUser() {
		return ActiveUser;
	}

	

	/**
	 * ���õ�ǰ��Ծ�û�
	 * @param activeUser
	 */
	public void setActiveUser(UserEntity activeUser) {
		
		//�����¼�
		
		ActiveUserChangeEvent AUCE = new ActiveUserChangeEvent(this.getActiveUser(), activeUser);
		
		
		boolean isSuccess=ASMC.getEventmanager().startActiveUserChangeEvent(AUCE);
		
		if(isSuccess) {
			msg.sendBr();
			msg.sendWarningMessage("!Ȩ�ޱ�����.");
			
			msg.sendWarningMessage("ActiveUser:"+ActiveUser.getAccount());
		}
		
		
	}
	
	
	
	//�л��û�(�������¼�)
	public void changeActiveUser(UserEntity activeUser) {		
		this.ActiveUser = activeUser;	
	}
	
	
	
	
	
}
