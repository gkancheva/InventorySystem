package bg.softuni.web.filter;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bg.softuni.dto.UserDto;

public class AuthenticationFilter implements Filter, Serializable {


	private static final long serialVersionUID = 1L;
	
	public static final String PATH_INDEX = "/index.jsp";
	public static final String PATH_LOGIN = "/page/login.html";
	public static final String PATH_LOGOUT = "/page/logout.jsp";
	

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void destroy() {
	}

	/**
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;

		String requestedPath = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

		if (PATH_INDEX.equals(requestedPath)) {
			chain.doFilter(request, response);
			return;
		}

		if (PATH_LOGIN.equals(requestedPath)) {
			chain.doFilter(request, response);
			return;
		}

		HttpSession session = httpRequest.getSession();
		UserDto loggedUser = (UserDto) session.getAttribute("LOGGED_USER");

		if (loggedUser == null) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH_LOGIN);
			requestDispatcher.forward(request, response);
			return;
		} else {
			chain.doFilter(request, response);
			return;
		}		

	}
}
