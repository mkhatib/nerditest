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

@SuppressWarnings("serial")
public class ResultServlet extends HttpServlet{

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		String userName = req.getRequestURI().split("/")[2];
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(AnswerDS.class,"peekName== '"+userName + "'");
		List<AnswerDS> userAnswers = (List<AnswerDS>) query.execute();
		query.closeAll();
		float result = 0;
		System.out.println(userAnswers.size());
		for (AnswerDS answerDS : userAnswers) {
			if(answerDS.isCorrect())result++;
		}
		float avg = ((result/userAnswers.size())*100); 

		resp.getWriter().write(avg + "");

	}

}
