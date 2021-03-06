<%@page import="in.co.rays.ORSProject4.util.HTMLUtility"%>
<%@page import="in.co.rays.ORSProject4.util.DataUtility"%>
<%@page import="in.co.rays.ORSProject4.ctl.CourseListCtl"%>
<%@page import="in.co.rays.ORSProject4.ctl.BaseCtl"%>
<%@page import="in.co.rays.ORSProject4.bean.CourseBean"%>
<%@page import="in.co.rays.ORSProject4.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html>
<head>
<title>Course List</title>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=ORSView.APP_CONTEXT%>/js/CheckBox.js">
	
</script>
</head>
<body>

	<%@include file="Header.jsp"%>
	<jsp:useBean id="bean" class="in.co.rays.ORSProject4.bean.CourseBean" scope="request"></jsp:useBean>
	<div align="center">
	<%
		List<CourseBean> courselist = (List<CourseBean>)request.getAttribute("CourseList");
	List<CourseBean> disList = (List<CourseBean>)request.getAttribute("DisList");
	%>
		<h1 align="center" style="margin-bottom: -15; color: navy;">Course
			List</h1>

		<div style="height: 15px; margin-bottom: 12px">
			<h3>
				<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
			</h3>
			<h3>
				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
			</h3>
		</div>

		<form action="<%=ORSView.COURSE_LIST_CTL%>" method="post">
			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;
				int nextPageSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());

				@SuppressWarnings("unchecked")
				List<CourseBean> courseList = (List<CourseBean>) request.getAttribute("courseList");
			
				@SuppressWarnings("unchecked")
				List<CourseBean> list = (List<CourseBean>) ServletUtility.getList(request);
				Iterator<CourseBean> it = list.iterator();

				if (list.size() != 0) {
			%>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">

			<table style="width: 100%">
				<tr>
					<tr>
                 <td align="center">
                 <label><b>College Name :</b></label> <%-- <input type="text"
						name="collegeName" placeholder="Enter college Name"
						value="<%=ServletUtility.getParameter("collegeName", request)%>"> --%>&emsp;
						<%=HTMLUtility.getList("ids", String.valueOf(bean.getId()), courselist) %>
						&nbsp; 
						
						<input type="submit" name="operation"
						value="<%=CourseListCtl.OP_SEARCH%>">&nbsp; <input
						type="submit" name="operation" value="<%=CourseListCtl.OP_RESET%>">
					</td>
				</tr>
			</table>
			<br>

			<table border="1" style="width: 100%; border: groove;">
				<tr style="background-color: #e1e6f1e3;">
					<th width="5%"><input type="checkbox" id="selectall" /></th>
					<th width="5%">S.No</th>
					<th width="25%">Name</th>
					<th width="15%">Duration</th>
					<th width="45%">Description</th>
					<th width="5%">Edit</th>
				</tr>

				
			<%	while (it.hasNext()) {
							bean = (CourseBean) it.next();
				%>
				<tr>
				
					<td style="text-align: center;"><input type="checkbox"
						class="case" name="ids" value="<%=bean.getId()%>"></td>
					<td style="text-align: center;"><%=index++%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getName()%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getDuration()%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getDescription()%></td>
					<td style="text-align: center;"><a
						href="CourseCtl?id=<%=bean.getId()%>">Edit</a></td>
				</tr>
				<%
					}
				%>
			</table>
			<table style="width: 100%">
				<tr>

					<td style="width: 25%"><input type="submit" name="operation"
						value="<%=CourseListCtl.OP_PREVIOUS%>"
						<%=pageNo > 1 ? "" : "disabled"%>></td>
					<td align="center" style="width: 25%"><input type="submit"
						name="operation" value="<%=CourseListCtl.OP_NEW%>"></td>
					<td align="center" style="width: 25%"><input type="submit"
						name="operation" value="<%=CourseListCtl.OP_DELETE%>"></td>
					<td style="width: 25%" align="right"><input type="submit"
						name="operation" value="<%=CourseListCtl.OP_NEXT%>"
						<%=(nextPageSize != 0) ? "" : "disabled"%>></td>

				</tr>
			</table>
			<%
				}
				if (list.size() == 0) {
			%>
			<table>
				<tr>
					<td align="right"><input type="submit" name="operation"
						value="<%=CourseListCtl.OP_BACK%>"></td>
				</tr>
			</table>

			<%
				}
			%>
		</form>
	</div>
	<%@include file="Footer.jsp"%>

</body>
</html>