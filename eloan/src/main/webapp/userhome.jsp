<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.iiht.evaluation.eloan.dto.UserInfo"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>user home</title>
</head>
<body bgcolor="lightblue">
<jsp:include page="header.jsp"/>
<%
	UserInfo userDetails = (UserInfo) session.getAttribute("userInfo");

%>

<h1>Hi <%=userDetails.getFirstName()%></h1>

<h4><marquee>User Services</marquee></h4>
<div align="right"><a href="index.jsp">Logout</a></div>
<a href="user?action=application">Apply for Loan</a><br>
<a href="trackloan.jsp">Track Loan </a><br>
<a href="editloan.jsp">Edit Loan </a>
<jsp:include page="footer.jsp"/>
</body>
</html>