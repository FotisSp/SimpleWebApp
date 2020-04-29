package org.webApp.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.webApp.entities.HomeAddress;
import org.webApp.entities.User;
import org.webApp.entities.WorkAddress;
import org.webApp.models.UserModel;

/**
 * @author Fotis Spanopoulos
 *
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("user/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("back") != null) {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		}
		else if (request.getParameter("save") != null) {
			editUser(request, response);
		}
		else {
			registerUser(request, response);
		}
	}

	/**
	 * Construct the User object with the data provided by the user and passes it 
	 * to the UserModel to create a new record to the SQL database.
	 * 
	 * @param 	request				Contains the info to construct the User object. 
	 * @param 	response			Forwards it to the index.jsp file.
	 * @throws 	ServletException	Called if something fails during forward.
	 * @throws 	IOException			Called if something fails during forward.
	 */
	private void registerUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserModel userModel = new UserModel();
		User user = new User();

		registerEssentailData(request, user);

		if (userModel.create(user)) {
			request.setAttribute("msg", "Successful");
		} else {
			request.setAttribute("msg", "Failed");
		}
		request.getRequestDispatcher("user/index.jsp").forward(request, response);
	}

	/**
	 * Constructs the User object with the updated info and passes it to the UserModel
	 * Update method to update it in the SQL database.
	 * 
	 * @param 	request				Contains the info to construct the User object. 
	 * @param 	response			Forwards it to the edit.jsp file.
	 * @throws 	ServletException	Called if something fails during forward.
	 * @throws 	IOException			Called if something fails during forward.
	 */
	private void editUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserModel um = new UserModel();
		int id = Integer.parseInt(request.getParameter("userId"));
		User user = new User();

		user.setId(id);
		registerEssentailData(request, user);

		if (um.update(user)) {
 			request.setAttribute("msg", "Update was Successful");
		} else {
			request.setAttribute("msg", "Update Failed");
		}
		request.setAttribute("data", user);
		request.getRequestDispatcher("user/edit.jsp").forward(request, response);
	}

	/**
	 * Registers the basic information for the user to the User object.
	 * 
	 * @param 	request		A HttpServletRequest which contains the info from the page.
	 * @param 	user		The user object to store the data.
	 */
	private void registerEssentailData(HttpServletRequest request, User user) {
		user.setName(request.getParameter("name"));
		user.setSurname(request.getParameter("surname"));
		user.setGender(request.getParameter("gender"));

		String d = request.getParameter("birthdate");
		SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy", Locale.ENGLISH);
		Date date;
		try {
			date = sdf.parse(d);
			sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			d = sdf.format(date);
			user.setBirthdate(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
 		String address = new String(request.getParameter("homeAddress"));
		if (address != null) {
			addHomeAddress(address, user);
		}

		address = new String(request.getParameter("workAddress"));
		if (address != null) {
			addWorkAddress(address, user);
		}
	}


	/**
	 * Constructs a HoemAddress Object and then is sets it to the 
	 * user object.
	 * 
	 * @param 	reqAddress	The address to be registered.
	 * @param 	user		The user object to register the address.
	 */
	private void addHomeAddress(String reqAddress, User user) {
		HomeAddress address = new HomeAddress();
		address.setAddress(reqAddress);
		address.setUser(user);

		user.setHomeAddress(address);
	}

	/**
	 * Constructs a WorkAddress Object and then is sets it to the 
	 * user object.
	 * 
	 * @param 	reqAddress	The address to be registered.
	 * @param 	user		The user object to register the address.
	 */
	private void addWorkAddress(String reqAddress, User user) {
		WorkAddress address = new WorkAddress();
		address.setAddress(reqAddress);
		address.setUser(user);

		user.setWorkAddress(address);
	}

}