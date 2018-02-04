package webapp.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webapp.daos.UserDAO;
import webapp.daos.UserDAOFactory;
import webapp.models.User;
import webapp.util.Constants;

@WebServlet(urlPatterns = Constants.Pages.LOGIN)
public class LoginServlet extends HttpServlet
{

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;

	private UserDAO userDao = UserDAOFactory.getInstance();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		request.getRequestDispatcher(Constants.Views.LOGIN).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			User validUser = userDao.login(username, password);

			if (validUser != null) {
				request.getSession().setAttribute("userId", validUser.getId());
				response.sendRedirect(Constants.Pages.LIST_TODOS_BY_USER);
			} else {
				request.setAttribute("errorMessage", "Invalid Credentials!");
				request.getRequestDispatcher(Constants.Views.LOGIN).forward(request, response);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}