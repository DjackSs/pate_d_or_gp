package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bll.BLLException;
import bll.RestaurantBLL;
import bo.Reservation;
import bo.Restaurant;
import bo.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vo.ReservationVO;

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
		
		// check if user is connected or not
		if(request.getParameter("deco") != null) 
		{
			request.getSession().setAttribute("user", null);
			
			response.sendRedirect(request.getContextPath()+"/home");
			
		} 
		else 
		{
			User user = (User) request.getSession().getAttribute("user");
			
			if(user.getReservations().isEmpty())
			{
				try
				{
					//get the restaurant of each reservations
					Map<Integer,Restaurant> reservationRestaurants = new HashMap<>();
					
					for(Reservation reservation : user.getReservations())
					{
						reservationRestaurants.put(reservation.getId(), this.restaurantBLL.selectByReservation(reservation.getTables().getId()));
					}
					
					request.setAttribute("restaurants", reservationRestaurants);
					
					//create VO for each reservations
					List<ReservationVO> allResaVO = new ArrayList<>();
					
					for (Reservation resa: user.getReservations()) 
					{
						ReservationVO resaVO = new ReservationVO(resa);
						allResaVO.add(resaVO);
						
					}
						
					request.setAttribute("reservationVO", allResaVO);
					
				}
				catch(BLLException e)
				{
					e.printStackTrace();
				}
				
			}
			
			request.getRequestDispatcher("/WEB-INF/jsp/JSPUserPage.jsp").forward(request, response);
			
		}
	}

}
