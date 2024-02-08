package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bll.BLLException;
import bll.RestaurantBLL;
import bo.Reservation;
import bo.Restaurant;
import bo.RestaurantTable;
import bo.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletUserPage extends HttpServlet 
{
	private RestaurantBLL restaurantBLL;
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException 
	{
		super.init();
		try 
		{
			this.restaurantBLL = new RestaurantBLL();
		} 
		catch (BLLException e) 
		{
			e.printStackTrace();
		}
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		
		if(request.getParameter("deco") != null) 
		{
			request.getSession().setAttribute("user", null);
			
			response.sendRedirect(request.getContextPath()+"/home");
			
		} else 
		{
			User user = (User) request.getSession().getAttribute("user");
			
			if(user.getReservations().size() != 0)
			{
				try
				{
					Map<Integer,Restaurant> reservationRestaurants = new HashMap<>();
					
					List<Restaurant> restaurants = this.restaurantBLL.selectAll();
					
					for(Reservation reservation : user.getReservations())
					{
						for(Restaurant restaurant : restaurants)
						{
							for(RestaurantTable table : restaurant.getTables())
							{
								if(table.getId() == reservation.getTables().getId())
								{
									reservationRestaurants.put(reservation.getId(), restaurant);
									
								}
	
							}
							
						}
						
					}
					
					
					request.setAttribute("restaurants", reservationRestaurants);
					
				}
				catch(BLLException e)
				{
					e.printStackTrace();
				}
				
				
			}
			

			request.getRequestDispatcher("/WEB-INF/jsp/JSPUserPage.jsp").forward(request, response);
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

}
