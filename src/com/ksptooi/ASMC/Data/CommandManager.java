package com.ksptooi.ASMC.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ksptooi.ASMC.Entity.CommandEntity;
import com.ksptooi.ASMC.Main.ASMC;
import com.ksptooi.ASMC.Message.MessageManager;

public class CommandManager {

	
	private String Table_Command=ASMC.getConfigEntity().getTable_Command();
	
	private String Field_CommandType=ASMC.getConfigEntity().getField_CommandType();
	
	private String Field_CommandName=ASMC.getConfigEntity().getField_CommandName();
	
	private String Field_CommandPath=ASMC.getConfigEntity().getField_CommandPath();
	
	private String Filed_CommandTitle=ASMC.getConfigEntity().getFiled_CommandTitle();
	
	private String Filed_CommandCreateAccount=ASMC.getConfigEntity().getFiled_CommandCreateAccount();
	
	private MessageManager msg=new MessageManager();
	
	private SqlDAO dao=null;
	
	public CommandManager(){
		
		Table_Command=ASMC.getConfigEntity().getTable_Command();
		Field_CommandType=ASMC.getConfigEntity().getField_CommandType();
		Field_CommandName=ASMC.getConfigEntity().getField_CommandName();
		Field_CommandPath=ASMC.getConfigEntity().getField_CommandPath();
		Filed_CommandTitle=ASMC.getConfigEntity().getFiled_CommandTitle();
		Filed_CommandCreateAccount=ASMC.getConfigEntity().getFiled_CommandCreateAccount();
		msg=new MessageManager();
		dao=ASMC.getSqlmanager().getDAO();
	}
	
	
	//判断某条命令是否存在
	public boolean isExistsCmd(String cmd){
		
		String sql="select "+Field_CommandName+" from "+Table_Command+" where Name='"+cmd+"'";
		
		
		
		try {
			
			ResultSet rs=dao.query(sql);
			
			while(rs.next()){
				
				if(rs.getString(Field_CommandName).equalsIgnoreCase(cmd)){
					rs.getStatement().close();
					return true;
				}
				
			}
			
			rs.getStatement().close();
			
		} catch (Exception e) {
			return false;	
		}
		
		return false;
		
	}
	
	
	//查询命令 根据命令名
	public CommandEntity getCommandByName(String cmd){
		
		String sql="select * from "+Table_Command+" where Name='"+cmd+"'";
		
		ResultSet rs=dao.query(sql);
		
		CommandEntity ce=new CommandEntity();
		
		try {
			
			
			while(rs.next()){
				
				ce.setName(rs.getString(Field_CommandName));
				ce.setType(rs.getString(Field_CommandType));
				ce.setPath(rs.getString(Field_CommandPath));
				ce.setTitle(rs.getString(Filed_CommandTitle));
				ce.setCCA(rs.getString(Filed_CommandCreateAccount));
				
			}
			
			rs.getStatement().close();
			
			return ce;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	//查询所有命令
	public ArrayList<CommandEntity> getAllCommand(){
		
		
		String sql="select * from "+Table_Command+" order by Name";
		
		ResultSet rs=dao.query(sql);
		
		ArrayList<CommandEntity> al=new ArrayList<CommandEntity>();
		
		try {
			
			while(rs.next()){
				
				CommandEntity ce=new CommandEntity();
				ce.setName(rs.getString(Field_CommandName));
				ce.setType(rs.getString(Field_CommandType));
				ce.setPath(rs.getString(Field_CommandPath));
				ce.setTitle(rs.getString(Filed_CommandTitle));
				ce.setCCA(rs.getString(Filed_CommandCreateAccount));
				
				al.add(ce);
				
			}
			
			return al;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return al;
		
	}
	
	//添加命令
	public void addCommand(CommandEntity ce){
		
		
		String sql="insert into "+Table_Command+" values('"+ce.getName()+"','"+ce.getType()+"','"+ce.getPath()+"','"+ce.getTitle()+"','"+ce.getCCA()+"')";
		
		dao.noQuery(sql);
		
		
	}
	
	//删除命令
	public void delCommand(String cmd){
		
		
		String sql="delete "+Table_Command+" where "+Field_CommandName+"='"+cmd+"'";
		
		if(!this.isExistsCmd(cmd)){
			msg.sendWarningMessage("待删除的命令不存在.");
			return;
		}
		
		
		msg.sendSysMessage("等待数据库..");
		dao.noQuery(sql);
		msg.sendSysMessage("操作成功!");
		
	}
	
	
	
	
}
