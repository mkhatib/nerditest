package org.nerditest.client;

import java.util.List;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class AnswerMgr {

	private final AnswerSvcAsync service = GWT
	.create(AnswerSvc.class);

	public void getAllGroupMembers() {

		service.getAllGroupMembers(new AsyncCallback<List<GroupMember>>() {
			@Override
			public void onSuccess(List<GroupMember> results) {
				StringBuilder namesAndGroups = new StringBuilder();
				for (GroupMember groupMember : results) {
					namesAndGroups.append(groupMember.getPeekName());
					namesAndGroups.append(" - ");
					namesAndGroups.append(groupMember.getTeamName() + "");
					namesAndGroups.append("<br />");
				}
				HTML namesNumbers = new HTML(namesAndGroups.toString());
				RootPanel.get().add(namesNumbers);
			}			
			@Override
			public void onFailure(Throwable caught) {

			}
		});
	}


	public void saveGroupMember(GroupMember groupMember) {		
		service.saveGroupMember(groupMember, new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
				Window.alert("Succeeded");
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Faild");
			}
		});
	}

	public void deleteAllGroupMembers() {
		service.deleteAllGroupMembers(new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Failed");
			}

			@Override
			public void onSuccess(Void result) {	
				Window.alert("Succeeded");
			}
		});

	}


	public void saveNewQuestion(Question question) {
		service.saveNewQuestion(question, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				Window.alert("Succeeded");
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Failed");
				
			}
		});		
	}


	public void getAllQuestions() {
		service.getAllQuestions(new AsyncCallback<List<Question>>() {
			@Override
			public void onSuccess(List<Question> results) {
				StringBuilder namesAndGroups = new StringBuilder();
				for (Question question : results) {
					namesAndGroups.append("Q: " + question.getQuestion() + "<br />");
					namesAndGroups.append("Choices: <br />");
					namesAndGroups.append("1- " + question.getAnswer1() + "<br />");
					namesAndGroups.append("2- " + question.getAnswer2() + "<br />");
					namesAndGroups.append("3- " + question.getAnswer3() + "<br />");
					namesAndGroups.append("4- " + question.getAnswer4() + "<br />");
					namesAndGroups.append("Corrcect Answer: " + question.getCorrectAnswer() + "<br />");					
				}
				HTML namesNumbers = new HTML(namesAndGroups.toString());
				RootPanel.get().add(namesNumbers);
			}			
			@Override
			public void onFailure(Throwable caught) {

			}
		});
	}


	public void deleteAllQuestions() {
		service.deleteAllQuestions(new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				Window.alert("Succeeded");
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Failed");
				
			}
		});		
	}


	public void getGroupsResults() {
		service.getGroupsResults(new AsyncCallback<List<Answer>>() {
			
			@Override
			public void onSuccess(List<Answer> result) {
				//handle the graphs here
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Failed");				
			}
		});
	}
}
