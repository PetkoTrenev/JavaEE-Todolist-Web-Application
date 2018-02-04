package webapp.controllers;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webapp.daos.TodoDAO;
import webapp.daos.TodoDAOFactory;
import webapp.util.Constants;

/*
 * Browser sends Http Request to Web Server
 * 
 * Code in Web Server => Input:HttpRequest, Output: HttpResponse
 * JEE with Servlets
 * 
 * Web Server responds with Http Response
 */

@WebServlet(urlPatterns = Constants.Pages.DELETE_TODO)
public class DeleteTodoServlet extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	private TodoDAO todoDao = TodoDAOFactory.getInstance();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		HttpSession session = request.getSession();
		Long userId = (Long) session.getAttribute("userId");

		Integer todoId = Integer.parseInt(request.getParameter("id"));

		try {
			todoDao.deleteTodoByIdAndUserId(todoId, userId);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		// display remaining todos
		response.sendRedirect(Constants.Pages.LIST_TODOS_BY_USER);
	}
}