package org.nerditest.server.datastore;
import com.google.appengine.api.datastore.Key;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class AnswerDS {
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    @Persistent
    private String peekName;
    
    @Persistent
    private String teamName;
    
    @Persistent
    private boolean isCorrect;
    
    @Persistent
    private int questionId;
    
    public AnswerDS(){
    	
    }
	public AnswerDS(String peekName, String teamName, boolean isCorrect, int questionId) {
		super();
		this.peekName = peekName;
		this.teamName = teamName;
		this.isCorrect = isCorrect;
		this.questionId = questionId;
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
	public void setKey(Key key) {
		this.key = key;
	}
	public Key getKey() {
		return key;
	}
    
    
}