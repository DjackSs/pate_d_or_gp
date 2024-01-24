package controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import bll.BLLException;
import bll.UserBLL;
import bo.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ServletConnection extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	private UserBLL userBLL;
	
	@Override
	public void init() throws ServletException 
	{
		super.init();
		
		try 
		{
			this.userBLL = new UserBLL();
			
		} 
		catch (BLLException e) 
		{
			e.printStackTrace();
		}
	}
	
	
		
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/JSPConnection.jsp");
		
		rd.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		try 
		{
			User newUser = new User();
			
			newUser.setEmail(request.getParameter("email"));
			newUser.setPassword(request.getParameter("password"));
			
			User trueUser = this.userBLL.selectByEmailAndPassword(newUser.getEmail(), newUser.getPassword());
			
			request.getSession().setAttribute("user", trueUser);
			
			response.sendRedirect(request.getContextPath()+"/user");
			
		} 
		catch (BLLException e) 
		{
			e.printStackTrace();
			response.sendRedirect("home");
		}
		catch (DateTimeParseException e)
		{
			
			e.printStackTrace();
			response.sendRedirect("home");
			
		}
		
	}

}
