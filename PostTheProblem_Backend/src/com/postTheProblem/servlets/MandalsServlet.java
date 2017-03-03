package com.postTheProblem.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.postTheProblem.AppConstants;
import com.postTheProblem.District;
import com.postTheProblem.Mandal;
import com.postTheProblem.ServletResponse;
import com.postTheProblem.database.JDBC_Connection;

/**
 * Servlet implementation class DistrictsServlet
 */
@WebServlet("/get_mandals")
public class MandalsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MandalsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @throws IOException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ServletResponse servletResponse = new ServletResponse();
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		StringBuilder sb = new StringBuilder();
		String s;
		while ((s = request.getReader().readLine()) != null) {
			sb.append(s);
		}
		Mandal mandal = new Gson().fromJson(sb.toString(), Mandal.class);
		if (mandal==null||mandal.getDist_id()<=0) {
			servletResponse.setStatus_code(AppConstants.STATUS_CODE_FAILURE);
			servletResponse.setStatus_message("Invalid Parameter, Please send district ID");
			servletResponse.setResponse(null);
		} else {
			
			try {

				ArrayList<Mandal> mandals = JDBC_Connection.getConnection().getMandals(mandal.getDist_id());
				if (mandals != null) {
					servletResponse.setStatus_code(AppConstants.STATUS_CODE_SUCCESS);
					servletResponse.setStatus_message(AppConstants.STATUS_MESSAGE_SUCCESS);
					servletResponse.setResponse(mandals);
				} else {
					servletResponse.setStatus_code(AppConstants.STATUS_CODE_FAILURE);
					servletResponse.setStatus_message(AppConstants.STATUS_MESSAGE_FAILURE);
					servletResponse.setResponse(null);
				}

			} catch (Exception e) {
				servletResponse.setStatus_code(AppConstants.STATUS_CODE_FAILURE);
				servletResponse.setStatus_message(e.getMessage());
				servletResponse.setResponse(null);
			}

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

}
