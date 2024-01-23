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
	private UserBLL userBLL;
	private ReservationBLL reservationBLL;

	@Override
	public void init() throws ServletException {
		super.init();
		try {
			userBLL = new UserBLL();
			reservationBLL = new ReservationBLL();
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. Récupération des paramètres
		String idString = request.getParameter("id");
		
		if (idString == null || idString.isBlank()) {
			request.getRequestDispatcher("/WEB-INF/jsp/JSPHome.jsp").forward(request, response);
			return;
		}
	
		// 2. Passage des paramètres dans le type voulu
		int id = Integer.parseInt(idString);
		
	    // 3. Exploitation des paramètres par le bll
	    User user = null;
	    List<Reservation> reservations = null;
	    try {
	        user = userBLL.selectById(id);
	        reservations = reservationBLL.selectReservationByIdUser(id);
	    } catch (BLLException e) {
	        e.printStackTrace();
	    }
	    
	    // 4. Ajout des attributs éventuels à ma request
	    request.setAttribute("user", user);
	    request.setAttribute("reservations", reservations);

	    // 5. Redirection vers la JSP choisie
	    request.getRequestDispatcher("/WEB-INF/jsp/JSPUserPage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
