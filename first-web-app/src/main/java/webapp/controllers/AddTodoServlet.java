package webapp.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webapp.daos.TodoDAO;
import webapp.daos.TodoDAOFactory;
import webapp.daos.UserDAO;
import webapp.daos.UserDAOFactory;
import webapp.models.Priority;
import webapp.models.Todo;
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

@WebServlet(urlPatterns = Constants.Pages.ADD_TODO)
public class AddTodoServlet extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	private TodoDAO todoDao = TodoDAOFactory.getInstance();
	private UserDAO userDao = UserDAOFactory.getInstance();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.getRequestDispatcher(Constants.Views.ADD_TODO).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		String newTodo = request.getParameter("todo");
		String category = request.getParameter("category");
		Priority priority = Priority.valueOf(request.getParameter("priority"));

		System.out.println(priority);
		try {
			Long userId = (Long) request.getSession().getAttribute("userId");
			User wantedUser = userDao.getUserById(userId);
			todoDao.addTodo(new Todo(wantedUser, newTodo, category, priority));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// display new todos
		response.sendRedirect(Constants.Pages.LIST_TODOS_BY_USER);
	}
}