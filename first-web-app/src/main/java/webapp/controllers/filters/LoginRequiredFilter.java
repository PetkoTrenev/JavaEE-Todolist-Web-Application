package webapp.controllers.filters;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import webapp.util.Constants;

@WebFilter(urlPatterns = { "*.do" })
public class LoginRequiredFilter implements Filter
{

	private static final Set<String> ALLOWED_PATHS = Collections
			.unmodifiableSet(new HashSet<>(Arrays.asList("", Constants.Pages.LOGIN, Constants.Pages.REGISTER)));

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException
	{
		HttpServletRequest request = (HttpServletRequest) servletRequest;

		String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
		boolean allowedPath = ALLOWED_PATHS.contains(path);
		Object userId = request.getSession().getAttribute("userId");

		if (allowedPath || userId != null) {
			chain.doFilter(servletRequest, servletResponse);
		} else {
			request.getRequestDispatcher(Constants.Views.WELCOME).forward(servletRequest, servletResponse);
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException
	{

	}

	@Override
	public void destroy()
	{

	}

}
