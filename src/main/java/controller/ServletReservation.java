package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import bll.BLLException;
import bll.RestaurantBLL;
import bll.UserBLL;
import bo.Restaurant;
import bo.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletReservation extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private RestaurantBLL restaurantBll;
	private UserBLL userBLL;
	

	@Override
	public void init() throws ServletException {
		super.init();
		
		try 
		{
			this.restaurantBll = new RestaurantBLL();
			this.userBLL = new UserBLL();
			
		} 
		catch (BLLException e) 
		{
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	
		User userSession = ((User) request.getSession().getAttribute("user"));
		
		int idRestaurant = Integer.parseInt(request.getParameter("idRestaurant"));
		
		
		LocalDate now = LocalDate.now();
		String dateTimeInputMin = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/JSPReservation.jsp");
		
		try 
		{
			
			Restaurant restaurant = restaurantBll.selectById(idRestaurant);
			
			
			
			//List<RestaurantTable> restaurantTables = tableBll.selectAllByIdRestaurantOrderBy(idRestaurant, "number_place");
			
			//Schedule restaurantLunchSchedule = scheduleBll.selectByIdRestaurantAnd(idRestaurant, "open_hour < '18:00'");
			//Schedule restaurantDinerSchedule = scheduleBll.selectByIdRestaurantAnd(idRestaurant, "close_hour >= '18:00'");
			
			request.setAttribute("restaurant", restaurant);
			request.setAttribute("dateTimeInputMin", dateTimeInputMin);
			//request.setAttribute("restaurantLunchSchedule", restaurantLunchSchedule);
			//request.setAttribute("restaurantDinerSchedule", restaurantDinerSchedule);
			
		} 
		catch (BLLException e) 
		{
			e.printStackTrace();
		}
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	

	}

}
