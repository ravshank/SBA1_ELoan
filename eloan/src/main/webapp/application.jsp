<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Loan Application Form</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
</head>
<body onload="myFunction()" bgcolor="lightblue">
<!--
	write the html code to accept laon info from user and send to placeloan servlet
-->
<form action="user?action=placeloan" method="post">
<% session.setAttribute("username", session.getAttribute("username").toString()); %>
<centre>
<H1>Mortgage Loan Application</H1>
</centre>
<label>Applicant Name</label><input type="text" name="appname"></input><BR/>
<BR/>
<label>Loan Amount</label><input type="text" name="loanamount"></input><BR/><BR/>
<label>Loan Application Date</label><input type="date" name="appdate" value="yyyy-mm-dd"><BR/><BR/>
<label>Individual/Organization</label><select name="indorg">
									  <option value="Individual">Individual</option>
									  <option value="Organization">Organization</option>
									   </select><BR/><BR/>
<label>Salaried/Business</label><select name="salbus">
									  <option value="Salaried">Salaried</option>
									  <option value="Business">Business</option>
									   </select><BR/><BR/>
<label>Tax Indicator</label><input type="radio" name="taxindi" value="T">Tax Payer</input>
                            <input type="radio" name="taxindi" value="NT">Non Tax Payer</input> <BR/><br/>

<label>Property Address</label><textarea name="address" id="address" rows="4" cols="50">Enter address here</textarea><br><BR>
<label>Phone Number</label><input type="number" name="phone"></input><br/><br/>
<label>Enter Email</label><input type="email" name="email" id="email"><br/><br/>
<input type="submit" name="Submit" value="Submit Application">
</form>
</script>
</body>
</html>