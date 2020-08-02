package uk.iksp.asmc.entity.identity;

public class Account {

	private int id = 0;
	
	private int from_identity_id = 0;
	
	private String account = null;
	
	private String password = null;
	
	private String descript = null;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFrom_identity_id() {
		return from_identity_id;
	}

	public void setFrom_identity_id(int from_identity_id) {
		this.from_identity_id = from_identity_id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}
	
	
}
