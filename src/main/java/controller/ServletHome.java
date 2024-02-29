package controller;

import java.io.IOException;
import java.util.List;

import bll.BLLException;
import bll.RestaurantBLL;
import bo.Restaurant;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ServletHome extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private RestaurantBLL restaurantBll;
	
	@Override
	public void init() throws ServletException 
	{
		
		super.init();
		
		try 
		{
			this.restaurantBll = new RestaurantBLL();
		} 
		catch (BLLException e) 
		{
			e.printStackTrace();
		}
	}
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/JSPHome.jsp");
		
		try 
		{
			List<Restaurant> restaurants = this.restaurantBll.selectAll();
			
			request.setAttribute("restaurants", restaurants);
		} 
		catch (BLLException e) 
		{
			e.printStackTrace();
		}

		rd.forward(request, response);
		
	}
}
