package in.co.rays.ORSProject4.ctl;

import in.co.rays.ORSProject4.bean.BaseBean;
import in.co.rays.ORSProject4.bean.UserBean;
import in.co.rays.ORSProject4.exception.ApplicationException;
import in.co.rays.ORSProject4.model.RoleModel;
import in.co.rays.ORSProject4.model.UserModel;
import in.co.rays.ORSProject4.util.DataUtility;
import in.co.rays.ORSProject4.util.PropertyReader;
import in.co.rays.ORSProject4.util.ServletUtility;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * @author Madhuri
 * @version 1.0
 *
 */
@WebServlet(name = "UserListCtl", urlPatterns = { "/ctl/UserListCtl" })
public class UserListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(UserListCtl.class);

	@SuppressWarnings("rawtypes")
	@Override
	protected void preload(HttpServletRequest request) {
		System.out.println("UserListCtl preload mathod started");
		RoleModel roleModel = new RoleModel();

		try {
			List roleList = roleModel.list();
			request.setAttribute("roleList", roleList);

		} catch (ApplicationException e) {
			log.error(e);
		}
		System.out.println("UserListCtl preload mathod end");
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		System.out.println("UserListCtl populateBean mathod started");
		UserBean bean = new UserBean();

		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));

		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));

		bean.setLogin(DataUtility.getString(request.getParameter("login")));

		bean.setRoleId(DataUtility.getLong(request.getParameter("roleId")));
		System.out.println("UserListCtl populateBean mathod ended");
		return bean;
	}

	/**
	 * Contains Display logics
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("UserListCtl doGet Start");
		System.out.println("UserListCtl doGet Start");
		List list = null;
		List next = null;

		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
		UserBean bean = (UserBean) populateBean(request);
		DataUtility.getString(request.getParameter("operation"));

		// get the selected checkbox ids array for delete list
		@SuppressWarnings("unused")
		String[] ids = request.getParameterValues("ids");
		UserModel model = new UserModel();
		try {
			list = model.search(bean, pageNo, pageSize);
			Collections.sort(list);
			next = model.search(bean, pageNo + 1, pageSize);
			// ServletUtility.setList(list, request);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
			}
			request.setAttribute("nextListSize", next.size());
			ServletUtility.setList(list, request);
			ServletUtility.setBean(bean, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);
		} catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("UserListCtl doPOst End");
		System.out.println("UserListCtl doget End");
	}

	/**
	 * Contains Submit logics
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("UserListCtl doPost Start");
		System.out.println("UserListCtl doPost Start");
		List list = null;
		List next = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;
		UserBean bean = (UserBean) populateBean(request);
		String op = DataUtility.getString(request.getParameter("operation"));
		// get the selected checkbox ids array for delete list
		String[] ids = request.getParameterValues("ids");
		UserModel model = new UserModel();

		try {

			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {

				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				}

			} else if (OP_NEW.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.USER_CTL, request, response);
				return;
			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null && ids.length > 0) {
					UserBean deletebean = new UserBean();
					for (String id : ids) {
						deletebean.setId(DataUtility.getInt(id));
						System.out.println("UserListctl dopost delete operation");
						model.delete(deletebean);
						ServletUtility.setSuccessMessage("Data is deleted successfully", request);
					}
				} else {
					ServletUtility.setErrorMessage("Select at least one record", request);
				}
			} else if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.USER_LIST_CTL, request, response);
				return;
			} else if (OP_BACK.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.USER_LIST_CTL, request, response);
				return;
			}

			list = model.search(bean, pageNo, pageSize);
			System.out.println("UserListCtl doPost List: "+list);
			Collections.sort(list);
			ServletUtility.setBean(bean, request);
			next = model.search(bean, pageNo + 1, pageSize);
			System.out.println("UserListCtl doPost next: "+next);
			if (!OP_DELETE.equalsIgnoreCase(op)) {
				if (list == null || list.size() == 0) {
					ServletUtility.setErrorMessage("No record found ", request);
				}
			}
			request.setAttribute("nextListSize", next.size());
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("UserListCtl doPost End");
		System.out.println("UserListCtl doPost End");
	}

	@Override
	protected String getView() {
		return ORSView.USER_LIST_VIEW;
	}
}
