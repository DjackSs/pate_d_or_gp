package controller;

import java.io.IOException;

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
		User tryUser = new User(request.getParameter("email"),request.getParameter("password"));
		
		try 
		{
			
			User trueUser = this.userBLL.selectByEmailAndPassword(tryUser.getEmail(), tryUser.getPassword());

			request.getSession().setAttribute("user", trueUser);
			
			response.sendRedirect(request.getContextPath()+"/user");

			
		} 
		catch (BLLException e) 
		{
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/JSPConnection.jsp");
			
			request.setAttribute("errors", e.getErrors());
			request.setAttribute("tryUser", tryUser);
			
			rd.forward(request, response);
			
		}
		
	}

}
