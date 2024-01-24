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
import bo.Reservation;
import bo.User;

public class ServletDinerReservation extends HttpServlet {
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
		
		String dinerTablesStr = request.getParameter("diner-tables");
		String dateDinerReservationStr = request.getParameter("diner-reservation-date");
		String hourDinerReservationStr = request.getParameter("diner-reservation-hour");
		
		
		int dinerTableInt = Integer.parseInt(dinerTablesStr);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		
		String dinerReservationDateTimeStr = dateDinerReservationStr + "T" + hourDinerReservationStr + ":00";
		LocalDateTime dinerReservationDateTime = LocalDateTime.parse(dinerReservationDateTimeStr, formatter);

		Reservation newReservation = null;
		
		try {
			newReservation = reservationBll.insert(dinerReservationDateTime, "hold", dinerTableInt, userSession.getId());
			response.sendRedirect("user");
		} catch (BLLException e) {
			e.printStackTrace();
		}		

	}

}
