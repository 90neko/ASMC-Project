package com.ksptooi.ASMC.Data;

import java.sql.Connection;

public class SqlManager {

	
	private SqlDAO dao=new SqlDAO();
	

	
	
	//�������ݿ������
	public void Connect(){	
		dao.Connect();	
	}
	
	//�ж��Ƿ����ӳɹ�
	public boolean isActive(){
		return dao.isActive();
	}
	
	
	//��ȡdao
	public SqlDAO getDAO(){
		return dao;
	}
	
	
	
	//��ȡ/��������
	public Connection getConn() {
		return dao.getConn();
	}

	public void setConn(Connection conn) {
		this.dao.setConn(conn);
	}
	
	
	
	
	
	
	/**
	
	//��ѯ��������
	public String getTypeOfCmd(String cmd){
		
		String sql="select "+Field_CommandType+" from "+Table_Command+" where Name='"+cmd+"'";
		
		
		ResultSet rs=dao.query(sql);
		
		try {
			
			while(rs.next()){
				
				return rs.getString(Field_CommandType);
				
			}
			
			rs.getStatement().close();
			
		} catch (SQLException e) {
			e.printStackTrace();	
		}
		
		return null;
		
	}
	
	//��ѯ�����ַ
	public String getPathOfCmd(String cmd){
		
		String sql="select "+Field_CommandPath+" from "+Table_Command+" where Name='"+cmd+"'";
		
		
		ResultSet rs=dao.query(sql);
		
		try {
			
			while(rs.next()){
				
				return rs.getString(Field_CommandPath);
				
			}
			
			rs.getStatement().close();
			
		} catch (SQLException e) {
			e.printStackTrace();	
		}
		
		return null;
		
	}
	
	**/
	
//	//��ȡ����TypeΪlink���ֶ�
//	public ArrayList<String> getAllCommandOfLink(){
//		
//		Statement stm=null;
//		ResultSet RS=null;
//		ArrayList<String> AL=new ArrayList<String>();
//		
//		
//		try {
//			
//			stm=Var.SQLConn.createStatement();
//			
//			RS=stm.executeQuery("select CMDAddress,[Type] from CMDTable where Type='link' and CMDAddress like '%exe%'");
//			
//			
//			while(RS.next()){
//				AL.add(RS.getString("CMDAddress"));
//			}
//			
//			stm.close();
//			RS.close();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//			Var.MessageManager.SendErrorMessage("���ݿ����Ӵ���");
//		}
//		
//
//		
//		return AL;
//		
//	}
	
//	//���ݵ�ַɾ������
//	public void DeleteCommandONAddress(String Address){
//		
//			String SQL="delete from CMDTable where CMDAddress like '%"+Address+"%' ";
//		
//		try {
//			
//			Statement stm=Var.SQLConn.createStatement();
//			
//			stm.executeUpdate(SQL);
//
//			
//			stm.close();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//			Var.MessageManager.SendErrorMessage("���ݿ����Ӵ���");
//		}
//		
//		
//		
//	}
	
	
	
//	//ɾ��һ������
//	public void delCommand(String Name){
//		
//		Statement stm=null;
//		
//		String SQL="delete from CMDTable"
//				  +" where CMDName='"+Name+"'";
//		
//		try {
//			
//			stm=Var.SQLConn.createStatement();
//			
//			if(!Var.SFAC_SQL.CMDisExists(Name)){
//				Var.MessageManager.SendWarningMessage("���������");
//				return;
//			}
//			
//			stm.executeUpdate(SQL);
//			
//			Var.MessageManager.SendSysMessage("��ͬ�������ݿ�.");
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//			Var.MessageManager.SendErrorMessage("���ݿ����Ӵ���!");
//			
//		}
//		
//				
//	}
	


	
}
