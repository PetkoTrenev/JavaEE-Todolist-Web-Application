package webapp.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webapp.daos.UserDAO;
import webapp.daos.UserDAOFactory;
import webapp.models.User;
import webapp.util.Constants;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(urlPatterns = Constants.Pages.REGISTER)
public class RegisterServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private UserDAO userDao = UserDAOFactory.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.getRequestDispatcher(Constants.Views.REGISTER).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");

		try {

			if (userDao.userAlreadyHasEmail(email)) {
				request.setAttribute("errorMessage", "Email taken");
				request.getRequestDispatcher(Constants.Views.REGISTER).forward(request, response);
			}

			else {
				userDao.create(new User(username, password, email));
				User loggedInUser = userDao.login(username, password);
				request.getSession().setAttribute("userId", loggedInUser.getId());
				response.sendRedirect(Constants.Pages.LIST_TODOS_BY_USER);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
