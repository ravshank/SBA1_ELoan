<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="lightblue">
	<!-- write the code to display the loan status information 
	     received from usercontrollers' displaystatus method
	-->
<% String status = request.getAttribute("updateflag").toString();

if(status.equals("true")){
	
String apnum = request.getAttribute("appnum").toString();
%>
<H1>Application Succesfully Submitted</H1>
<H2>Application number is :</H2><%=apnum %>
<a href="userhome1.jsp">Click here to go to Home Page</a>
<%
}
else
{
%>
<H1>Application UnSuccessful</H1>
<a href="application.jsp">Click here to go to Loan Applicaiton Page</a>
<% 
}

%>
<!--<jsp:include page="userhome.jsp"/>!-->
</body>
</html>