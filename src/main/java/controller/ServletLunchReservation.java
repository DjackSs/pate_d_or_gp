package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import bll.BLLException;
import bll.ReservationBLL;
import bll.RestaurantBLL;
import bll.ScheduleBLL;
import bll.TableBLL;
import bo.Reservation;
import bo.User;

public class ServletLunchReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReservationBLL reservationBll;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		try 
		{
			this.reservationBll = new ReservationBLL();
		} 
		catch (BLLException e) 
		{
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User userSession = ((User) session.getAttribute("user"));
		
		String lunchTablesStr = request.getParameter("lunch-tables");
		String dateLunchReservationStr = request.getParameter("lunch-reservation-date");
		String hourLunchReservationStr = request.getParameter("lunch-reservation-hour");
		
		int lunchTableInt = Integer.parseInt(lunchTablesStr);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		
		String lunchReservationDateTimeStr = dateLunchReservationStr + "T" + hourLunchReservationStr + ":00";
		LocalDateTime lunchReservationDateTime = LocalDateTime.parse(lunchReservationDateTimeStr, formatter);
	
		Reservation newReservation = null;
			
		try {
			newReservation = reservationBll.insert(lunchReservationDateTime, "hold", lunchTableInt, userSession.getId());
			response.sendRedirect("user");
		} catch (BLLException e) {
			e.printStackTrace();
		}
			
	}

}
