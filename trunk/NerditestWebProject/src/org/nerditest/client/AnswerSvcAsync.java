package org.nerditest.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AnswerSvcAsync {
	
	void getAllGroupMembers(AsyncCallback<List<GroupMember>> callback);
	void saveGroupMember(GroupMember groupMember, AsyncCallback<Void> callback);
	void deleteAllGroupMembers(AsyncCallback<Void> callback);
	void saveNewQuestion(Question question, AsyncCallback<Void> callback);
	void getAllQuestions(AsyncCallback<List<Question>> callback);
	void deleteAllQuestions(AsyncCallback<Void> callback);
	void getGroupsResults(String [] teams, AsyncCallback<List<TeamResult>> callback);
	void deleteAllAnswers(AsyncCallback<Void> asyncCallback);

}
