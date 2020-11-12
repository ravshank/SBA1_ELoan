<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Loan Status</title>
</head>
<body bgcolor="lighblue">

<jsp:include page="header.jsp"/>

<%if(request.getAttribute("appstatus")==null)
		{		
		%>
			<h1>Enter valid Application Id</h1>
			<a href="trackloan.jsp"><input type="button" name="navigateback" value="Navigate Back"></a>
			
		<% }
		else
		{ %>
			<h1>Your application Loan Status is : <%=request.getAttribute("appstatus") %></h1>
			<a href="trackloan.jsp"><input type="button" name="navigateback" value="Navigate Back"></a>
			<a href="userhome1.jsp"><input type="button" name="home" value="Home"></a>
			
	<% } %> 
	

<jsp:include page="footer.jsp"/>

</body>
</html>