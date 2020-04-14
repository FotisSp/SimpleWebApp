package com.apache.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apache.entities.User;
import com.apache.models.UserModel;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public UserServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("user/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		UserModel userModel = new UserModel();
		User user = new User();
		
		user.setName(request.getParameter("name"));
		user.setSurname(request.getParameter("surname"));
		user.setGender(request.getParameter("gender"));
		
		try {
			String d = request.getParameter("birthdate");
			user.setBirthdate(dateFormat.parse(d));
		} catch (ParseException e) {
			System.out.println("ERROR AT UserServlet");		//TODO refine later
			e.printStackTrace();
			System.exit(1);
		}
		
		if (userModel.create(user)) {
			request.setAttribute("msg", "Successful");
		} else {
			request.setAttribute("msg", "Failed");
		}
		request.getRequestDispatcher("user/index.jsp").forward(request, response);
	}

}