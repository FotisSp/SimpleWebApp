package org.webApp.servlets;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.webApp.entities.User;
import org.webApp.models.HibernateUtil;
import org.webApp.models.UsersSet;

public class DisplayUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DisplayUsersServlet() {
        super();
    }
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("ID is : " + id);
		response.sendRedirect(request.getContextPath() + "/display/userData.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("back") != null) {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		} else if (request.getParameter("display") != null) {
			request.setAttribute("data", retrieve().getMap());
			request.getRequestDispatcher("/display/index.jsp").forward(request, response);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static UsersSet retrieve() {
		Session session = null;
		User u = null;
		UsersSet us = new UsersSet();
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			
			List<Object[]> users = session.createNativeQuery("SELECT * FROM users").list();
			for (Object[] obj : users) {
				u = new User();
				u.setId((Integer)obj[0]);
				u.setName((String)obj[1]);
				u.setSurname((String)obj[2]);
				u.setGender((String)obj[3]);
				u.setBirthdate((Date)obj[4]);
				us.addUser(u);
	         }
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		return us;
	}
	
}
