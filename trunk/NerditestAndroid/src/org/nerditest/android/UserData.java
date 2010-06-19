package org.nerditest.android;

import java.util.List;

public class UserData {
    private List<Question> questions;
    private String team;

    public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
    
}
