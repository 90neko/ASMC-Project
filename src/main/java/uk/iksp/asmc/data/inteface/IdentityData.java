package uk.iksp.asmc.data.inteface;

import java.util.ArrayList;

import uk.iksp.asmc.entity.identity.Identity;

public interface IdentityData {

	
	/**
	 * 获取全部的身份
	 */
	public ArrayList<Identity> getIdentity();
	
	/**
	 * 获取指定身份
	 */
	public Identity getIdentity(String name);
	
	/**
	 * 获取指定身份
	 */
	public Identity getIdentity(int id);
	
	/**
	 * 添加新的身份
	 */
	public void updateIdentity(Identity identity);
	
	
}
