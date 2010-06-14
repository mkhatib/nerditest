package org.nerditest.client;

import java.io.Serializable;

@SuppressWarnings("serial")
public class GroupMember implements Serializable {
	
	
	private String peekName;
	private String teamName;
	
	public GroupMember(){
		
	}

	public GroupMember(String peekName, String teamName) {
		super();
		this.peekName = peekName;
		this.teamName = teamName;
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
	

}
