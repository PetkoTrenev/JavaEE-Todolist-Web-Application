package webapp.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webapp.daos.TodoService;
import webapp.models.LoginService;

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

@WebServlet(urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LoginService userValidationService = new LoginService();

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {			
			request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			String username = request.getParameter("name");
			String password = request.getParameter("password");
			
			boolean isUserValid = userValidationService.isUserValid(username, password);
			
			if (isUserValid) {
				// i want to persist his name
				request.getSession().setAttribute("name", username);
				response.sendRedirect("/list-todo.do");
			} else {
				request.setAttribute("errorMessage", "Invalid Credentials!");
				request.getRequestDispatcher("WEB-INF/views/login.jsp")
											.forward(request, response);
			}
			
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}