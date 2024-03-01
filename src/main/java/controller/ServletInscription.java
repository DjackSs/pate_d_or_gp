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


public class ServletInscription extends HttpServlet 
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
			throw new ServletException("Impossible de se connecter à la base de données", e);
		}
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/JSPInscription.jsp");
		
		rd.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		User newUser = new User();
		
		newUser.setName(request.getParameter("name"));
		newUser.setLastname(request.getParameter("lastname"));
		newUser.setEmail(request.getParameter("email"));
		newUser.setPassword(request.getParameter("password"));
		
		
		try 
		{

			newUser = this.userBLL.insert(newUser);

			request.getSession().setAttribute("user", newUser);
			
			response.sendRedirect(request.getContextPath()+"/user");
			
				
			
		} 
		catch (BLLException e) 
		{
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/JSPInscription.jsp");
			
			request.setAttribute("errors", e.getErrors());
			request.setAttribute("newUser", newUser);
			
			rd.forward(request, response);
		}
		
		
		
		
	}

}
