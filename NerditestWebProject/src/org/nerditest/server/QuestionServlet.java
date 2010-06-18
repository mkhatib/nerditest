package org.nerditest.server;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nerditest.server.datastore.AnswerDS;
import org.nerditest.server.datastore.GroupMemberDS;
import org.nerditest.server.datastore.PMF;
import org.nerditest.server.datastore.QuestionDS;

@SuppressWarnings("serial")
public class QuestionServlet extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws IOException {
		String [] splits = req.getRequestURI().split("/");
		String userName = "";

		if(splits.length > 1){
			userName = splits[2];

			System.out.println(splits[2]);

			PersistenceManager pm = PMF.get().getPersistenceManager();
			List<QuestionDS> questions = new ArrayList<QuestionDS>();
			int totalNumberOfQuestoins = ((List<QuestionDS>) pm.newQuery(QuestionDS.class).execute()).size(); 
			int numOfAlreadyAnsweredQ = ((List<AnswerDS>) pm.newQuery(AnswerDS.class,"peekName =='"+userName+ "'").execute()).size();
			System.out.println("totalNumberOfQuestoins = " + totalNumberOfQuestoins + ", numOfAlreadyAnsweredQ=" + numOfAlreadyAnsweredQ);
			if(numOfAlreadyAnsweredQ < totalNumberOfQuestoins){						
				
				Query query = pm.newQuery("select from "+QuestionDS.class.getName() + " where questionId > " + numOfAlreadyAnsweredQ);
				try{
					questions = (List<QuestionDS>) query.execute();
				}
				finally{
					query.closeAll();
				}
				System.out.println("questions num = " + questions.size());
				Query teamQ = pm.newQuery(GroupMemberDS.class,"peekName== '"+userName + "'");
				List<GroupMemberDS> member = (List<GroupMemberDS>) teamQ.execute();
				if(member != null && member.size() != 0){
					StringBuilder jsonResponse = new StringBuilder();
					jsonResponse.append("{\"team\":\""+member.get(0).getTeamName()+"\",\"questions\":[");
					for (QuestionDS questionDS : questions) {
						//{"questions":[{"number":1,"body":"First Question Body"},{"number":2,"body":"Second Question Body"}]}
						jsonResponse.append("{\"number\":" + questionDS.getQuestionId() + ",\"body\":\"" + questionDS.getQuestion()
								+"\",\"answers\":[\"" + questionDS.getAnswer1() + "\",\"" + questionDS.getAnswer2()
								+ "\",\"" + questionDS.getAnswer3()+ "\",\"" + questionDS.getAnswer4() + "\"]},");		
					}
					if(!questions.isEmpty()) jsonResponse.deleteCharAt(jsonResponse.length()-1);
					jsonResponse.append("]}");
					resp.getWriter().write(jsonResponse.toString());
				}
				else{ 
					resp.getWriter().write("{\"error\":\"No team for this user\"}");			
					System.out.println("Username is not found");
				}
			}
			else resp.getWriter().write("{\"error\":\"User already did the quiz\"}");
		}
		else resp.getWriter().write("{\"error\":\"url error\"}");
	}

}
