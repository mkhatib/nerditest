package org.nerditest.server;
import java.util.ArrayList;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import org.nerditest.client.Answer;
import org.nerditest.client.AnswerSvc;
import org.nerditest.client.AnswerSvcException;
import org.nerditest.client.GroupMember;
import org.nerditest.client.Question;
import org.nerditest.server.datastore.AnswerDS;
import org.nerditest.server.datastore.GroupMemberDS;
import org.nerditest.server.datastore.PMF;
import org.nerditest.server.datastore.QuestionDS;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ServerServiceImpl extends RemoteServiceServlet implements AnswerSvc {

	
	@Override
	public void saveGroupMember(GroupMember groupMember) throws AnswerSvcException {
		GroupMemberDS groupMemberDS = new GroupMemberDS();
		groupMemberDS.setPeekName(groupMember.getPeekName());
		groupMemberDS.setTeamName(groupMember.getTeamName());
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			pm.makePersistent(groupMemberDS);
		} finally {
			pm.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GroupMember> getAllGroupMembers() throws AnswerSvcException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<GroupMemberDS> groupMembers = new ArrayList<GroupMemberDS>();
		Query query = pm.newQuery(GroupMemberDS.class);
		try{
			groupMembers = (List<GroupMemberDS>) query.execute();
		}
		finally{
			query.closeAll();
		}
		List<GroupMember> groupMembersDtos = new ArrayList<GroupMember>();
		for (GroupMemberDS groupMember : groupMembers) {
			groupMembersDtos.add(new GroupMember(groupMember.getPeekName(),groupMember.getTeamName()));
		}
		return groupMembersDtos;
	}


	@Override
	public void deleteAllGroupMembers() throws AnswerSvcException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(GroupMemberDS.class);
		try{
			 query.deletePersistentAll();
		}
		finally{
			query.closeAll();
		}
	}

	@Override
	public void saveNewQuestion(Question question) throws AnswerSvcException{
		QuestionDS questionDS = new QuestionDS();
		questionDS.setQuestionId(question.getQuestionId());
		questionDS.setQuestion(question.getQuestion());
		questionDS.setAnswer1(question.getAnswer1());
		questionDS.setAnswer2(question.getAnswer2());
		questionDS.setAnswer3(question.getAnswer3());
		questionDS.setAnswer4(question.getAnswer4());
		questionDS.setCorrectAnswer(question.getCorrectAnswer());
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(questionDS);
		} finally {
			pm.close();
		}		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> getAllQuestions() throws AnswerSvcException{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<QuestionDS> questions = new ArrayList<QuestionDS>();
		Query query = pm.newQuery(QuestionDS.class);
		try{
			questions = (List<QuestionDS>) query.execute();
		}
		finally{
			query.closeAll();
		}
		List<Question> questionDtos = new ArrayList<Question>();
		for (QuestionDS question : questions) {
			questionDtos.add(new Question(question.getQuestionId(),question.getQuestion(),question.getAnswer1(),question.getAnswer2(),question.getAnswer3(),question.getAnswer4(),question.getCorrectAnswer()));
		}
		return questionDtos;
				
	}
	
	public void deleteAllQuestions() throws AnswerSvcException{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(QuestionDS.class);
		try{
			 query.deletePersistentAll();
		}
		finally{
			query.closeAll();
		}		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Answer> getGroupsResults() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<AnswerDS> answers = new ArrayList<AnswerDS>();
		Query query = pm.newQuery(AnswerDS.class);
		try{
			answers = (List<AnswerDS>) query.execute();
		}
		finally{
			query.closeAll();
		}
		List<Answer> answerDtos = new ArrayList<Answer>();
		for (AnswerDS answer : answers) {
			answerDtos.add(new Answer(answer.getPeekName(),answer.getTeamName(),answer.isCorrect(),answer.getQuestionId()));
		}
		return answerDtos;
	}
}
