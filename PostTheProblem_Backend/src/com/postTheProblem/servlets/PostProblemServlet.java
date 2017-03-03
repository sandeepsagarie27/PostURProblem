package com.postTheProblem.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.postTheProblem.AppConstants;
import com.postTheProblem.Issue;
import com.postTheProblem.ServletResponse;
import com.postTheProblem.database.JDBC_Connection;

/**
 * Servlet implementation class PostProblemServlet
 */
@WebServlet("/post_problem")
public class PostProblemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PostProblemServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletResponse servletResponse = new ServletResponse();
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		StringBuilder sb = new StringBuilder();
		String s;
		while ((s = request.getReader().readLine()) != null) {
			sb.append(s);
		}
		Issue issue = new Gson().fromJson(sb.toString(), Issue.class);
		try {
			String reference_num = JDBC_Connection.getConnection().submitIssue(issue);
			if (issue != null && issue.getDistrict_id() > 0 && issue.getMandal_id() >= 0 && !reference_num.isEmpty()) {
				servletResponse.setStatus_code(AppConstants.STATUS_CODE_SUCCESS);
				servletResponse.setStatus_message(AppConstants.STATUS_MESSAGE_SUCCESS);
				servletResponse.setResponse(reference_num);
				Thread thread=new  Thread(new Runnable() {
					
					@Override
					public void run() {
						sendEmail(issue);
				
					}
				});
				thread.start();
				
			} else {
				servletResponse.setStatus_code(AppConstants.STATUS_CODE_FAILURE);
				servletResponse.setStatus_message("Invalid Parameters");
				servletResponse.setResponse(null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			servletResponse.setStatus_code(AppConstants.STATUS_CODE_FAILURE);
			servletResponse.setStatus_message(e.getMessage());
			servletResponse.setResponse(null);
		}

		out.write(new Gson().toJson(servletResponse));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void sendEmail(Issue issue) {
		
	 
	      // Sender's email ID needs to be mentioned
	      String from = "help.posturproblem@gmail.com";	 
	      Properties props = new Properties();    
          props.put("mail.smtp.host", "smtp.gmail.com");    
          props.put("mail.smtp.socketFactory.port", "465");    
          props.put("mail.smtp.socketFactory.class",    
                    "javax.net.ssl.SSLSocketFactory");    
          props.put("mail.smtp.auth", "true");    
          props.put("mail.smtp.port", "465");    
          //get Session   
          Session session = Session.getDefaultInstance(props,    
           new javax.mail.Authenticator() {    
           protected PasswordAuthentication getPasswordAuthentication() {    
           return new PasswordAuthentication(from,"postURproblem");  
           }    
          });  
	      
		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));
			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(issue.getPetitioner_email()));
			// Set Subject: header field
			message.setSubject("Thank you for sharing the problem with us.");
			// Now set the actual message
			message.setText("Hi,\n\nThis is the reference number for your concern is: "+issue.getReference_id()+".\n\nWe will update you the status with neccesary action taken.\nThank you.");
			// Send message
			Transport.send(message);
		
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}
