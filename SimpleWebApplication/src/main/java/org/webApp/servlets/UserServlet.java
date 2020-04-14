package org.webApp.servlets;

import java.io.IOException;
import java.text.DateFormat;
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
		
		System.err.println("\n\n\n\n\n\n\n\n\n\nksdjfgbijkshfkjisd \n\n\n\n\n\n\n\n\n\n\n");	//TODO DELETE LATER
		System.exit(1);																			// TODO DELETE LATER
		
		request.getRequestDispatcher("user/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("back") != null) {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		} else {
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