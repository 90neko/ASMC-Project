package com.ksptooi.ASMC.Command;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ksptooi.ASMC.Entity.AccountEntity;
import com.ksptooi.ASMC.Entity.CommandEntity;
import com.ksptooi.ASMC.Main.ASMC;
import com.ksptooi.ASMC.Util.AdvHash;
import com.ksptooi.ASMC.Util.Var;

public class Command_AccountM implements Command_cmd{

	
	private String Table_Account = ASMC.getConfigEntity().getTable_Account();
	private String Field_Account = ASMC.getConfigEntity().getField_Account();
	private String Field_Password = ASMC.getConfigEntity().getField_Password();
//	private String Field_AccountType = AQSCP.getConfigEntity().getField_AccountType();
	private String Field_AccountTitle = ASMC.getConfigEntity().getField_AccountTitle();
	
	private String rules=null;
	
	@Override
	public void ExecuteOfType(CommandEntity ce) {
		
		
		if( ! ASMC.getUsermanager().isHaveAccess(ce)){
			return;
		}
		
		System.out.print("输入Rule:");
		rules = msg.getMessageOfPWD();
		
		
		while(true){
			
			msg.sendBr();
			System.out.print("ASMC@AccountMode:");
			
			String command=msg.getMessage();
			
			if(command == null){
				continue;
			}
			
			
			if(command.equals("")){
				continue;
			}
			
			
			command=command.trim();
			
			
			//菜单Show
			if(command.equalsIgnoreCase("show")){
				msg.sendBr();
				this.showAccount("select * from "+Table_Account+" order by "+Field_AccountTitle+"");
				continue;
			}
			
			
			//search搜索
			if(command.contains("search")){
				msg.sendBr();
				String searchTarget = null;
				
				try{
					searchTarget = command.split(",")[1];
				}catch(Exception e){
					System.out.println("搜索语法: search,内容");
					continue;
				}
				
				ArrayList<AccountEntity> list=this.showAccount("select * from "+Table_Account+" where "+Field_AccountTitle+" like '%"+searchTarget+"%' order by name");
				msg.sendBr();
				
				if(list.size()<1){
					msg.sendWarningMessage("此次查询没有返回任何结果.");
					continue;
				}
				
				
				
				System.out.print("选择:");
				
				int select=-2;
				
				try{
					select=new Integer(msg.getMessage());
				}catch(Exception e){
					continue;
				}
				
				AccountEntity ae=null;
				
				try{
					ae=list.get(select);
				}catch(Exception e){
					msg.sendWarningMessage("序号错误.");
					continue;
				}
				
				String pwd=AdvHash.AESDncode(rules, ae.getPassword());
				
				if(pwd==null){
					msg.sendWarningMessage("解码失败,rule错误.");
					continue;
				}
				
				//校验完成 选择获取方式
				System.out.print("选择方案:1 and 2:");
							
				try{
					select=new Integer(msg.getMessage());
				}catch(Exception e){
					continue;
				}
				
				if(select == 1){
					msg.sendWarningMessage("方案1:已复制到粘贴板!");
					Var.setClipboardString(ae.getName());
				}
				
				if(select == 2){
					msg.sendWarningMessage("方案2:已复制到粘贴板!");
					Var.setClipboardString(pwd);			
				}
				

				
//				msg.sendWarningMessage("已复制到粘贴板!");
//				Var.setClipboardString(pwd);
				
				continue;
				
			}
			
			//添加
			if(command.contains("add")){
				
				
				AccountEntity ae=new AccountEntity();

				
				try{
					ae.setName( command.split(",")[1]);
					ae.setPassword(  command.split(",")[2]);
					ae.setTitle( command.split(",")[3]);
				}catch(Exception e){
					System.out.println("添加语法: add,Name,pwd,Title");
					continue;
				}
				
				this.addAccount(ae);	
				msg.sendWarningMessage("操作成功");
				msg.sendBr();
				continue;
				
			}
			
			//删除
			if(command.contains("del")){
				
				msg.sendBr();
				
				String delTarget = null;
				
				try{
					delTarget = command.split(",")[1];
				}catch(Exception e){
					System.out.println("删除语法: del,要搜索的内容");
					continue;
				}
				
				ArrayList<AccountEntity> list=this.showAccount("select * from "+Table_Account+" where "+Field_AccountTitle+" like '%"+delTarget+"%' order by name");
				
				System.out.print("选择:");
				
				int select=-2;
				
				try{
					select=new Integer(msg.getMessage());
				}catch(Exception e){
					continue;
				}
				
				AccountEntity ae=null;
				
				try{
					ae=list.get(select);
				}catch(Exception e){
					msg.sendWarningMessage("序号错误.");
					continue;
				}		
				
				if(AdvHash.AESDncode(rules, ae.getPassword()) == null){
					msg.sendWarningMessage("rule错误!");
					continue;
				}
				
				this.delAccountByTitle(ae.getTitle());
				msg.sendWarningMessage("操作成功");
				msg.sendBr();
				continue;
				
			}
			
			//退出
			if(command.equalsIgnoreCase("quit")){
				return;
			}
			
			//规则测试
			if(command.equalsIgnoreCase("test")){
				
				
				
				ArrayList<AccountEntity> list=this.getAccount("select * from "+Table_Account+" where "+Field_Account+"='RuleTest'");
				
				if(AdvHash.AESDncode(rules, list.get(0).getPassword())==null){
					msg.sendWarningMessage("测试不通过.");
					continue;
				}
				
				msg.sendWarningMessage("rule正确");
				continue;
				
				
			}
			
			
			
			msg.sendWarningMessage("子程序Account的命令:");
			msg.sendWarningMessage("show");
			msg.sendWarningMessage("search,?");
			msg.sendWarningMessage("del,?");
			msg.sendWarningMessage("add,?,?,?");
			msg.sendWarningMessage("quit");
			
			
		}
		
		
	}

	
	@Override
	public Command_cmd getThis() {
		
		return this;
	}

	
	
	public AccountEntity getAccountByName(String name){
		
		String sql = "select * from "+Table_Account+" where name='"+name+"'";

		ResultSet rs=dao.query(sql);
		
		AccountEntity ae=new AccountEntity();
		
		try {
			
			while(rs.next()){
				ae.setName(rs.getString(Field_Account));
				ae.setPassword(rs.getString(Field_Password));
				ae.setTitle(rs.getString(Field_AccountTitle));
				
			}
			
			return ae;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	
	
	
	
	public void addAccount(AccountEntity ae){	
		
		String sql="insert into "+Table_Account+" values('"+ae.getName()+"','"+AdvHash.AESEncode(rules, ae.getPassword())+"','','"+ae.getTitle()+"');";
		
		dao.noQuery(sql);
	}
	
	
	public void delAccountByTitle(String del){	
		
		String sql="delete "+Table_Account+" where "+Field_AccountTitle+"='"+del+"'";
		
		dao.noQuery(sql);
		

	}
	
	
	public ArrayList<AccountEntity> getAccount(String sql1){
		
		String sql=sql1;
		
		ResultSet rs=dao.query(sql);
		
		ArrayList<AccountEntity> list=new ArrayList<AccountEntity>();
		
		
		try {
			//从数据库获取数据到List
			while(rs.next()){
				
				AccountEntity ae=new AccountEntity();
				
				ae.setName(rs.getString(Field_Account));
				ae.setPassword(rs.getString(Field_Password));
				ae.setTitle(rs.getString(Field_AccountTitle));
				
				list.add(ae);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	
	
	public ArrayList<AccountEntity> showAccount(String sql1){
		
		String sql=sql1;
		
		ResultSet rs=dao.query(sql);
		
		ArrayList<AccountEntity> list=new ArrayList<AccountEntity>();
		
		
		try {
			//从数据库获取数据到List
			while(rs.next()){
				
				AccountEntity ae=new AccountEntity();
				
				ae.setName(rs.getString(Field_Account));
				ae.setPassword(rs.getString(Field_Password));
				ae.setTitle(rs.getString(Field_AccountTitle));
				
				list.add(ae);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		String R2="序列|    账号名    |   Title   ";
		System.out.println(R2);
		
		String name="";
		String title="";
		
		int i=-1;
		for(AccountEntity ae:list){
			i++;
//			name="|"+ae.getName();
			name="|"+String.valueOf(ae.getName().charAt(0))+"*****";
			title=""+ae.getTitle();
			
			
			while(name.length()<=11){
				name=name+" ";
			}
			name=name+"|";
			
			
			while(title.length()<=32){
				title=title+" ";
			}
			title=title+"|";
			
			
			System.out.println(i+"."+name+title);	
		}
		
		
		return list;
	}
	
	
}
