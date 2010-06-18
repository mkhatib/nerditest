package org.nerditest.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class NerditestWebProject implements EntryPoint {
	
	public String [] teams = {"mega","giga","tera","peta","exa","zetta","yotta","xona","weka","vunda"};
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		Button saveGroupMemberBtn = new Button("Save Group Member");
		Button deleteBtn = new Button("Clean Group Members Table");
		final AnswerMgr answerMgr = new AnswerMgr();
		final ListBox teamsListBox = new ListBox();
		final TextBox nameTextBox = new TextBox();
		final GroupMember groupMember = new GroupMember();	
		
		Button saveNewQuestion= new Button ("Save Question");
		final TextArea questionTextArea = new TextArea();
		final TextBox questionId = new  TextBox();
		final TextBox answer1 = new TextBox();
		final TextBox answer2 = new TextBox();
		final TextBox answer3 = new TextBox();
		final TextBox answer4 = new TextBox();
		final ListBox correctAnswer = new ListBox();
		
		correctAnswer.addItem("1");
		correctAnswer.addItem("2");
		correctAnswer.addItem("3");
		correctAnswer.addItem("4");
		
		for (String team : teams) {
			teamsListBox.addItem(team);
		}
		
		final Question question = new Question();
		saveNewQuestion.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				question.setQuestionId(Integer.parseInt(questionId.getValue()));
				question.setQuestion(questionTextArea.getValue());
				question.setAnswer1(answer1.getValue());	
				question.setAnswer2(answer2.getValue());	
				question.setAnswer3(answer3.getValue());
				question.setAnswer4(answer4.getValue());
				question.setCorrectAnswer(Integer.parseInt(correctAnswer.getValue(correctAnswer.getSelectedIndex())));
				answerMgr.saveNewQuestion(question);					
				
			}
		});
		
		saveGroupMemberBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				groupMember.setPeekName(nameTextBox.getValue());
				groupMember.setTeamName(teamsListBox.getItemText(teamsListBox.getSelectedIndex()));				
				answerMgr.saveGroupMember(groupMember);								
			}
		});
		
		Button showAllGroupMembers = new Button("Show Members and Groups");
		showAllGroupMembers.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				answerMgr.getAllGroupMembers();
				
			}
		});
		
		Button showAllQuestionsAndAnswers = new Button("Show All Questions And Answers");
		
		showAllQuestionsAndAnswers.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				answerMgr.getAllQuestions();				
			}
		});
		
		Button deleteAllQuestions = new Button("Clean Questions Table");
		
		deleteAllQuestions.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				answerMgr.deleteAllQuestions();
				
			}
		});
		
		deleteBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				answerMgr.deleteAllGroupMembers();
				
			}
		});
		
		Button groupsResults = new Button("Group Results"); 
		
		groupsResults.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				answerMgr.getGroupsResults(teams);				
			}
		});
		
		RootPanel.get().add(new Label("Name: "));
		RootPanel.get().add(nameTextBox);
		RootPanel.get().add(new Label("Group: "));
		RootPanel.get().add(teamsListBox);
		RootPanel.get().add(new HTML("<br />"));
		RootPanel.get().add(saveGroupMemberBtn);
		RootPanel.get().add(showAllGroupMembers);
		RootPanel.get().add(new HTML("<hr>"));
		RootPanel.get().add(new Label("Question Id: "));
		RootPanel.get().add(questionId);
		RootPanel.get().add(new Label("Question: "));
		RootPanel.get().add(questionTextArea);
		RootPanel.get().add(new Label("Answer 1: "));
		RootPanel.get().add(answer1);
		RootPanel.get().add(new Label("Answer 2"));
		RootPanel.get().add(answer2);
		RootPanel.get().add(new Label("Answer 3: "));
		RootPanel.get().add(answer3);
		RootPanel.get().add(new Label("Answer 4: "));
		RootPanel.get().add(answer4);
		RootPanel.get().add(new Label("Correct Answer #: "));
		RootPanel.get().add(correctAnswer);
		RootPanel.get().add(new HTML("<br />"));
		RootPanel.get().add(saveNewQuestion);
		
		RootPanel.get().add(showAllQuestionsAndAnswers);
		RootPanel.get().add(new HTML("<hr>"));
		
		Button showResults = new Button("Show Results");
		
		showResults.addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				new results();				
			}
		});
		
		RootPanel.get().add(showResults);
		RootPanel.get().add(new HTML("<hr>"));
		RootPanel.get().add(deleteBtn);
		RootPanel.get().add(deleteAllQuestions);
		
		Button deleteAllAnswers = new Button("Clean Answers Table"); 
		
		deleteAllAnswers.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				answerMgr.deleteAllAnswers();
				
			}
		});
		RootPanel.get().add(deleteAllAnswers);
		
	}
}
