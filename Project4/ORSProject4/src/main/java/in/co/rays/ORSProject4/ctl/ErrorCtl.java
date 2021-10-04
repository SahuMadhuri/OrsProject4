package in.co.rays.ORSProject4.ctl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.ORSProject4.util.ServletUtility;

/**
 * @author Madhuri
 * @version 1.0
 *
 */
@WebServlet(name="ErrorCtl", urlPatterns="/ErrorCtl")
public class ErrorCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(ErrorCtl.class);

	/**
	 * Contains Display logics
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("ErrorCtl Method doGet Started");
		System.out.println("ErrorCtl Method doGet Started");
		ServletUtility.forward(getView(), request, response);

		log.debug("ErrorCtl Method doGetEnded");
		System.out.println("ErrorCtl Method doGetEnded");
	}

	/**
	 * Contains Submit logics
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("ErrorCtl Method doGet Started");
		System.out.println("ErrorCtl Method dopost Started");

		ServletUtility.forward(getView(), request, response);

		log.debug("ErrorCtl Method doPost Ended");
		System.out.println("ErrorCtl Method dopost end");
	}

	@Override
	protected String getView() {
		System.out.println("ErrorCtl getView");
		return ORSView.ERROR_VIEW;
	}


}
