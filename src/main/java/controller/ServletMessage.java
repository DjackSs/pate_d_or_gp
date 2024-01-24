package controller;

import java.io.IOException;

import bll.BLLException;
import bll.MessageBLL;
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
	
	private MessageBLL messageBLL;
	
	@Override
	public void init() throws ServletException 
	{
		super.init();
		
		try 
		{
			this.messageBLL = new MessageBLL();
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
		
		
		try 
		{
			Message message = this.messageBLL.insert(request.getParameter("object"), request.getParameter("message"), ((User) request.getSession().getAttribute("user")).getId());
		}
		catch (BLLException e)
		{
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath()+"/user");
		
		
		
		
	}

}