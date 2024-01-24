package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bll.BLLException;
import bll.ReservationBLL;
import bo.Reservation;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ServletUserReservation extends HttpServlet {
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

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1 Récupération des params
		String idStr = request.getParameter("id_user");
		
		// 2 Passage des params dans le type voulu
		int id_user = Integer.parseInt(idStr);
		
		// 3 Exploitation des params par le bll
		List<Reservation> reservations = new ArrayList<>();
		try {
			reservations = reservationBLL.selectReservationByIdUser(id_user);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		// 4 Ajout des attributs éventuels à ma requête
		request.setAttribute("reservations", reservations);
		
		// 5 Redirection vers la JSP souhaitée	
		request.getRequestDispatcher("/WEB-INF/jsp/JSPUserPage.jsp").forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
