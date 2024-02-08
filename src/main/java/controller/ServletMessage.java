package controller;

import java.io.IOException;

import bll.BLLException;
import bll.UserBLL;
import bo.Message;
import bo.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ServletMessage extends HttpServlet 
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
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/JSPContact.jsp");
		
		rd.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Message newMessage = new Message(request.getParameter("object"),request.getParameter("message"));
		
		User user = ((User) request.getSession().getAttribute("user"));
		
<<<<<<< HEAD
		try 
		{
			newMessage = this.userBLL.insertMessage(newMessage);
			
			user.addMessage(newMessage);
=======
		
		try 
		{
			newMessaga = this.userBLL.insertMessage(newMessaga);
			
			user.addMessage(newMessaga);
>>>>>>> 6ac7390bace480012404b54642814b81ea171de1
			
			this.userBLL.update(user);
		}
		catch (BLLException e)
		{
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath()+"/user");
		
		
		
		
	}

}
