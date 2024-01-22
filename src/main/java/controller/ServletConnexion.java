package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//pas bien : la servlet ne doit pas interagir avec la bdd, c'est le role du dao!!!!!
public class ServletConnexion extends HttpServlet{
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		
		try 
		{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/sqlserver");
			
			Connection cnx = dataSource.getConnection();
			
			if(!cnx.isClosed())
			{
				System.out.println("la co est ouverte");
			}
		
		} 
		catch (NamingException e) 
		{
			e.printStackTrace();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		
	}
		
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}
		

}
