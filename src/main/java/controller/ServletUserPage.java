package controller;

import java.io.IOException;
import java.util.List;

import bll.BLLException;
import bll.MessageBLL;
import bll.ReservationBLL;


import bo.Message;
import bo.ReservationWithRestaurant;
import bo.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletUserPage extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		if(request.getParameter("deco") != null) 
		{
			request.getSession().setAttribute("user", null);
			
			response.sendRedirect(request.getContextPath()+"/home");
			
		} else 
		{
			request.getRequestDispatcher("/WEB-INF/jsp/JSPUserPage.jsp").forward(request, response);
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
