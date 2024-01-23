package controller;

import java.io.IOException;
import java.util.List;

import bll.BLLException;
import bll.RestaurantBLL;
import bll.TableBLL;
import bo.Restaurant;
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
//	private ScheduleBLL scheduleBll;
//	private CardBLL cardBll;
//	private DishBLL dishBll;

	@Override
	public void init() throws ServletException {
		super.init();
		
		try 
		{
			this.restaurantBll = new RestaurantBLL();
			this.tableBll = new TableBLL();
//			this.scheduleBll = new ScheduleBLL();
//			this.cardBll = new CardBLL();
//			this.dishBll = new DishBLL();
		} 
		catch (BLLException e) 
		{
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idRestaurantStr = request.getParameter("idRestaurant");
		
		int idRestaurant = Integer.parseInt(idRestaurantStr);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/JSPReservation.jsp");
		
		try {
			Restaurant restaurant = restaurantBll.selectById(idRestaurant);
			List<Table> restaurantTables = tableBll.selectTablesByRestaurantId(idRestaurant); 
			
			request.setAttribute("restaurant", restaurant);
			request.setAttribute("restaurantTables", restaurantTables);
			
			System.out.println(restaurantTables);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/JSPUserPage.jsp").forward(request, response);
	}

}
