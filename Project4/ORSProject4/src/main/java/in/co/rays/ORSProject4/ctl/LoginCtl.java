package in.co.rays.ORSProject4.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.rays.ORSProject4.bean.BaseBean;
import in.co.rays.ORSProject4.bean.RoleBean;
import in.co.rays.ORSProject4.bean.UserBean;
import in.co.rays.ORSProject4.exception.ApplicationException;
import in.co.rays.ORSProject4.model.RoleModel;
import in.co.rays.ORSProject4.model.UserModel;
import in.co.rays.ORSProject4.util.DataUtility;
import in.co.rays.ORSProject4.util.DataValidator;
import in.co.rays.ORSProject4.util.PropertyReader;
import in.co.rays.ORSProject4.util.ServletUtility;




/**
 * @author Madhuri
 * @version 1.0
 *
 */
@WebServlet(name = "LoginCtl", urlPatterns = { "/LoginCtl" })
public class LoginCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_REGISTER = "Register";
	public static final String OP_SIGN_IN = "Sign In";
	public static final String OP_SIGN_UP = "Sign Up";
	public static final String OP_LOG_OUT = "Logout";

	private static Logger log = Logger.getLogger(LoginCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("LoginCtl Method validate Started");
		System.out.println("LoginCtl Method validate Started");

		boolean pass = true;
		

		String op = request.getParameter("operation");
		System.out.println("opration : "+op);
		if (OP_SIGN_UP.equals(op) || OP_LOG_OUT.equals(op)) {
			return pass;
		}

		

		if (DataValidator.isNull(request.getParameter("login"))) {
			request.setAttribute("login", PropertyReader.getValue("error.require", "Login Id"));
			System.out.println("LoginCtl login id is null");
			pass = false;
		} else if (!DataValidator.isEmail(request.getParameter("login"))) {
			request.setAttribute("login", PropertyReader.getValue("error.email", "Login "));
			System.out.println("LoginCtl login id is not Email");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", PropertyReader.getValue("error.require", "Password"));
			System.out.println("LoginCtl password is null");
			pass = false;
		}

		log.debug("LoginCtl Method validate Ended");
		System.out.println("Logincl pass"+pass);
		System.out.println("LoginCtl Method validate Ended");
        
		return pass;
		
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("LoginCtl Method populatebean Started");
		System.out.println("LoginCtl Method populatebean Started");

		UserBean bean = new UserBean();
      	bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));

		log.debug("LoginCtl Method populatebean Ended");
		System.out.println("LoginCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * Display Login form
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		log.debug(" LOginCtl Method doGet Started");
		System.out.println(" LOginCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		System.out.println(" loginCtl deget operation2 : "+op);

		// get model
		UserModel model = new UserModel();

		long id = DataUtility.getLong(request.getParameter("id"));
		System.out.println("LoginCtl Id "+id);
		if (id > 0) {
			UserBean userbean;
			try {
				userbean = model.findByPK(id);
				System.out.println("Loginctl doget mathod userbean : "+userbean);
				ServletUtility.setBean(userbean, request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		}

		if (OP_LOG_OUT.equals(op)) {
System.out.println("Loginctl doget operation logout");
			session.invalidate();

			ServletUtility.setSuccessMessage("Logout Successful!", request);

			ServletUtility.forward(getView(), request, response);

			return;
		}

		ServletUtility.forward(getView(), request, response);

		log.debug("LoginCtl Method doGet Ended");
		System.out.println("LoginCtl Method doGet Ended");

	}

	/**
	 * Submitting or login action performing
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
	
		log.debug(" LoginCtl Method doPost Started");
		System.out.println(" LoginCtl Method doPost Started");
		System.out.println(" LoginCtl Method doPost session"+session);

		String op = DataUtility.getString(request.getParameter("operation"));
		System.out.println("operation2 :"+op);

		// get model
		UserModel model = new UserModel();
		RoleModel role = new RoleModel();

		DataUtility.getLong(request.getParameter("id"));
		

		if (OP_SIGN_IN.equalsIgnoreCase(op)) {

			UserBean bean = (UserBean) populateBean(request);
			

			try {

				bean = model.authenticate(bean.getLogin(), bean.getPassword());
				
				

				if (bean != null) {
					session.setAttribute("user", bean);
					long roleId = bean.getRoleId();
					System.out.println("Loginctl Role Id"+roleId);

					RoleBean rolebean = role.findByPk(roleId);
					System.out.println(" LoginCtl dopost rolebean"+rolebean);

					if (rolebean != null) {
						System.out.println("LoginCtl Dopost rolebean get name"+rolebean);
						session.setAttribute("role", rolebean.getName());
					}

					String uri = (String) request.getParameter("uri");
					System.out.println("LoginCtl dopost Uri : "+uri);
					if (uri == null || "null".equalsIgnoreCase(uri)) {
						ServletUtility.redirect(ORSView.WELCOME_CTL, request, response);
						return;
					} else {
						ServletUtility.redirect(uri, request, response);
						return;
					}
				} else {
					bean = (UserBean) populateBean(request);
					ServletUtility.setBean(bean, request);
					ServletUtility.setErrorMessage("Invalid LoginId And Password", request);
				}
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_SIGN_UP.equalsIgnoreCase(op)) {
System.out.println("LoginCtl Dopost operation sign up condition");
			ServletUtility.redirect(ORSView.USER_REGISTRATION_CTL, request, response);
			System.out.println("LoginCtl Dopost operation sign up condition2");
			System.out.println();
			return;

		}
		System.out.println("LoginCtl Method doPost forward loginView ");
		ServletUtility.forward(getView(), request, response);

		log.debug("UserCtl Method doPost Ended");
		System.out.println("LoginCtl Method doPost Ended");
	}

	@Override
	protected String getView() {
		System.out.println("LoginCtl Method GetView");
		return ORSView.LOGIN_VIEW;
	}


}
