package org.nerditest.server.datastore;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class QuestionDS {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private int questionId;

	@Persistent
	private String question;

	@Persistent
	private String answer1;


	@Persistent
	private String answer2;


	@Persistent
	private String answer3;


	@Persistent
	private String answer4;

	@Persistent
	private int correctAnswer;


	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	public String getAnswer4() {
		return answer4;
	}

	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}

	public int getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public QuestionDS(int questionId,String question, String answer1, String answer2,
			String answer3, String answer4, int correctAnswer) {
		super();
		this.question = question;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
		this.correctAnswer = correctAnswer;
		this.questionId = questionId;
	}
	public QuestionDS(){
		
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getQuestionId() {
		return questionId;
	}
}
