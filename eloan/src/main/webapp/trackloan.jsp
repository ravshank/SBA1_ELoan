 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Track Loan</title>
</head>
<body bgcolor="lightblue">
	<!-- write html code to read the application number and send to usercontrollers'
             displaystatus method for displaying the information
	-->


 
<form action="user?action=displaystatus" method="post">
<label>Enter the Loan Application Number</label><input type="text" name="lappnum"><BR/><BR/>
<input type="submit" name="Submit" value="Click here to view the status">


</form> 
<a href="userhome.jsp"><input type="button" name="returnhome" value="Home"></a>


</body>
</html>