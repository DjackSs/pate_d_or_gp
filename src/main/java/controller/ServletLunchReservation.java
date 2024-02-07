package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import bll.BLLException;
import bll.UserBLL;
import bo.Reservation;
import bo.RestaurantTable;
import bo.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletLunchReservation extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private UserBLL userBLL;
	
	@Override
	public void init() throws ServletException 
	{
		super.init();
		
		try 
		{
			this.userBLL = new UserBLL();
		} 
		catch (BLLException e) 
		{
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		User userSession = (((User) request.getSession().getAttribute("user")));
		userSession.setPassword("");
		
		String dateLunchReservationStr = request.getParameter("lunch-reservation-date");
		String hourLunchReservationStr = request.getParameter("lunch-reservation-hour");
		
		RestaurantTable table = new RestaurantTable();
		table.setId(Integer.parseInt(request.getParameter("lunch-tables")));
		
		//int lunchTableInt = Integer.parseInt(request.getParameter("lunch-tables"));
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		
		String lunchReservationDateTimeStr = dateLunchReservationStr + "T" + hourLunchReservationStr + ":00";
		LocalDateTime lunchReservationDateTime = LocalDateTime.parse(lunchReservationDateTimeStr, formatter);
	
		Reservation newReservation = new Reservation(lunchReservationDateTime, "hold", table);
		userSession.addReservation(newReservation);
			
		try 
		{
			this.userBLL.update(userSession);
			response.sendRedirect("user");
		} 
		catch (BLLException e) 
		{
			e.printStackTrace();
			response.sendRedirect("home");
		}
			
	}

}
