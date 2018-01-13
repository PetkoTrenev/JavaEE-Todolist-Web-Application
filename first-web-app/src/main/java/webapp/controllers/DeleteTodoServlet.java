package webapp.controllers;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webapp.daos.TodoDAO;
import webapp.daos.TodoDAOFactory;
import webapp.models.Todo;
import webapp.models.User;

/*
 * Browser sends Http Request to Web Server
 * 
 * Code in Web Server => Input:HttpRequest, Output: HttpResponse
 * JEE with Servlets
 * 
 * Web Server responds with Http Response
 */

@WebServlet(urlPatterns = "/delete-todo.do")
public class DeleteTodoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TodoDAO todoDao = TodoDAOFactory.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		//todoDao.deleteTodo(new Todo(request.getParameter("todo"), user, request.getParameter("name"), 
				//request.getParameter("category"), request.getParameter("priority"));
		// display remaining todos
		response.sendRedirect("/list-todo.do");
	}
}