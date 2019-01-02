package com.kamal.user.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteUserServlet
 */
@WebServlet("/deleteServlet")
public class DeleteUserServlet extends HttpServlet {
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
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String firstName=request.getParameter("firstName");
	
		
		try 
		{
			Statement stmt = conn.createStatement();
			int result=stmt.executeUpdate("Delete from users  where firstName='"+firstName+"'");
			PrintWriter out =response.getWriter();
			if(result>0) {
				out.print("<h1>User deleted</H1>");
			}
			else {
				out.print("<H1>Error in Deleting the user. Please re-enter a valid username.</H1>");
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
