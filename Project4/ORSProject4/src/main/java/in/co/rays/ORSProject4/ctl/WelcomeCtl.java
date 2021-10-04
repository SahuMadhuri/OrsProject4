package in.co.rays.ORSProject4.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.ORSProject4.util.ServletUtility;

/**
 * @author Madhuri
 * @version 1.0
 *
 */
@WebServlet(name = "WelcomeCtl", urlPatterns = { "/WelcomeCtl" })
public class WelcomeCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(WelcomeCtl.class);
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("WelcomeCtl Method doGet Started");
        System.out.println("WelcomeCtl Method doGet Started");
		ServletUtility.forward(getView(), request, response);

		log.debug("WelcomeCtl Method doGet Ended");
		System.out.println("WelcomeCtl Method doGet End");
	}

	@Override
	protected String getView() {
		System.out.println("WelcomeCtl Method getview");
		return ORSView.WELCOME_VIEW;
	}

}
