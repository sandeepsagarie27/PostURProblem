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
import com.postTheProblem.ServletResponse;
import com.postTheProblem.database.JDBC_Connection;

/**
 * Servlet implementation class DistrictsServlet
 */
@WebServlet("/get_districts")
public class DistrictsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DistrictsServlet() {
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
		try {
			ArrayList<District> districts = JDBC_Connection.getConnection().getDistricts();
			if (districts != null) {
				servletResponse.setStatus_code(AppConstants.STATUS_CODE_SUCCESS);
				servletResponse.setStatus_message(AppConstants.STATUS_MESSAGE_SUCCESS);
				servletResponse.setResponse(districts);
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
