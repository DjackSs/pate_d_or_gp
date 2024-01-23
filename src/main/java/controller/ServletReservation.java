package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import bll.BLLException;
import bll.RestaurantBLL;
import bll.ScheduleBLL;
import bll.TableBLL;
import bo.Restaurant;
import bo.Schedule;
import bo.Table;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RestaurantBLL restaurantBll;
	private TableBLL tableBll;
	private ScheduleBLL scheduleBll;

	@Override
	public void init() throws ServletException {
		super.init();
		
		try 
		{
			this.restaurantBll = new RestaurantBLL();
			this.tableBll = new TableBLL();
			this.scheduleBll = new ScheduleBLL();
		} 
		catch (BLLException e) 
		{
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idRestaurantStr = request.getParameter("idRestaurant");
		
		LocalDate now = LocalDate.now();
		String dateTimeInputMin = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		int idRestaurant = Integer.parseInt(idRestaurantStr);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/JSPReservation.jsp");
		
		try {
			Restaurant restaurant = restaurantBll.selectById(idRestaurant);
			List<Table> restaurantTables = tableBll.selectAllByIdRestaurantOrderBy(idRestaurant, "number_place"); 
			Schedule restaurantLunchSchedule = scheduleBll.selectByIdRestaurantAnd(idRestaurant, "open_hour < '18:00'");
			Schedule restaurantDinerSchedule = scheduleBll.selectByIdRestaurantAnd(idRestaurant, "close_hour >= '18:00'");
			
			request.setAttribute("restaurant", restaurant);
			request.setAttribute("restaurantTables", restaurantTables);
			request.setAttribute("dateTimeInputMin", dateTimeInputMin);
			request.setAttribute("restaurantLunchSchedule", restaurantLunchSchedule);
			request.setAttribute("restaurantDinerSchedule", restaurantDinerSchedule);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tablesStr = request.getParameter("tables");
		String dateLunchReservationStr = request.getParameter("lunch-reservation-date");
		String hourLunchReservationStr = request.getParameter("lunch-reservation-hour");
		String dateDinerReservationStr = request.getParameter("diner-reservation-date");
		String hourDinerReservationStr = request.getParameter("diner-reservation-hour");
		
		String lunchReservationDateTimeStr = dateLunchReservationStr + " " + hourLunchReservationStr + ":00";
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		// Parse from string to Java.util.Date object to Java.sql.Timestamp (for database)
		try {
			Date lunchReservationDateTimeParsed = dateFormat.parse(lunchReservationDateTimeStr);
			Timestamp lunchReservationTimestamp = new Timestamp(lunchReservationDateTimeParsed.getTime());
			System.out.println("Parsed Timestamp: " + lunchReservationTimestamp);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
//		if (dateLunchReservationStr.length() > 0 && hourLunchReservationStr.length() > 0) {
//			LocalDateTime lunchReservation = LocalDateTime.parse(dateLunchReservationStr + hourLunchReservationStr);
//			System.out.println(lunchReservation);
//		}
		
		System.err.println(lunchReservationDateTimeStr);
//		System.err.println(dateLunchReservationStr);
//		System.err.println(hourLunchReservationStr);
//		System.err.println(dateDinerReservationStr);
//		System.err.println(hourDinerReservationStr);
		
		
		
	}

}
