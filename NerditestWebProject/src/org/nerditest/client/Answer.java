package org.nerditest.client;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Answer implements Serializable {
	
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

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public Answer(String peekName, String teamName, boolean isCorrect,
			int questionId) {
		super();
		this.peekName = peekName;
		this.teamName = teamName;
		this.isCorrect = isCorrect;
		this.questionId = questionId;
	}

	private String peekName;
    private String teamName;
    private boolean isCorrect;
    private int questionId;

	public Answer(){}
	
}
