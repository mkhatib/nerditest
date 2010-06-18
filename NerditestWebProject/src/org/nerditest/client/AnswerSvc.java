package org.nerditest.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("nerditest")
public interface AnswerSvc extends RemoteService {
	
	public List<GroupMember> getAllGroupMembers() throws AnswerSvcException;
	public void saveGroupMember(GroupMember groupMember) throws AnswerSvcException;
	public void deleteAllGroupMembers() throws AnswerSvcException;
	public void saveNewQuestion(Question question) throws AnswerSvcException;
	public List<Question> getAllQuestions() throws AnswerSvcException;
	public void deleteAllQuestions() throws AnswerSvcException;
	public List<TeamResult> getGroupsResults(String [] teams) throws AnswerSvcException;
	public void deleteAllAnswers() throws AnswerSvcException;
	
}
