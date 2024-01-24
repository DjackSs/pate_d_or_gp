package controller;

import java.io.IOException;
import java.util.List;

import bll.BLLException;
import bll.MessageBLL;
import bll.ReservationBLL;


import bo.Message;
import bo.ReservationWithRestaurant;
import bo.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletUserPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReservationBLL reservationBLL;
	private MessageBLL messageBLL;


	@Override
	public void init() throws ServletException {
		super.init();
		try {
			reservationBLL = new ReservationBLL();
			messageBLL = new MessageBLL();
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("deco") != null) {
			request.getSession().setAttribute("user", null);
			
			response.sendRedirect(request.getContextPath()+"/home");
			
		} else {
		    List<ReservationWithRestaurant> reservations = null;
		    List<Message> messages = null;
		    try {
		        reservations = reservationBLL.selectReservationWithRestaurantByIdUser(((User) request.getSession().getAttribute("user")).getId());
		        messages = messageBLL.selectMessageByIdUser(((User) request.getSession().getAttribute("user")).getId());
		    } catch (BLLException e) {
		        e.printStackTrace();
		    }
		 
		    request.setAttribute("reservations", reservations);
		    request.setAttribute("messages", messages);

			request.getRequestDispatcher("/WEB-INF/jsp/JSPUserPage.jsp").forward(request, response);
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
