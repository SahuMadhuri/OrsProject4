/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.39
 * Generated at: 2021-10-04 00:56:34 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import in.co.rays.ORSProject4.ctl.ForgetPasswordCtl;
import in.co.rays.ORSProject4.util.DataUtility;
import in.co.rays.ORSProject4.util.ServletUtility;
import in.co.rays.ORSProject4.util.ServletUtility;
import java.util.Enumeration;
import in.co.rays.ORSProject4.ctl.LoginCtl;
import in.co.rays.ORSProject4.ctl.ORSView;
import in.co.rays.ORSProject4.bean.RoleBean;
import in.co.rays.ORSProject4.bean.UserBean;

public final class ForgetPasswordView_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/jsp/Footer.jsp", Long.valueOf(1632119046750L));
    _jspx_dependants.put("/jsp/Header.jsp", Long.valueOf(1632146359550L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("in.co.rays.ORSProject4.util.DataUtility");
    _jspx_imports_classes.add("java.util.Enumeration");
    _jspx_imports_classes.add("in.co.rays.ORSProject4.bean.RoleBean");
    _jspx_imports_classes.add("in.co.rays.ORSProject4.ctl.ForgetPasswordCtl");
    _jspx_imports_classes.add("in.co.rays.ORSProject4.ctl.LoginCtl");
    _jspx_imports_classes.add("in.co.rays.ORSProject4.ctl.ORSView");
    _jspx_imports_classes.add("in.co.rays.ORSProject4.bean.UserBean");
    _jspx_imports_classes.add("in.co.rays.ORSProject4.util.ServletUtility");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<title>Forget Password</title>\r\n");
      out.write("<link rel=\"icon\" type=\"image/png\" href=\"");
      out.print(ORSView.APP_CONTEXT);
      out.write("/img/logo.png\" sizes=\"16x16\"/>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<form action=\"");
      out.print(ORSView.FORGET_PASSWORD_CTL);
      out.write("\" method=\"post\">\r\n");
      out.write("\r\n");
      out.write("\t\t");
      out.write(" \r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write(" \r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<link rel=\"icon\" type=\"image/png\" href=\"");
      out.print(ORSView.APP_CONTEXT);
      out.write("/img/logo.png\" sizes=\"16x16\" />\r\n");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\"\r\n");
      out.write("\thref=\"//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/resources/demos/style.css\">\r\n");
      out.write("<script src=\"https://code.jquery.com/jquery-1.12.4.js\"></script>\r\n");
      out.write("<script src=\"https://code.jquery.com/ui/1.12.1/jquery-ui.js\"></script>\r\n");
      out.write("<script>\r\n");
      out.write("\t$(function() {\r\n");
      out.write("\t\t$(\"#datepicker\").datepicker({\r\n");
      out.write("\t\t\tdateFormat : 'mm/dd/yy',\r\n");
      out.write("\t\t\t//defaultDate : \"06/06/2001\",\r\n");
      out.write("\t\t\tchangeMonth : true,\r\n");
      out.write("\t\t\tchangeYear : true,\r\n");
      out.write("\t\t\t//yearRange: \"c-20:c+0\",\r\n");
      out.write("\t\t\t//yearRange : \"1980:2002\",\r\n");
      out.write("\t\t//maxDate:'0',\r\n");
      out.write("\t\t// minDate:0\r\n");
      out.write("\t\tyearRange : \"-40:-18\"\r\n");
      out.write("\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");

	UserBean userBean = (UserBean) session.getAttribute("user");
    
	boolean userLoggedIn = userBean != null;
	
	String welcomeMsg = "Hi, ";

	if (userLoggedIn) {
		String role = (String) session.getAttribute("role");
		welcomeMsg += userBean.getFirstName() + " (" + role + ")";
		
	} else {
		welcomeMsg += "Guest";
		
	}

      out.write("\r\n");
      out.write("\r\n");
      out.write("<table>\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t\t<td width=\"90%\"><a style=\"text-decoration: none;\"\r\n");
      out.write("\t\t\thref=\"");
      out.print(ORSView.WELCOME_CTL);
      out.write("\"><b>Welcome</b></a> | ");

			if (userLoggedIn) {
		
      out.write(" <a style=\"text-decoration: none;\" href=\"");
      out.print(ORSView.LOGIN_CTL);
      out.write("?operation=");
      out.print(LoginCtl.OP_LOG_OUT);
      out.write("\"><b>Logout</b></a>\r\n");
      out.write("\r\n");
      out.write("\t\t\t");

				} else {
			
      out.write(" <a style=\"text-decoration: none;\" href=\"");
      out.print(ORSView.LOGIN_CTL);
      out.write("\"><b>Login</b></a>\r\n");
      out.write("\t\t\t");

				}
			
      out.write("</td>\r\n");
      out.write("\t\t<td rowspan=\"2\">\r\n");
      out.write("\t\t\t<h1 align=\"Right\">\r\n");
      out.write("\t\t\t\t<img src=\"");
      out.print(ORSView.APP_CONTEXT);
      out.write("/img/customLogo.jpg\" width=\"318\"\r\n");
      out.write("\t\t\t\t\theight=\"90\">\r\n");
      out.write("\t\t\t</h1>\r\n");
      out.write("\t\t</td>\r\n");
      out.write("\r\n");
      out.write("\t</tr>\r\n");
      out.write("\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t\t<td>\r\n");
      out.write("\t\t\t<h3>");
      out.print(welcomeMsg);
      out.write("</h3>\r\n");
      out.write("\t\t</td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t");

		if (userLoggedIn) {
	
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t\t<td colspan=\"2\"><a href=\"");
      out.print(ORSView.MY_PROFILE_CTL);
      out.write("\">My Profile</a>    \r\n");
      out.write("\t\t\t\t      | <a href=\"");
      out.print(ORSView.CHANGE_PASSWORD_CTL);
      out.write("\">Change Password</a> \r\n");
      out.write("\t\t\t\t      | <a href=\"");
      out.print(ORSView.GET_MARKSHEET_CTL);
      out.write("\">Get Marksheet</a> \r\n");
      out.write("\t\t\t\t      | <a href=\"");
      out.print(ORSView.MARKSHEET_MERIT_LIST_CTL);
      out.write("\">Marksheet Merit List </a> \r\n");
      out.write("  ");

 	if (userBean.getRoleId() == RoleBean.ADMIN) {
 
      out.write(" | <a href=\"");
      out.print(ORSView.COLLEGE_CTL);
      out.write("\">Add College</a> \r\n");
      out.write("    | <a href=\"");
      out.print(ORSView.COLLEGE_LIST_CTL);
      out.write("\">College List</a> \r\n");
      out.write("    | <a href=\"");
      out.print(ORSView.MARKSHEET_CTL);
      out.write("\">Add Marksheet</a> \r\n");
      out.write("    | <a href=\"");
      out.print(ORSView.MARKSHEET_LIST_CTL);
      out.write("\">Marksheet List</a> \r\n");
      out.write("    | <a href=\"");
      out.print(ORSView.USER_CTL);
      out.write("\">Add User</a> \r\n");
      out.write("    | <a href=\"");
      out.print(ORSView.USER_LIST_CTL);
      out.write("\">User List</a> \r\n");
      out.write("    | <a href=\"");
      out.print(ORSView.ROLE_CTL);
      out.write("\">Add Role</a> \r\n");
      out.write("    | <a href=\"");
      out.print(ORSView.ROLE_LIST_CTL);
      out.write("\">Role List</a>\r\n");
      out.write("     ");

 	}

 		if (userBean.getRoleId() == RoleBean.COLLEGE || userBean.getRoleId() == RoleBean.ADMIN) {
 
      out.write(" | <a href=\"");
      out.print(ORSView.STUDENT_CTL);
      out.write("\">Add Student</a> | \r\n");
      out.write("      <a href=\"");
      out.print(ORSView.STUDENT_LIST_CTL);
      out.write("\">Student List</a> | <a\r\n");
      out.write("\t\t\thref=\"");
      out.print(ORSView.COURSE_CTL);
      out.write("\">Add Course</a> | <a\r\n");
      out.write("\t\t\thref=\"");
      out.print(ORSView.COURSE_LIST_CTL);
      out.write("\">Course List</a> | <a\r\n");
      out.write("\t\t\thref=\"");
      out.print(ORSView.SUBJECT_CTL);
      out.write("\">Add Subject</a> | <a\r\n");
      out.write("\t\t\thref=\"");
      out.print(ORSView.SUBJECT_LIST_CTL);
      out.write("\">Subject List</a> | <a\r\n");
      out.write("\t\t\thref=\"");
      out.print(ORSView.FACULTY_CTL);
      out.write("\">Add Faculty</a> | <a\r\n");
      out.write("\t\t\thref=\"");
      out.print(ORSView.FACULTY_LIST_CTL);
      out.write("\">Faculty List</a> ");

 	}
 		if (userBean.getRoleId() == RoleBean.COLLEGE || userBean.getRoleId() == RoleBean.FACULTY || userBean.getRoleId() == RoleBean.ADMIN) {
 
      out.write(" | <a href=\"");
      out.print(ORSView.TIMETABLE_CTL);
      out.write("\">Add Timetable</a> | <a\r\n");
      out.write("\t\t\thref=\"");
      out.print(ORSView.TIMETABLE_LIST_CTL);
      out.write("\">Timetable List</a> ");

 	}

 		if (userBean.getRoleId() == RoleBean.ADMIN) {
 
      out.write(" | <a href=\"");
      out.print(ORSView.JAVA_DOC_VIEW);
      out.write("\" target=\"blank\">Java Doc</a> ");

 	}
 	} else {
 
      out.write(" <a href=\"");
      out.print(ORSView.LOGIN_CTL);
      out.write("\"></a> ");

 	}
 
      out.write("</td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("\r\n");
      out.write("</table>\r\n");
      out.write("<hr>\r\n");
      out.write("</html>");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t");
      in.co.rays.ORSProject4.bean.UserBean bean = null;
      bean = (in.co.rays.ORSProject4.bean.UserBean) _jspx_page_context.getAttribute("bean", javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      if (bean == null){
        bean = new in.co.rays.ORSProject4.bean.UserBean();
        _jspx_page_context.setAttribute("bean", bean, javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      }
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t<div align=\"center\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t<h1 align=\"center\" style=\"margin-bottom: -15; color: navy\">Forgot\r\n");
      out.write("\t\t\t\tyour password?</h1>\r\n");
      out.write("\r\n");
      out.write("\t\t\t<div style=\"height: 15px; margin-bottom: 12px\">\r\n");
      out.write("\t\t\t\t<H3 align=\"center\">\r\n");
      out.write("\t\t\t\t\t<font color=\"green\"> ");
      out.print(ServletUtility.getSuccessMessage(request));
      out.write("\r\n");
      out.write("\t\t\t\t\t</font>\r\n");
      out.write("\t\t\t\t</H3>\r\n");
      out.write("\t\t\t\t<H3 align=\"center\">\r\n");
      out.write("\t\t\t\t\t<font color=\"red\"> ");
      out.print(ServletUtility.getErrorMessage(request));
      out.write("\r\n");
      out.write("\t\t\t\t\t</font>\r\n");
      out.write("\t\t\t\t</H3>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<input type=\"hidden\" name=\"id\" value=\"");
      out.print(bean.getId());
      out.write("\">\r\n");
      out.write("\t\t\t<h3 style=\"margin-bottom: -10;\">\r\n");
      out.write("\t\t\t\t<label>Submit your email address and we'll send you\r\n");
      out.write("\t\t\t\t\tpassword.</label>\r\n");
      out.write("\t\t\t</h3>\r\n");
      out.write("\t\t\t<table>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<th align=\"left\">Email Id<span style=\"color: red\">*</span>&nbsp;\r\n");
      out.write("\t\t\t\t\t</th>&emsp;\r\n");
      out.write("\t\t\t\t\t<td align=\"center\"><input type=\"text\" name=\"login\"\r\n");
      out.write("\t\t\t\t\t\tplaceholder=\"Enter Email ID Here\"\r\n");
      out.write("\t\t\t\t\t\tvalue=\"");
      out.print(ServletUtility.getParameter("login", request));
      out.write("\">&nbsp;</td>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t<td align=\"center\"><input type=\"submit\" name=\"operation\"\r\n");
      out.write("\t\t\t\t\t\tvalue=\"");
      out.print(ForgetPasswordCtl.OP_GO);
      out.write("\"></td>\r\n");
      out.write("\t\t\t\t\t<td style=\"position: fixed;\"><font color=\"red\"> ");
      out.print(ServletUtility.getErrorMessage("login", request));
      out.write("</font></td>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</form>\r\n");
      out.write("\r\n");
      out.write("\t");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("div.sticky {\r\n");
      out.write("\tposition: -webkit-sticky;\r\n");
      out.write("\tposition: sticky;\r\n");
      out.write("\twidth: 100%;\r\n");
      out.write("\ttext-align:center;\r\n");
      out.write("\tbottom: 0;\r\n");
      out.write("\ttop: 100%;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<div class=\"sticky\">\r\n");
      out.write("\t<HR>\r\n");
      out.write("\t<strong>Copyrights &copy; Rays Technologies 2021</strong>\r\n");
      out.write("\t\r\n");
      out.write("</div>\r\n");
      out.write("</html>");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
