package in.co.rays.ORSProject4.ctl;

import java.io.IOException;

import javax.mail.Session;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.co.rays.ORSProject4.util.ServletUtility;

/**
 * @author Madhuri
 * @version 1.0
 *
 */
@WebFilter(urlPatterns = { "/doc/*", "/ctl/*" })//wildcard mapping
public class FrontController implements Filter {

	public void init(FilterConfig conf) throws ServletException {
		System.out.println(" FrontController FilterConfig Mathod");
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("FrontController dofilter start");

		HttpServletRequest request = (HttpServletRequest) req;
		//System.out.println("Frontcontroller request"+request);
		HttpServletResponse response = (HttpServletResponse) resp;
		//System.out.println("Frontcontroller request"+response);

		HttpSession session = request.getSession();

		String uri = request.getRequestURI();
		System.out.println("URI FrontControlle......: "+uri);
		request.setAttribute("uri", uri);
		System.out.println("session.getAttribute(user) : "+session.getAttribute("user"));

		if (session.getAttribute("user") == null) {
			request.setAttribute("error", "Your session has been expired. Please Login again!");
			System.out.println("FrontController dofilter forward loginview");
			ServletUtility.forward(ORSView.LOGIN_VIEW, request, response);
			return;
		} else {
			System.out.println("FrontController dofilter chain.doFilter");
			chain.doFilter(req, resp);
		}
	}

	public void destroy() {
		System.out.println("Filter destroy method");
	}
}
