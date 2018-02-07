package webapp.util;

public class Constants
{
	public static class Views
	{
		private Views() {
		}

		private static final String PREFIX = "/WEB-INF";

		public static final String LOGIN = PREFIX + "/login.jsp";

		public static final String REGISTER = PREFIX + "/register.jsp";

		public static final String WELCOME = PREFIX + "/welcome.jsp";

		public static final String ADD_TODO = PREFIX + "/add-todo.jsp";

		public static final String LIST_TODOS_BY_USER = PREFIX + "/list-todo.jsp";

		public static final String UPDATE_TODO = PREFIX + "/update-todo.jsp";

	}

	public static class Pages
	{
		private Pages() {
		}

		public static final String WELCOME = "/welcome.jsp";

		public static final String LOGIN = "/login.do";

		public static final String REGISTER = "/register.do";

		public static final String LIST_TODOS_BY_USER = "/list-todo.do";

		public static final String ADD_TODO = "/add-todo.do";

		public static final String LOGOUT = "/logout.do";

		public static final String DELETE_TODO = "/delete-todo.do";

		public static final String UPDATE_TODO = "/update-todo.do";
	}

}
