package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import bll.BLLException;
import bll.RestaurantBLL;
import bll.UserBLL;
import bo.Reservation;
import bo.Restaurant;
import bo.RestaurantTable;
import bo.Schedule;
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
	
	private Restaurant restaurant;
	private List<Schedule> schedules;
	

	@Override
	public void init() throws ServletException {
		super.init();
		
		try 
		{
			this.restaurantBll = new RestaurantBLL();
			this.userBLL = new UserBLL();
			this.schedules = new ArrayList<>();
			this.restaurant = new Restaurant();
			
		} 
		catch (BLLException e) 
		{
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		int idRestaurant = Integer.parseInt(request.getParameter("idRestaurant"));
		
		LocalDate now = LocalDate.now();
		String dateTimeInputMin = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/JSPReservation.jsp");
		
		try 
		{
			
			this.restaurant = restaurantBll.selectById(idRestaurant);
			this.schedules = restaurant.getSchedules();
			
			request.setAttribute("restaurant", this.restaurant);
			request.setAttribute("dateTimeInputMin", dateTimeInputMin);
			
		} 
		catch (BLLException e) 
		{
			e.printStackTrace();
		}
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String redirectDestination = "/home";
		
		if(!"none".equals(request.getParameter("tables")))
		{
			User userSession = (((User) request.getSession().getAttribute("user")));
			

			String dateReservationStr = request.getParameter("reservation-date");
			String hourReservationStr = request.getParameter("reservation-hour");			

			RestaurantTable table = new RestaurantTable();
			table.setId(Integer.parseInt(request.getParameter("tables")));

			try 
			{
				Reservation newReservation = this.userBLL.insertReservation(dateReservationStr, hourReservationStr, this.schedules);
				
				newReservation.setTables(table);
				
				userSession.addReservation(newReservation);
				
				this.userBLL.update(userSession);
				
				redirectDestination = "/user";
				
				
			} 
			catch (BLLException e) 
			{
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/JSPReservation.jsp");
				
				LocalDate now = LocalDate.now();
				String dateTimeInputMin = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				
				request.setAttribute("dateTimeInputMin", dateTimeInputMin);
				request.setAttribute("restaurant", this.restaurant);
				request.setAttribute("errors", e.getErrors());
				
				rd.forward(request, response);
			}
			
		}
		
		
		//response.sendRedirect(request.getContextPath()+redirectDestination);
	
			
	}

	

}
