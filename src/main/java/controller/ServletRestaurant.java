package controller;

import java.io.IOException;

import bll.BLLException;
import bll.CardBLL;
import bll.RestaurantBLL;
import bo.Card;
import bo.Restaurant;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletRestaurant extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private RestaurantBLL restaurantBll;
	private CardBLL cardBll;
       
	@Override
	public void init() throws ServletException {
		super.init();
		
		try 
		{
			this.restaurantBll = new RestaurantBLL();
			this.cardBll = new CardBLL();
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
			
			Card restaurantCard = cardBll.selectById(restaurant.getCard().getId());
			
			request.setAttribute("restaurant", restaurant);
			
			request.setAttribute("restaurantCard", restaurantCard);
			
		} 
		catch (BLLException e) {
			e.printStackTrace();
		}
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
