package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import bll.BLLException;
import bll.RestaurantBLL;
import bll.UserBLL;
import bo.Message;
import bo.Reservation;
import bo.Restaurant;
import bo.RestaurantTable;
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
	

	@Override
	public void init() throws ServletException {
		super.init();
		
		try 
		{
			this.restaurantBll = new RestaurantBLL();
			this.userBLL = new UserBLL();
			this.restaurant = new Restaurant();
			
		} 
		catch (BLLException e) 
		{
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		User userSession = (User) request.getSession().getAttribute("user");
		
		int idRestaurant = Integer.parseInt(request.getParameter("idRestaurant"));
		
		LocalDate now = LocalDate.now();
		String dateTimeInputMin = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/JSPReservation.jsp");
		
		try 
		{
			
			this.restaurant = restaurantBll.selectById(idRestaurant);
			
			String defaultUserObjectMessageReservation = restaurantBll.selectById(idRestaurant).getName();
			
			request.setAttribute("restaurant", this.restaurant);
			request.setAttribute("dateTimeInputMin", dateTimeInputMin);
			request.setAttribute("defaultReservationObjectMessage", defaultUserObjectMessageReservation);
			
		} 
		catch (BLLException e) 
		{
			e.printStackTrace();
		}
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/JSPReservation.jsp");
		
		LocalDate now = LocalDate.now();
		String dateTimeInputMin = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		request.setAttribute("dateTimeInputMin", dateTimeInputMin);
		request.setAttribute("restaurant", this.restaurant);
		
		if(!"none".equals(request.getParameter("tables")))
		{
			User userSession = (User) request.getSession().getAttribute("user");
			
			String dateReservationStr = request.getParameter("reservation-date");
			String hourReservationStr = request.getParameter("reservation-hour");

			RestaurantTable table = new RestaurantTable();
			table.setId(Integer.parseInt(request.getParameter("tables")));

			try 
			{
				//1 - create the reservation
				Reservation newReservation = this.userBLL.insertReservation(dateReservationStr, hourReservationStr, this.restaurant.getSchedules());
				
				//2 - set up the object model
				newReservation.setTables(table);
				userSession.addReservation(newReservation);
				
				
				//3 - Gestion de message additionnel à la réservation client
				String userDefaultMessageObjectReservation = request.getParameter("reservation-message-object");
				String userMessageContentReservation = request.getParameter("reservation-message-content");
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate date = LocalDate.parse(dateTimeInputMin, formatter);
				String formattedDate = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				
				String userMessageObjectReservation = dateReservationStr + " | " + userDefaultMessageObjectReservation;
				
				if(!userMessageContentReservation.isBlank()) {
					Message newReservationMessage = new Message(userMessageObjectReservation, userMessageContentReservation);
					newReservationMessage = userBLL.insertMessage(newReservationMessage);
					userSession.addMessage(newReservationMessage);
				}
				
				//4 - update the database with the object model
				this.userBLL.update(userSession);
				
				response.sendRedirect(request.getContextPath()+"/user");
				
			} 
			catch (BLLException e) 
			{

				request.setAttribute("errors", e.getErrors());
				
				rd.forward(request, response);
			}
			
		}
		else
		{
	
			request.setAttribute("errorTable", "Choisissez une table à réserver" );
			
			rd.forward(request, response);
			
		}
		
		
	
			
	}

	

}