package org.webApp.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
	private static UsersSet us;

	public DisplayUsersServlet() {
        super();
    }
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("data", retrieveUser(id));
		request.getRequestDispatcher("/display/userData.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("backFromList") != null) {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		} else if (request.getParameter("backFromUser") != null) {
			request.setAttribute("data", us.getMap());
			request.getRequestDispatcher("/display/index.jsp").forward(request, response);
		} else if (request.getParameter("display") != null) {
			request.setAttribute("data", retrieveUserSet().getMap());
			request.getRequestDispatcher("/display/index.jsp").forward(request, response);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static UsersSet retrieveUserSet() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Session session = null;
		User u = null;
		us = new UsersSet();
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			
			List<Object[]> users = session.createNativeQuery("SELECT * FROM users").list();
			for (Object[] obj : users) {
				u = new User();
				u.setId((Integer)obj[0]);
				u.setName((String)obj[1]);
				u.setSurname((String)obj[2]);
				u.setGender((String)obj[3]);
				System.out.println(obj[4]);
				u.setBirthdate(dateFormat.format(obj[4]));
				us.addUser(u);
	         }
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		return us;
	}
	
	public User retrieveUser(int id) {
		return us.getUser(id);
	}
	
}
