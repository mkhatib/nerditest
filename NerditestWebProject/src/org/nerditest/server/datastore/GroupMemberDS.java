package org.nerditest.server.datastore;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class GroupMemberDS {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	@Persistent
	private String peekName;
	@Persistent
	private String teamName;
	
	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getPeekName() {
		return peekName;
	}

	public void setPeekName(String peekName) {
		this.peekName = peekName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public GroupMemberDS(){

	}

	public GroupMemberDS(String peekName, String teamName) {
		super();
		this.peekName = peekName;
		this.teamName = teamName;
	}
}
