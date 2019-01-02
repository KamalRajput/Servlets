package com.kamal.user.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReadServlet
 */
@WebServlet("/ReadServlet")
public class ReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
private Connection conn;
	
	public void init() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			 conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
	}
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
		
		
		try 
		{
			Statement stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery("Select * from users");
			PrintWriter out =response.getWriter();
			while(rs.next()) {
				String firstName=rs.getString("firstName");
				String lastName=rs.getString("lastName");
				String email=rs.getString("email");
				String password=rs.getString("password");
				
				out.println(firstName+ " :" +lastName+ " :"+email+ " :"+password);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	

	public void destroy() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
