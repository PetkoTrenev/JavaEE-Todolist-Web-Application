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

/*
 * Browser sends Http Request to Web Server
 * 
 * Code in Web Server => Input:HttpRequest, Output: HttpResponse
 * JEE with Servlets
 * 
 * Web Server responds with Http Response
 */

//Java Platform, Enterprise Edition (Java EE) JEE6

//Servlet is a Java programming language class 
//used to extend the capabilities of servers 
//that host applications accessed by means of 
//a request-response programming model.

//1. extends javax.servlet.http.HttpServlet
//2. @WebServlet(urlPatterns = "/login.do")
//3. doGet(HttpServletRequest request, HttpServletResponse response)
//4. How is the response created?

@WebServlet(urlPatterns = "/add-todo.do")
public class AddTodoServlet extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	private TodoDAO todoDao = TodoDAOFactory.getInstance();
	private UserDAO userDao = UserDAOFactory.getInstance();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.getRequestDispatcher("/WEB-INF/views/add-todo.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		String newTodo = request.getParameter("todo");
		String category = request.getParameter("category");
		String code = request.getParameter("priority");
		
		try {
			User user = (User) request.getSession().getAttribute("user");
			User wantedUser = userDao.getUserById(user.getId());
			todoDao.addTodo(new Todo(wantedUser, newTodo, category, Priority.valueOf(code)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// display new todos
		response.sendRedirect("/list-todo.do");
	}
}