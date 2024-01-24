package controller;

import java.io.IOException;

import bll.BLLException;
import bll.UserBLL;
import bo.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletUpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserBLL userBLL;
	
	@Override
	public void init() throws ServletException {
		super.init();
		try {
			userBLL = new UserBLL();
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		
		if (idStr == null || idStr.isBlank()) {
			request.getRequestDispatcher("/WEB-INF/jsp/JSPHome.jsp").forward(request, response);
			return;
		}
		
		int id = Integer.parseInt(idStr);
		
		try {
			User user = userBLL.selectById(id);
			request.setAttribute("user", user);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/jsp/JSPUpdateUser.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1 Récupérer les données d'insertion
		String idStr = request.getParameter("id");
		String nameStr = request.getParameter("name");
		String lastnameStr = request.getParameter("lastname");
		String emailStr = request.getParameter("email");
		String passwordStr = request.getParameter("password");
		String roleStr = request.getParameter("role");
		
		
		// 2 eventuellement passer dans le bon type
		int id = Integer.parseInt(idStr);
		
		// 3 Exploitation des params par le BLL
		try {
			User userToUpdate;
			userToUpdate = userBLL.selectById(id);
			System.out.println(id);
			userToUpdate.setName(nameStr);
			System.out.println(nameStr);
			userToUpdate.setLastname(lastnameStr);
			System.out.println(lastnameStr);
			userToUpdate.setEmail(emailStr);
			System.out.println(emailStr);
			userToUpdate.setPassword(passwordStr);
			System.out.println("mdp de sevlet"+passwordStr);
			userToUpdate.setRole(roleStr);
			System.out.println(roleStr);
			
			System.out.println(userToUpdate);
			userBLL.update(userToUpdate);
			System.out.println(userToUpdate);
			
			request.getSession().setAttribute("user", userToUpdate);
			response.sendRedirect("user?id="+id);
		} catch (BLLException e) {
			request.setAttribute("erreur", e);
			request.getRequestDispatcher("/WEB-INF/jsp/JSPUpdateUser.jsp").forward(request, response);
		}
	}

}
