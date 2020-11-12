<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.iiht.evaluation.eloan.model.LoanInfo"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Loan Application</title>
<style>
#loanInfo{
font-family:CALIBRI;
border-collapse:collapse;
width:100%;

}
#loanInfo td,tr,th{
border : 1px solid;
font-family:CALIBRI;
}
#loanInfo th{
background:#4bc323;
color:White;

}
</head>
</style>
</head>
<body>
	<!-- read the application number to edit from user and send to 
	     user controller to edit info
	-->
	<%
		if(request.getAttribute("editStatus")==null) {
	%>
<form action="user?action=editloan" method="post">
<label>Enter the Loan Application Number</label><input type="text" name="applicationnumber" required placeholder="Enter your application number"><BR/><BR/>
<input type="submit" name="editapplication" value="Edit ">
<%}
		else{
			LoanInfo loaninfo = (LoanInfo) request.getAttribute("updtLoanInfo");
			
			if(request.getAttribute("editStatus").equals("done")) {
				
				%>
				<%} else { %>
				<h2>Loan edit unsuccessful !!!</h2>
				<% 	}
				request.removeAttribute("editStatus");	
				%>
						
				<h2>Loan edit successful !!!</h2>
                <table id="loanInfo">
		<thead>
			<tr>
				<th>User Name</th>
				<th>Application Number</th>
				<th>Purpose</th>
				<th>Amount Request</th>
				<th>Loan Date</th>
				<th>Business Structure</th>
				<th>Business Indicator</th>
				<th>Address</th>
				<th>Email</th>
				<th>Mobile</th>
				<th>Status</th>
			</tr>
		</thead>
		
		<tbody>
			<tr>
				<td><%=loaninfo.getUsername() %></td>
				<td><%=loaninfo.getApplno() %></td>
				<td><%=loaninfo.getPurpose() %></td>
				<td><%=loaninfo.getAmtrequest() %></td>
				<td><%=loaninfo.getDoa() %></td>
				<td><%=loaninfo.getBstructure() %></td>
				<td><%=loaninfo.getBindicator() %></td>
				<td><%=loaninfo.getAddress() %></td>
				<td><%=loaninfo.getEmail() %></td>
				<td><%=loaninfo.getMobile() %></td>
				<td><%=loaninfo.getStatus() %></td>
			</tr>
		</tbody>
	</table>
	
	
	<%} %>
		
		<a href="userhome1.jsp"><input type="button" name="home" value="Home"></a>
	
</form>
</body>
</html>