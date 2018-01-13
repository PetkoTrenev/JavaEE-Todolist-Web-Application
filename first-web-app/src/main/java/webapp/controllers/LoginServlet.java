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

@WebServlet(urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserDAO userDao = UserDAOFactory.getInstance();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		try {
			request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			User validUser = userDao.login(username, password);
			
			if (validUser != null) {
				request.getSession().setAttribute("userId", validUser.getId());				
				response.sendRedirect("/list-todo.do");
			} else {
				//request.setAttribute("errorMessage", "Invalid Credentials!");
				//request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
				response.sendRedirect("welcome.jsp");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}