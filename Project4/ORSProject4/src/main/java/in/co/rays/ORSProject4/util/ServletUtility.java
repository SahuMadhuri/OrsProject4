package in.co.rays.ORSProject4.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.ORSProject4.bean.BaseBean;
import in.co.rays.ORSProject4.ctl.BaseCtl;
import in.co.rays.ORSProject4.ctl.ORSView;
import in.co.rays.ORSProject4.model.BaseModel;


/**
 * @author Madhuri
 * @version 1.0
 *
 */
public class ServletUtility {
	public static void forward(String page, HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
	//	System.out.println("ServletUtility forward mathod started");
		RequestDispatcher rd = request.getRequestDispatcher(page);
		
		rd.forward(request, response);

	}

	/**
	 * Forward to Layout View.
	 *
	 * @param page the page
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void forwardView(String page, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//System.out.println("ServletUtility forwardView mathod started");
		request.setAttribute("bodyPage", page);
		RequestDispatcher rd = request.getRequestDispatcher(ORSView.LAYOUT_VIEW);
	//	System.out.println("request.getRequestDispatcher(ORSView.LAYOUT_VIEW): "+rd);
		rd.forward(request, response);
	}

	/**
	 * Redirect to given JSP/Servlet.
	 *
	 * @param page the page
	 * @param request the request
	 * @param response the response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void redirect(String page, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
	//	System.out.println("ServletUtility redirect mathod started");
		response.sendRedirect(page);
	}

	/**
	 * Redirect to Application Error Handler Page.
	 *
	 * @param e the e
	 * @param request the request
	 * @param response the response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void handleException(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
		//System.out.println("ServletUtility handleException mathod started");
		request.setAttribute("exception", e);
		
		
		response.sendRedirect(ORSView.ERROR_CTL);
		
	}
	
	

	
	/**
	 * Sets the bean.
	 *
	 * @param bean the bean
	 * @param request the request
	 */
	public static void setBean(BaseBean bean, HttpServletRequest request) {
	//	System.out.println("ServletUtility setBean Mathod started");
		request.setAttribute("bean", bean);
	}

	/**
	 * Sets the error message.
	 *
	 * @param msg the msg
	 * @param request the request
	 */
	public static void setErrorMessage(String msg, HttpServletRequest request) {
	//	System.out.println("ServletUtility setErrorMessage started");
		request.setAttribute(BaseCtl.MSG_ERROR, msg);
	}

	/**
	 * Gets the error message.
	 *
	 * @param property the property
	 * @param request the request
	 * @return the error message
	 */
	public static String getErrorMessage(String property, HttpServletRequest request) {
	//	System.out.println("ServletUtility getErrorMessage started");
		String val = (String) request.getAttribute(property);
		System.out.println("ServletUtility getErrorMessage val ...."+val);

		if (val == null) {
			return "";
		} else {
			return val;
		}
	}

	/**
	 * Gets the error message.
	 *
	 * @param request the request
	 * @return the error message
	 */
	public static String getErrorMessage(HttpServletRequest request) {
	//	System.out.println("ServletUtility getErrorMessage started3");
        String val = (String) request.getAttribute(BaseCtl.MSG_ERROR);
  //      System.out.println("ServletUtility getErrorMessage val3"+val);
        if (val == null) {
            return "";
        } else {
            return val;
        }
    }
	
	/**
	 * Sets the success message.
	 *
	 * @param msg the msg
	 * @param request the request
	 */
	public static void setSuccessMessage(String msg, HttpServletRequest request) {
	//	System.out.println("ServletUtility setSuccessMessage started");
		request.setAttribute(BaseCtl.MSG_SUCCESS, msg);
	}

	/**
	 * Gets the success message.
	 *
	 * @param request the request
	 * @return the success message
	 */
	public static String getSuccessMessage(HttpServletRequest request) {
	//	System.out.println("ServletUtility setSuccessMessage started");
		String val = (String) request.getAttribute(BaseCtl.MSG_SUCCESS);
	//	System.out.println("ServletUtility setSuccessMessage val4"+val);
		if (val == null) {
			return "";
		} else {
			return val;
		}

	}

	/**
	 * Sets the model.
	 *
	 * @param model the model
	 * @param request the request
	 */
	public static void setModel(BaseModel model, HttpServletRequest request) {
	//	System.out.println("ServletUtility setModel started");
		request.setAttribute("model", model);
	}

	/**
	 * Gets the model.
	 *
	 * @param request the request
	 * @return the model
	 */
	public static BaseModel getModel(HttpServletRequest request) {
	//	System.out.println("ServletUtility getModel started");
		return (BaseModel) request.getAttribute("model");
	}
	
	/**
	 * Get request parameter to display. If value is null then return empty
	 * string
	 *
	 * @param property the property
	 * @param request the request
	 * @return the parameter
	 */

    public static String getParameter(String property, HttpServletRequest request) {
   // 	System.out.println("ServletUtility getParameter mathod started");
        String val = (String) request.getParameter(property);
        if (val == null) {
            return "";
        } else {
            return val;
        }
    }
    
    /**
     * Sets the list.
     *
     * @param list the list
     * @param request the request
     */
    public static void setList(List list,HttpServletRequest request){
  //  	System.out.println("ServletUtility setList started");
    	request.setAttribute("list", list);
    }
    
    /**
     * Gets the list.
     *
     * @param request the request
     * @return the list
     */
    public static List getList(HttpServletRequest request){
 //   	System.out.println("ServletUtility getList started");
    	return (List) request.getAttribute("list"); 
    }
    
    /**
     * Sets the page no.
     *
     * @param pageNo the page no
     * @param request the request
     */
    public static void setPageNo(int pageNo, HttpServletRequest request) {
    //	System.out.println("ServletUtility setPageNo started");
		request.setAttribute("pageNo", pageNo);
	}
    
    /**
     * Gets the page no.
     *
     * @param request the request
     * @return the page no
     */
    public static int getPageNo(HttpServletRequest request){
    //	System.out.println("ServletUtility getpageNo started");
    	return (Integer)request.getAttribute("pageNo");
    }

   
    public static void setPageSize(int pageSize,HttpServletRequest request){
   // 	System.out.println("ServletUtility setPageSize started");
    	request.setAttribute("pageSize",pageSize);
    	
    }
    
   
    public static int getPageSize(HttpServletRequest request){
    //	System.out.println("ServletUtility getPageSize started");
    	return (Integer) request.getAttribute("pageSize");
    }

}
