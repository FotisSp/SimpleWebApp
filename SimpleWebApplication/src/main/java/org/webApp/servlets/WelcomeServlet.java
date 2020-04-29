package org.webApp.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Fotis Spanopoulos
 *
 */
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("display") != null) {
			response.sendRedirect(request.getContextPath() + "/display/index.jsp");
		} else {
			response.sendRedirect(request.getContextPath() + "/user/index.jsp");
		}
	}

}
