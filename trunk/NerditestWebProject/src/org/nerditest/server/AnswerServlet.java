package org.nerditest.server;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nerditest.server.datastore.AnswerDS;
import org.nerditest.server.datastore.PMF;

@SuppressWarnings("serial")
public class AnswerServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/plain");
        String url = req.getRequestURI();
        String [] splits = url.split("/");
        
        AnswerDS answer = new AnswerDS();        
        answer.setTeamName(splits[2]);        
		answer.setPeekName(splits[3]);		
		answer.setQuestionId(Integer.parseInt(splits[4]));
		
		answer.setCorrect(true);
		
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			pm.makePersistent(answer);
		} finally {
			pm.close();
		}
        
        resp.getWriter().write("Succeeded");        
    }   
    
    public void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	
    }
}