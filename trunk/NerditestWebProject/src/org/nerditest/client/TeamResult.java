package org.nerditest.client;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TeamResult implements Serializable{

	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public int getTeamResult() {
		return teamResult;
	}
	public void setTeamResult(int teamResult) {
		this.teamResult = teamResult;
	}
	private String teamName;
	private int teamResult;

	public TeamResult(){

	}
	public TeamResult(String teamName, int teamResult){
		super();
		setTeamName(teamName);
		setTeamResult(teamResult);
	}

}
