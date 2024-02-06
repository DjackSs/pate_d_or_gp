package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import bll.BLLException;
import bll.ReservationBLL;
import bll.RestaurantBLL;
import bll.ScheduleBLL;
import bll.TableBLL;
import bo.Restaurant;
import bo.Schedule;
import bo.Table;
import bo.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ServletReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RestaurantBLL restaurantBll;
	private TableBLL tableBll;
	private ScheduleBLL scheduleBll;
	private ReservationBLL reservationBll;

	@Override
	public void init() throws ServletException {
		super.init();
		
		try 
		{
			this.restaurantBll = new RestaurantBLL();
			this.tableBll = new TableBLL();
			this.scheduleBll = new ScheduleBLL();
			this.reservationBll = new ReservationBLL();
		} 
		catch (BLLException e) 
		{
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		User userSession = ((User) session.getAttribute("user"));
		request.setAttribute("user", userSession);
		
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

	}

}
