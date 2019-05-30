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
		
		System.out.print("����Rule:");
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
			
			
			//�˵�Show
			if(command.equalsIgnoreCase("show")){
				msg.sendBr();
				this.showAccount("select * from "+Table_Account+" order by "+Field_AccountTitle+"");
				continue;
			}
			
			
			//search����
			if(command.contains("search")){
				msg.sendBr();
				String searchTarget = null;
				
				try{
					searchTarget = command.split(",")[1];
				}catch(Exception e){
					System.out.println("�����﷨: search,����");
					continue;
				}
				
				ArrayList<AccountEntity> list=this.showAccount("select * from "+Table_Account+" where "+Field_AccountTitle+" like '%"+searchTarget+"%' order by name");
				msg.sendBr();
				
				if(list.size()<1){
					msg.sendWarningMessage("�˴β�ѯû�з����κν��.");
					continue;
				}
				
				
				
				System.out.print("ѡ��:");
				
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
					msg.sendWarningMessage("��Ŵ���.");
					continue;
				}
				
				String pwd=AdvHash.AESDncode(rules, ae.getPassword());
				
				if(pwd==null){
					msg.sendWarningMessage("����ʧ��,rule����.");
					continue;
				}
				
				//У����� ѡ���ȡ��ʽ
				System.out.print("ѡ�񷽰�:1 and 2:");
							
				try{
					select=new Integer(msg.getMessage());
				}catch(Exception e){
					continue;
				}
				
				if(select == 1){
					msg.sendWarningMessage("����1:�Ѹ��Ƶ�ճ����!");
					Var.setClipboardString(ae.getName());
				}
				
				if(select == 2){
					msg.sendWarningMessage("����2:�Ѹ��Ƶ�ճ����!");
					Var.setClipboardString(pwd);			
				}
				

				
//				msg.sendWarningMessage("�Ѹ��Ƶ�ճ����!");
//				Var.setClipboardString(pwd);
				
				continue;
				
			}
			
			//���
			if(command.contains("add")){
				
				
				AccountEntity ae=new AccountEntity();

				
				try{
					ae.setName( command.split(",")[1]);
					ae.setPassword(  command.split(",")[2]);
					ae.setTitle( command.split(",")[3]);
				}catch(Exception e){
					System.out.println("����﷨: add,Name,pwd,Title");
					continue;
				}
				
				this.addAccount(ae);	
				msg.sendWarningMessage("�����ɹ�");
				msg.sendBr();
				continue;
				
			}
			
			//ɾ��
			if(command.contains("del")){
				
				msg.sendBr();
				
				String delTarget = null;
				
				try{
					delTarget = command.split(",")[1];
				}catch(Exception e){
					System.out.println("ɾ���﷨: del,Ҫ����������");
					continue;
				}
				
				ArrayList<AccountEntity> list=this.showAccount("select * from "+Table_Account+" where "+Field_AccountTitle+" like '%"+delTarget+"%' order by name");
				
				System.out.print("ѡ��:");
				
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
					msg.sendWarningMessage("��Ŵ���.");
					continue;
				}		
				
				if(AdvHash.AESDncode(rules, ae.getPassword()) == null){
					msg.sendWarningMessage("rule����!");
					continue;
				}
				
				this.delAccountByTitle(ae.getTitle());
				msg.sendWarningMessage("�����ɹ�");
				msg.sendBr();
				continue;
				
			}
			
			//�˳�
			if(command.equalsIgnoreCase("quit")){
				return;
			}
			
			//�������
			if(command.equalsIgnoreCase("test")){
				
				
				
				ArrayList<AccountEntity> list=this.getAccount("select * from "+Table_Account+" where "+Field_Account+"='RuleTest'");
				
				if(AdvHash.AESDncode(rules, list.get(0).getPassword())==null){
					msg.sendWarningMessage("���Բ�ͨ��.");
					continue;
				}
				
				msg.sendWarningMessage("rule��ȷ");
				continue;
				
				
			}
			
			
			
			msg.sendWarningMessage("�ӳ���Account������:");
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
			//�����ݿ��ȡ���ݵ�List
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
			//�����ݿ��ȡ���ݵ�List
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
		
		
		String R2="����|    �˺���    |   Title   ";
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
