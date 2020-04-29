package org.webApp.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.webApp.entities.HomeAddress;
import org.webApp.entities.User;
import org.webApp.entities.WorkAddress;
import org.webApp.models.UserModel;
import org.webApp.models.UsersSet;

/**
 * @author Fotis Spanopoulos
 *
 */
public class DisplayUsersServlet extends HttpServlet {
	private static final int ID = 0;
	private static final int NAME = 1;
	private static final int SURNAME = 2;
	private static final int GENDER = 1;
	private static final int BIRTHDATE = 2;
	private static final int HOMEADDRESS = 3;
	private static final int WORKADDRESS = 4;
	private static final long serialVersionUID = 1L;
	private static UserModel um;
	private static UsersSet us;

	public DisplayUsersServlet() {
        um = new UserModel();
        us = new UsersSet();
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
		} 
		else if (request.getParameter("backFromUser") != null) {
			request.setAttribute("data", us.getMap());
			request.getRequestDispatcher("/display/index.jsp").forward(request, response);
		} 
		else if (request.getParameter("display") != null) {
			request.setAttribute("data", retrieveUserSet().getMap());
			request.getRequestDispatcher("/display/index.jsp").forward(request, response);
		} 
		else if (request.getParameter("editUser") != null) {
			int id = Integer.parseInt(request.getParameter("userId"));
			request.setAttribute("data", retrieveUser(id));
			request.getRequestDispatcher("/user/edit.jsp").forward(request, response);
		} 
		else {
			int id = Integer.parseInt(request.getParameter("userId"));
			deleteUser(id);
			request.setAttribute("data", retrieveUserSet().getMap());
			request.getRequestDispatcher("/display/index.jsp").forward(request, response);
		}
	}
	
	/**
	 * Retrieves a User set which contains all the registered users.
	 * 
	 * @return		UsersSet with all the registered users.
	 */
	private static UsersSet retrieveUserSet() {
		User u = null;
		us = new UsersSet();

		String query = "SELECT " + 
				"    u.id, " + 
				"    u.name, " + 
				"    u.surname " + 
				" FROM " + 
				"    webapp.users as u " + 
				" ORDER BY u.name ASC;";

		List<Object[]> users = um.getUsers(query);
		for (Object[] obj : users) {
			u = new User();
			u.setId((Integer)obj[ID]);
			u.setName((String)obj[NAME]);
			u.setSurname((String)obj[SURNAME]);
			us.addUser(u);
         }
		return us;
	}
	
	/**
	 * Constructs the SQL query to retrieve the specified user.
	 * 
	 * @param 	id	The selected user's id.
	 * @return		User Object with the retrieved info.
	 */
	private User retrieveUser(int id) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		User u = us.getUser(id);
		
		String query = "SELECT DISTINCT" + 
				"    u.id," + 
				"    u.gender," + 
				"    u.birthdate," + 
				"    h.homeAddress," + 
				"    w.workAddress" + 
				" FROM" + 
				"    webapp.users AS u" + 
				"        INNER JOIN" + 
				"    webapp.home_address AS h ON u.id = h.user_id" + 
				"        INNER JOIN" + 
				"    webapp.work_address AS w ON u.id = w.user_id" + 
				" WHERE" + 
				"   u.id = " + id + ";";
		
		Object[] user = um.getSingleUser(query);
		if(user == null) {
			return null;
		}
		
		u.setGender((String)user[GENDER]);
		u.setBirthdate(dateFormat.format(user[BIRTHDATE]));
		
		HomeAddress homeAdd = new HomeAddress();
		homeAdd.setId(id);
		homeAdd.setAddress((String)user[HOMEADDRESS]);
		homeAdd.setUser(u);
		u.setHomeAddress(homeAdd);
		
		WorkAddress workAdd = new WorkAddress();
		workAdd.setId(id);
		workAdd.setAddress((String)user[WORKADDRESS]);
		workAdd.setUser(u);
		u.setWorkAddress(workAdd);
		
		us.addUser(u);
		
		return u;
	}
	
	/**
	 * Constructs the SQL query to delete the selected user.
	 * 
	 * @param 	id	The selected user's id.
	 */
	private void deleteUser(int id) {
		String query = "DELETE FROM `webapp`.`users` " + 
				"WHERE " + 
				"    (`id` = '" + id + "');";
		
		um.deleteUser(query);
	}
}
