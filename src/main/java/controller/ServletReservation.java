package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
		int idRestaurant = Integer.parseInt(request.getParameter("idRestaurant"));
		
		LocalDate now = LocalDate.now();
		String dateTimeInputMin = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/JSPReservation.jsp");
		
		try 
		{
			
			Restaurant restaurant = restaurantBll.selectById(idRestaurant);
			
			List<Schedule> restaurantLunchSchedule = new ArrayList<>();
			List<Schedule> restaurantDinerSchedule = new ArrayList<>();
			
			for(Schedule item : restaurant.getSchedules())
			{
				if(item.getOpenHour().isBefore(LocalTime.of(18, 00)))
				{
					restaurantLunchSchedule.add(item);
					
				}
				else
				{
					restaurantDinerSchedule.add(item);
					
				}
					
			}
			
			request.setAttribute("restaurant", restaurant);
			request.setAttribute("dateTimeInputMin", dateTimeInputMin);
			request.setAttribute("restaurantLunchSchedule", restaurantLunchSchedule);
			request.setAttribute("restaurantDinerSchedule", restaurantDinerSchedule);
			
		} 
		catch (BLLException e) 
		{
			e.printStackTrace();
		}
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		User userSession = (((User) request.getSession().getAttribute("user")));
		userSession.setPassword("");
		
		String redirectDestination = "user";
		
		//reservation for lunch
		if(request.getParameter("lunch-tables") != null)
		{
			String dateLunchReservationStr = request.getParameter("lunch-reservation-date");
			String hourLunchReservationStr = request.getParameter("lunch-reservation-hour");
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
			
			String lunchReservationDateTimeStr = dateLunchReservationStr + "T" + hourLunchReservationStr + ":00";
			LocalDateTime lunchReservationDateTime = LocalDateTime.parse(lunchReservationDateTimeStr, formatter);
			
			RestaurantTable table = new RestaurantTable();
			table.setId(Integer.parseInt(request.getParameter("lunch-tables")));
		
			Reservation newReservation = new Reservation(lunchReservationDateTime, "hold", table);
			
			try 
			{
				newReservation = this.userBLL.insertReservation(newReservation);
				
				userSession.addReservation(newReservation);
				
				this.userBLL.update(userSession);
				
				
			} 
			catch (BLLException e) 
			{
				e.printStackTrace();
				
				redirectDestination = "home";
			}
			
		}
		
		//reservation for diner
		if(request.getParameter("diner-tables") != null)
		{
			
			String dateDinerReservationStr = request.getParameter("diner-reservation-date");
			String hourDinerReservationStr = request.getParameter("diner-reservation-hour");
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
			
			String dinerReservationDateTimeStr = dateDinerReservationStr + "T" + hourDinerReservationStr + ":00";
			LocalDateTime dinerReservationDateTime = LocalDateTime.parse(dinerReservationDateTimeStr, formatter);
			
			RestaurantTable table = new RestaurantTable();
			table.setId(Integer.parseInt(request.getParameter("diner-tables")));

			Reservation newReservation = new Reservation(dinerReservationDateTime, "hold", table);
			
			try 
			{
				newReservation = this.userBLL.insertReservation(newReservation);
				
				userSession.addReservation(newReservation);
				
				this.userBLL.update(userSession);
				
			} 
			catch (BLLException e) 
			{
				e.printStackTrace();
				
				redirectDestination = "home";
			}
			
			response.sendRedirect(redirectDestination);
			
		}
		
	
			
	}

	

}
