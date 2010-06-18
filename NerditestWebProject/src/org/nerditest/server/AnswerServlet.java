package org.nerditest.server;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.nerditest.server.datastore.AnswerDS;
import org.nerditest.server.datastore.PMF;
import org.nerditest.server.datastore.QuestionDS;

@SuppressWarnings("serial")
public class AnswerServlet extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws IOException {
		System.out.println("I am in the doPost");
		resp.setContentType("text/plain");
		String url = req.getRequestURI();
		String [] splits = url.split("/");  
		resp.getWriter().write(url);
		AnswerDS answer = new AnswerDS();        
		answer.setTeamName(splits[2]);        
		answer.setPeekName(splits[4]);		
		answer.setQuestionId(Integer.parseInt(splits[6]));	
		PersistenceManager pm = PMF.get().getPersistenceManager();
		for (String split : splits) {
			System.out.print(split + " ");			
		}

		Query query = pm.newQuery("select from " +QuestionDS.class.getName() + " where questionId == " + answer.getQuestionId());
		List<QuestionDS> question = (List<QuestionDS>) query.execute();
		System.out.println("number of question to be answered = " + question.size());
		answer.setCorrect(question.get(0).getCorrectAnswer()==Integer.parseInt(splits[8]));
		System.out.println("");
		System.out.println(question.get(0).getCorrectAnswer()==Integer.parseInt(splits[8]));
		try {
			pm.makePersistent(answer);
		} finally {
			pm.close();
		}
		resp.getWriter().write("Succeeded");        
	}   
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
		System.out.println("I am in the doGet");
	}

	public void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {

	}
}