<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Collections"%>
<%@page import="in.co.rays.ORSProject4.ctl.UserCtl"%>
<%@page import="in.co.rays.ORSProject4.bean.TimetableBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.ORSProject4.util.HTMLUtility"%>
<%@page import="in.co.rays.ORSProject4.ctl.TimetableCtl"%>
<%@page import="in.co.rays.ORSProject4.util.DataUtility"%>
<%@page import="in.co.rays.ORSProject4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.List"%>   

<html>
<head>
<title>Add Timetable</title>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
	$(function() {
		$("#datepicker").datepicker({
			dateFormat : 'mm/dd/yy',
			//defaultDate : "06/06/2001",
			changeMonth : true,
			changeYear : true,
			//yearRange: "c-20:c+0",
			yearRange : "2021:2023",
		//maxDate:'0',
		// minDate:0
		//yearRange : "-40:-18"
		});
	});
</script>
</head>
<body ng-app="720" ng-controller="TestController as ctrl">
	<form action="<%=ORSView.TIMETABLE_CTL%>" method="POST">
		<%@ include file="Header.jsp"%>

		<jsp:useBean id="bean" class="in.co.rays.ORSProject4.bean.TimetableBean"
			scope="request"></jsp:useBean>

		<%
			@SuppressWarnings("unchecked")
			List<TimetableBean> courseList = (List<TimetableBean>) request.getAttribute("courseList");
			@SuppressWarnings("unchecked")
			List<TimetableBean> subjectList = (List<TimetableBean>) request.getAttribute("subjectList");
		%>

		<div align="center">
			<h1 align="center" style="margin-bottom: -15; color: navy">
				<%
					if (bean != null && bean.getId() > 0) {
				%>Update<%
					} else {
				%>Add<%
					}
				%>
				Timetable
			</h1>

			<div style="height: 15px; margin-bottom: 12px">
				<H3 align="center">
					<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
					</font>
				</H3>
				<H3 align="center">
					<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
					</font>
				</H3>
			</div>
			<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

			<table>
				<tr>
					<th align="left">Course<span style="color: red">*</span></th>
					<td><%=HTMLUtility.getList("courseId", String.valueOf(bean.getCourseId()), courseList)%></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("courseId", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Subject<span style="color: red">*</span></th>
					<td><%=HTMLUtility.getList("subjectId", String.valueOf(bean.getSubjectId()), subjectList)%></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("subjectId", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Semester<span style="color: red">*</span></th>
					<td>
						<%
							HashMap<String, String> map = new HashMap<String, String>();
							map.put("1", "1");
							map.put("2", "2");
							map.put("3", "3");
							map.put("4", "4");
							map.put("5", "5");
							map.put("6", "6");
							map.put("7", "7");
							map.put("8", "8");
							map.put("9", "9");
							map.put("10", "10");

							String htmlList = HTMLUtility.getList("semester", bean.getSemester(), map);
						%> <%=htmlList%>
					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("semester", request)%></font></td>
				</tr>
				<%-- <tr>
					<th align="left">Exam Date<span style="color: red">*</span></th>
					<td>
						<div class="input-group">
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
							<div data-datepicker date-format="MM/dd/yyyy" date-typer="true"
								date-max-limit="{{maxDateTime}}"
								date-min-limit="{{minDateTime1}}">
								<input type="text" name="examDate"
									placeholder="Select Date of Exam" readonly="readonly"
									value="<%=DataUtility.getDateString(bean.getExamDate())%>">
							</div>
						</div>
					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("examDate", request)%></font></td>
				</tr> --%>
				<tr>
					<th align="left">Exam Date<font color="red">*</font></th>
					<td><input type="text" name="examDate" id="datepicker"
						placeholder="Enter Date Of Birth" readonly="readonly"
						value="<%=DataUtility.getDateString(bean.getExamDate())%>">
						&nbsp;<font style="position: fixed;" color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Exam Time<span style="color: red">*</span></th>
					<td>
						<%
							LinkedHashMap<String, String> map1 = new LinkedHashMap<String, String>();
							map1.put("08:00 AM to 11:00 AM", "08:00 AM to 11:00 AM");
							map1.put("12:00 PM to 03:00 PM", "12:00 PM to 03:00 PM");
							map1.put("04:00 PM to 07:00 PM", "04:00 PM to 07:00 PM");

							String htmlList1 = HTMLUtility.getList("examTime", bean.getExamTime(), map1);
						%> <%=htmlList1%>
					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("examTime", request)%></font></td>
				</tr>

				<tr>
					<th align="left">Description<span style="color: red">*</span></th>
					<td align="left"><textarea style="width: 177px; resize: none;"
							name="description" rows="3" placeholder="Enter Short description"><%
								if (bean != null && bean.getId() > 0) {
							%><%=DataUtility.getStringData(bean.getDescription())%><%
								}
							%></textarea></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("description", request)%></font></td>
				</tr>
				<tr>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th></th>
					<%
						if (bean != null && bean.getId() > 0) {
					%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=TimetableCtl.OP_UPDATE%>"> <input
						type="submit" name="operation" value="<%=TimetableCtl.OP_CANCEL%>">
						<%
							} else {
						%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=TimetableCtl.OP_SAVE%>"> <input
						type="submit" name="operation" value="<%=TimetableCtl.OP_RESET%>">
						<%
							}
						%>
				</tr>
			</table>
		</div>
	</form>

	<%@ include file="Footer.jsp"%>
</body>
</html>