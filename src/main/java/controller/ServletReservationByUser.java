package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import bll.BLLException;
import bll.ReservationBLL;


public class ServletReservationByUser extends HttpServlet {
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
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
