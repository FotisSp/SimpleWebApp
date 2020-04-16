package org.webApp.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.webApp.entities.HomeAddress;
import org.webApp.entities.User;
import org.webApp.entities.WorkAddress;
import org.webApp.models.UserModel;

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
		if (request.getParameter("back") != null) {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat();
			UserModel userModel = new UserModel();
			User user = new User();

			user.setName(request.getParameter("name"));
			user.setSurname(request.getParameter("surname"));
			user.setGender(request.getParameter("gender"));

			String d = request.getParameter("birthdate");
			try {
				System.out.println("date : " + sdf.parse(d));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				//TODO 
			user.setBirthdate(d);
			
			String address = new String(request.getParameter("homeAddress"));
			if (address != null) {
				addHomeAddress(address, user);
			}
			
			address = new String(request.getParameter("workAddress"));
			if (address != null) {
				addWorkAddress(address, user);
			}
			
			if (userModel.create(user)) {
				request.setAttribute("msg", "Successful");
			} else {
				request.setAttribute("msg", "Failed");
			}
			request.getRequestDispatcher("user/index.jsp").forward(request, response);
		}
	}

	private void addHomeAddress(String reqAddress, User user) {
		HomeAddress address = new HomeAddress();
		address.setAddress(reqAddress);
		address.setUser(user);
		
		Set<HomeAddress> addressList = new HashSet<HomeAddress>();
		addressList.add(address);
		user.setHomeAddresses(addressList);
	}
	
	private void addWorkAddress(String reqAddress, User user) {
		WorkAddress address = new WorkAddress();
		address.setAddress(reqAddress);
		address.setUser(user);
		
		Set<WorkAddress> addressList = new HashSet<WorkAddress>();
		addressList.add(address);
		user.setWorkAddresses(addressList);
	}

}