package uk.iksp.asmc.entity.identity;

public class Identity {

	private int id = 0;
	
	private String identityName = null;
	
	private String identityMetadata = null;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdentityName() {
		return identityName;
	}

	public void setIdentityName(String identityName) {
		this.identityName = identityName;
	}

	public String getIdentityMetadata() {
		return identityMetadata;
	}

	public void setIdentityMetadata(String identityMetadata) {
		this.identityMetadata = identityMetadata;
	}
	
}
