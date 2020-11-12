<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@page import="com.iiht.evaluation.eloan.model.LoanInfo"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display All Loans</title>
</head>
<body bgcolor="lightblue">
	<!-- write code to display all the loan details 
             which are received from the admin controllers' listall method
	--> 
	
	<% ArrayList<LoanInfo> list_loans = (ArrayList) request.getAttribute("list_loans");%>


		<table id="loanInfo">
			<thead>
				<tr>
					
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
					<th>User Name</th>
				</tr>



			</thead>
			<tbody>

				<c:forEach items="${list_loans}" var="loan">


					<tr>
						
						<td><c:out value="${loan.applno}" /></td>
						<td><c:out value="${loan.purpose}" /></td>
						<td><c:out value="${loan.amtrequest}" /></td>
						<td><c:out value="${loan.doa}" /></td>
						<td><c:out value="${loan.bstructure}" /></td>
						<td><c:out value="${loan.bindicator}" /></td>
						<td><c:out value="${loan.address}" /></td>
						<td><c:out value="${loan.email}" /></td>
						<td><c:out value="${loan.mobile}" /></td>
						<td><c:out value="${loan.status}" /></td>
						<td><c:out value="${loan.username}" /></td>
					</tr>

				</c:forEach>
			</tbody>
		</table>
		

	</form>
	
	</head>
</body>
<jsp:include page="footer.jsp" />

</html>

