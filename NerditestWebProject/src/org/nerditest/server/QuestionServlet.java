package org.nerditest.server;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.nerditest.server.datastore.PMF;
import org.nerditest.server.datastore.QuestionDS;

@SuppressWarnings("serial")
public class QuestionServlet extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<QuestionDS> questions = new ArrayList<QuestionDS>();
		Query query = pm.newQuery(QuestionDS.class);
		try{
			questions = (List<QuestionDS>) query.execute();
		}
		finally{
			query.closeAll();
		}
		StringBuilder jsonResponse = new StringBuilder();
		jsonResponse.append("{\"questions\":[");
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

}
