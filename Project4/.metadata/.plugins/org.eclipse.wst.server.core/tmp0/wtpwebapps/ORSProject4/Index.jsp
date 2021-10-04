<%@page import="in.co.rays.ORSProject4.util.ServletUtility"%>
<%@page import="in.co.rays.ORSProject4.bean.UserBean"%>
<%@page import="in.co.rays.ORSProject4.ctl.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html>
<head>
<title>Online Result System</title>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>
	
	 <%
		UserBean userBean = (UserBean) session.getAttribute("user");
        System.out.println("indexJsp userbean index : "+userBean);
		boolean userLoggedIn = userBean != null;
		System.out.println("indexJsp userLoggedIn index : "+userLoggedIn);

		if (userLoggedIn) {
			ServletUtility.forward(ORSView.WELCOME_VIEW, request, response);
			
		}
	%> 
	<div>	
    <h1 align="Center"><img src="./img/customLogo.jpg" width="400" height="150" border="0">
    </h1>
    
    </div>
    
<div>
    <h1 align="Center">
    
        <font color="cyan" ; size="10px" >

         <a href="<%=ORSView.WELCOME_CTL%>">Online Result System</a>
        </font> 
    </h1>
</div>

</body>
</body>
</html>