package controller;

import java.io.IOException;

import bll.BLLException;
import bll.UserBLL;
import bo.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletUpdateUser extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private UserBLL userBLL;
	
	@Override
	public void init() throws ServletException {
		super.init();
		try 
		{
			userBLL = new UserBLL();
		} 
		catch (BLLException e) 
		{
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		if(request.getParameter("delete") != null) 
		{
			try 
			{
				this.userBLL.delete(((User) request.getSession().getAttribute("user")));
				
				request.getSession().setAttribute("user", null);
			} 
			catch (BLLException e) 
			{
				e.printStackTrace();
			}
			
			
			response.sendRedirect(request.getContextPath()+"/home");
			
		} 
		else 
		{
			request.getRequestDispatcher("/WEB-INF/jsp/JSPUpdateUser.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		int id = ((User) request.getSession().getAttribute("user")).getId();
		String name = request.getParameter("name");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		String role = ((User) request.getSession().getAttribute("user")).getRole();
		
		try {
			User userToUpdate;
			userToUpdate = userBLL.selectById(id);
			userToUpdate.setName(name);
			userToUpdate.setLastname(lastname);
			userToUpdate.setEmail(email);
			userToUpdate.setPassword(password);
			userToUpdate.setRole(role);
			
			userBLL.update(userToUpdate);
			
			request.getSession().setAttribute("user", userToUpdate);
			
			response.sendRedirect(request.getContextPath()+"user");
			
		} 
		catch (BLLException e) 
		{
			request.setAttribute("erreur", e);
			request.getRequestDispatcher("/WEB-INF/jsp/JSPUpdateUser.jsp").forward(request, response);
		}
	}

}
