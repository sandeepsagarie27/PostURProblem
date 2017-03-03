package com.postTheProblem.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.postTheProblem.AppConstants;
import com.postTheProblem.District;
import com.postTheProblem.Issue;
import com.postTheProblem.Mandal;

public class JDBC_Connection {
	private static JDBC_Connection connection;
	private Connection con;

	private JDBC_Connection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection(AppConstants.DB_URL, AppConstants.USER, AppConstants.PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static JDBC_Connection getConnection() {
		if (connection == null) {
			connection = new JDBC_Connection();
		}
		return connection;
	}

	public ArrayList<District> getDistricts() throws SQLException {
		ArrayList<District> districts = new ArrayList<>();

		ResultSet resultSet = con.createStatement().executeQuery("Select* from districts");
		while (resultSet != null && resultSet.next()) {
			District district = new District(resultSet.getInt("dist_id"), resultSet.getString("dist_name"));
			districts.add(district);
		}

		return districts;
	}

	public ArrayList<Mandal> getMandals(int dist_id) throws SQLException {
		ArrayList<Mandal> mandals = new ArrayList<>();

		ResultSet resultSet = con.createStatement().executeQuery("select * from mandals where dist_id=" + dist_id);
		while (resultSet != null && resultSet.next()) {
			Mandal mandal = new Mandal(resultSet.getInt("mandal_id"), resultSet.getInt("dist_id"),
					resultSet.getString("mandal_name"));
			mandals.add(mandal);
		}

		return mandals;
	}

	private String generateTrackingId(Issue issue) {
		StringBuilder sb = new StringBuilder();
		if (issue.getDistrict_id() < 10) {
			sb.append("0");
		}
		sb.append("" + issue.getDistrict_id());

		if (issue.getMandal_id() < 10) {
			sb.append("0");
		}
		sb.append("" + issue.getMandal_id());
		sb.append(System.currentTimeMillis());
		return sb.toString();
	}

	public String submitIssue(Issue issue) throws SQLException {
		issue.setReference_id(generateTrackingId(issue));
		String query = " insert into issues (referenece_id, petitioner_name, petitioner_mobile, petitioner_email, prob_pic_urls,prob_statement,"
				+ "village,mandal_id,district_id,Status,status_description)"
				+ " values (?, ?, ?, ?, ?,?, ?, ?, ?, ?,?)";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		preparedStmt.setString(1, issue.getReference_id());
		preparedStmt.setString(2, issue.getPetitioner_name());
		preparedStmt.setString(3, issue.getPetitioner_mobile());
		preparedStmt.setString(4, issue.getPetitioner_email());
		preparedStmt.setString(5, issue.getProb_pic_urls());
		preparedStmt.setString(6, issue.getProb_statement());
		preparedStmt.setString(7, issue.getVillage());
		preparedStmt.setInt(8, issue.getMandal_id());
		preparedStmt.setInt(9, issue.getDistrict_id());
		preparedStmt.setString(10, issue.getStatus());
		preparedStmt.setString(11, issue.getStatus_description());
		preparedStmt.execute();
		return issue.getReference_id();
	}
}
