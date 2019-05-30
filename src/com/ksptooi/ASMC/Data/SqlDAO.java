package com.ksptooi.ASMC.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ksptooi.ASMC.Entity.ConfigEntity;
import com.ksptooi.ASMC.Main.ASMC;

public class SqlDAO {

	
	private Connection conn=null;
	
	//打开与数据库的连接
	public void Connect() {
			
		
		ConfigEntity ce=ASMC.getConfigEntity();
		
		
		try {
			
			conn=DriverManager.getConnection(ce.getSql_URL(),ce.getSql_USER(),ce.getSql_PWD());
			
		} catch (Exception e) {
			e.printStackTrace();
			ASMC.getMessageManager().sendErrorMessage("数据库连接错误！");
		}
		
	}
	
	
	
	
	//判断是否连接成功
	public boolean isActive(){
		
		if(conn == null){
			return false;
		}
		
		return true;
	}
	

	//执行查询
	public ResultSet query(String sql){
		
		try {
			
			return conn.createStatement().executeQuery(sql);
			
		} catch (SQLException e) {
			return null;
		}
		
		
	}
	
	//执行非查询
	public void noQuery(String sql){
		
		try {
			
			conn.createStatement().executeUpdate(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}




	public Connection getConn() {
		return conn;
	}




	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
	
}

