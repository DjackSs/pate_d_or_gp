package controller;

import java.io.IOException;
import java.util.List;

import bll.BLLException;
import bll.ReservationBLL;
import bll.UserBLL;
import bo.Reservation;
import bo.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletUserPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReservationBLL reservationBLL;


	@Override
	public void init() throws ServletException {
		super.init();
		try {
			reservationBLL = new ReservationBLL();
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		if(request.getParameter("deco") != null)
		{
			request.getSession().setAttribute("user", null);
			
			response.sendRedirect(request.getContextPath()+"/home");
			
		}
		
		else
		{
		    List<Reservation> reservations = null;
		    try {
		        reservations = reservationBLL.selectReservationByIdUser(((User) request.getSession().getAttribute("user")).getId());
		    } catch (BLLException e) {
		        e.printStackTrace();
		    }
		 
		    request.setAttribute("reservations", reservations);

			request.getRequestDispatcher("/WEB-INF/jsp/JSPUserPage.jsp").forward(request, response);
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
