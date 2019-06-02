package com.ksptooi.ASMC.Data;

import java.sql.Connection;

public class SqlManager {

	
	private SqlDAO dao=new SqlDAO();
	

	
	
	//打开与数据库的连接
	public void Connect(){	
		dao.Connect();	
	}
	
	//判断是否连接成功
	public boolean isActive(){
		return dao.isActive();
	}
	
	
	//获取dao
	public SqlDAO getDAO(){
		return dao;
	}
	
	
	
	//获取/设置连接
	public Connection getConn() {
		return dao.getConn();
	}

	public void setConn(Connection conn) {
		this.dao.setConn(conn);
	}
	
	
	
	
	
	
	/**
	
	//查询命令类型
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
	
	//查询命令地址
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
	
//	//获取所有Type为link的字段
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
//			Var.MessageManager.SendErrorMessage("数据库连接错误！");
//		}
//		
//
//		
//		return AL;
//		
//	}
	
//	//根据地址删除命令
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
//			Var.MessageManager.SendErrorMessage("数据库连接错误！");
//		}
//		
//		
//		
//	}
	
	
	
//	//删除一个命令
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
//				Var.MessageManager.SendWarningMessage("此命令不存在");
//				return;
//			}
//			
//			stm.executeUpdate(SQL);
//			
//			Var.MessageManager.SendSysMessage("已同步至数据库.");
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//			Var.MessageManager.SendErrorMessage("数据库连接错误!");
//			
//		}
//		
//				
//	}
	


	
}
