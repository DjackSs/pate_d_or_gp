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
	
	private List<Schedule> schedules;
	

	@Override
	public void init() throws ServletException {
		super.init();
		
		try 
		{
			this.restaurantBll = new RestaurantBLL();
			this.userBLL = new UserBLL();
			this.schedules = new ArrayList<>();
			
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
			this.schedules = restaurant.getSchedules();
			
			request.setAttribute("restaurant", restaurant);
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
		String redirectDestination = "reservationUser";
		
		if(!"none".equals(request.getParameter("tables")))
		{
			User userSession = (((User) request.getSession().getAttribute("user")));
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
			
			String dateReservationStr = request.getParameter("reservation-date");
			String hourReservationStr = request.getParameter("reservation-hour");
			String lunchReservationDateTimeStr = dateReservationStr + "T" + hourReservationStr + ":00";
			
			LocalDateTime lunchReservationDateTime = LocalDateTime.parse(lunchReservationDateTimeStr, formatter);
			
			
			RestaurantTable table = new RestaurantTable();
			table.setId(Integer.parseInt(request.getParameter("tables")));
		
			Reservation newReservation = new Reservation(lunchReservationDateTime, "hold");
			
			try 
			{
				newReservation = this.userBLL.insertReservation(newReservation, this.schedules);
				
				newReservation.setTables(table);
				
				userSession.addReservation(newReservation);
				
				this.userBLL.update(userSession);
				
				redirectDestination = "/user";
				
				
			} 
			catch (BLLException e) 
			{
				e.printStackTrace();
				
				redirectDestination = "/home";
			}
			
		}
		
		
		response.sendRedirect(request.getContextPath()+redirectDestination);
	
			
	}

	

}
