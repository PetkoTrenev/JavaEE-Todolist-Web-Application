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
import webapp.models.Priority;
import webapp.models.Todo;
import webapp.util.Constants;

@WebServlet(Constants.Pages.UPDATE_TODO)
public class UpdateTodoServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	TodoDAO todoDao = TodoDAOFactory.getInstance();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		Long userId = (Long) request.getSession().getAttribute("userId");

		try {
			Todo todo = todoDao.getTodoByIdAndUserId(id, userId);
			request.setAttribute("todo", todo);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		request.getRequestDispatcher(Constants.Views.UPDATE_TODO).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Integer todoId = Integer.parseInt(request.getParameter("todoId"));
		String todoName = request.getParameter("todo");
		String category = request.getParameter("category");
		Priority priority = Priority.valueOf(request.getParameter("priority"));

		Todo todo = new Todo(todoId, todoName, category, priority);

		try {
			todoDao.updateTodo(todo);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		response.sendRedirect(Constants.Pages.LIST_TODOS_BY_USER);
	}

}
