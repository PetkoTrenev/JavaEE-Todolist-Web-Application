package webapp.controllers;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webapp.daos.TodoDAO;
import webapp.daos.TodoDAOFactory;
import webapp.daos.UserDAO;
import webapp.daos.UserDAOFactory;
import webapp.models.User;
import webapp.util.Constants;

/*
 * Browser sends Http Request to Web Server
 * 
 * Code in Web Server => Input:HttpRequest, Output: HttpResponse
 * JEE with Servlets
 * 
 * Web Server responds with Http Response
 */

@WebServlet(urlPatterns = Constants.Pages.LIST_TODOS_BY_USER)
public class ListTodoServlet extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	UserDAO userDao = UserDAOFactory.getInstance();
	TodoDAO todoDao = TodoDAOFactory.getInstance();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		try {
			Long userId = (Long) request.getSession().getAttribute("userId");
			User user = userDao.getUserById(userId);
			request.setAttribute("todos", todoDao.getTodosByUser(user));
			request.getRequestDispatcher(Constants.Views.LIST_TODOS_BY_USER).forward(request, response);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}