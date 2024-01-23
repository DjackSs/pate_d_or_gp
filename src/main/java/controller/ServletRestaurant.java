package controller;

import java.io.IOException;
import java.util.List;

import bll.BLLException;
import bll.CardBLL;
import bll.DishBLL;
import bll.RestaurantBLL;
import bll.ScheduleBLL;
import bo.Card;
import bo.Dish;
import bo.Restaurant;
import bo.Schedule;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletRestaurant extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RestaurantBLL restaurantBll;
	private ScheduleBLL scheduleBll;
	private CardBLL cardBll;
	private DishBLL dishBll;
       
	@Override
	public void init() throws ServletException {
		super.init();
		
		try 
		{
			this.restaurantBll = new RestaurantBLL();
			this.scheduleBll = new ScheduleBLL();
			this.cardBll = new CardBLL();
			this.dishBll = new DishBLL();
		} 
		catch (BLLException e) 
		{
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		
		int id = Integer.parseInt(idStr);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/JSPRestaurant.jsp");
		
		try {
			Restaurant restaurant = restaurantBll.selectById(id);
			List<Schedule> restaurantSchedule = scheduleBll.selectAllByIdRestaurantOrderBy(id, "open_hour");
			Card restaurantCard = cardBll.selectById(restaurant.getIdCard());
			List<Dish> restaurantCardDishes = dishBll.selectDishesByCardId(restaurant.getIdCard());
			
			request.setAttribute("restaurant", restaurant);
			request.setAttribute("restaurantSchedule", restaurantSchedule);
			request.setAttribute("restaurantCard", restaurantCard);
			request.setAttribute("restaurantCardDishes", restaurantCardDishes);
			
			System.out.println(restaurantSchedule);
		} 
		catch (BLLException e) {
			e.printStackTrace();
		}
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
