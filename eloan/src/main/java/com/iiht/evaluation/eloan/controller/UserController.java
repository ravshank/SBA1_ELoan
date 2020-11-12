package com.iiht.evaluation.eloan.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iiht.evaluation.eloan.dao.ConnectionDao;
import com.iiht.evaluation.eloan.dto.Applyloan;
import com.iiht.evaluation.eloan.dto.Displaystatus;
import com.iiht.evaluation.eloan.model.ApprovedLoan;
import com.iiht.evaluation.eloan.model.LoanInfo;
import com.iiht.evaluation.eloan.model.User;
import com.mysql.cj.xdevapi.Statement;

import com.iiht.evaluation.eloan.dto.UserInfo;
import com.iiht.evaluation.eloan.dto.Viewloan;




@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
private ConnectionDao connDao;
	
	public void setConnDao(ConnectionDao connDao) {
		this.connDao = connDao;
	}
	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config.getServletContext().getInitParameter("jdbcPassword");
		System.out.println(jdbcURL + jdbcUsername + jdbcPassword);
		this.connDao = new ConnectionDao(jdbcURL, jdbcUsername, jdbcPassword);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		
		String viewName = "";
		try {
			switch (action) {
			case "registernewuser":
				viewName=registernewuser(request,response);
				break;
			case "validate":
				viewName=validate(request,response);
				break;
			case "placeloan":
				viewName=placeloan(request,response);
				break;
			case "application1":
				viewName=application1(request,response);
				break;
			case "editLoanProcess"  :
				viewName=editLoanProcess(request,response);
				break;
			case "registeruser":
				viewName=registerUser(request,response);
				break;
			case "register":
				viewName = register(request, response);
				break;
			case "application":
				viewName = application(request, response);
				break;
			case "trackloan":
				viewName = trackloan(request, response);
				break;
			case "editloan":
				viewName = editloan(request, response);
				break;	
			case  "displaystatus" :
				viewName=displaystatus(request,response);
				break;
			default : viewName = "notfound.jsp"; break;	
			}
		} catch (Exception ex) {
			
			throw new ServletException(ex.getMessage());
		}
			RequestDispatcher dispatch = 
					request.getRequestDispatcher(viewName);
			dispatch.forward(request, response);
	}
	private String validate(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		/* write the code to validate the user */
		HttpSession session = request.getSession();

		if(request.getParameter("type").toString().equalsIgnoreCase("customer"))
		{
			String userID = request.getParameter("loginID");
			String password = request.getParameter("password");
			
			HashMap<String, String> cred_map = connDao.getUserCredData();
			UserInfo userInfo = connDao.getUserInfo(userID);
			
			if(cred_map.keySet().contains(userID) && cred_map.get(userID).equals(password)) 
			{
				session.setAttribute("username", userID);
				session.setAttribute("userInfo", userInfo);

				return "userhome1.jsp";
			}
			else 
			{
				return "errorPage.jsp"; 
			}
		} 
		else 
		{
			String adminID = request.getParameter("adminID");
			String password = request.getParameter("password");
			
			if(adminID.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin"))
			{
				return "adminhome1.jsp";
			}
			else 
			{
				return "errorPage.jsp"; 
			}
		}
	}
		
	private String placeloan(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
	/* write the code to place the loan information */
		HttpSession session = request.getSession();
		
		String appname = request.getParameter("appname");
		String loanamount = request.getParameter("loanamount");
		String appdate = request.getParameter("appdate");
		String indorg = request.getParameter("indorg");
		String salbus = request.getParameter("salbus");
		String taxindi = request.getParameter("taxindi");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String username = session.getAttribute("username").toString();
		
		System.out.println(username);
		Applyloan applyloan = new Applyloan(appname, loanamount, appdate, indorg, salbus, taxindi, address, phone, email, username);
		
		boolean updateflag = connDao.addNewLoanApplicant(applyloan);
		if(updateflag) {
		String appnum = connDao.getLoanNumber(appname);
		request.setAttribute("appnum", appnum);
		}
		request.setAttribute("updateflag", Boolean.toString(updateflag));
		
		return "loanDetails.jsp";
		//String view = "application.jsp";
		//return view;
	}
	private String application1(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	/* write the code to display the loan application page */
		
		return null;
	}
	private String editLoanProcess(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code to edit the loan info */
		
		return null;
	}
	private String registerUser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code to redirect page to read the user details */
		request.setAttribute("newUser", "newUser");
		return "newuserui.jsp";
	
	}
	private String registernewuser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code to create the new user account read from user 
		   and return to index page */
		UserInfo newUserInfo = new UserInfo();
		newUserInfo.setUsername(request.getParameter("username"));
		newUserInfo.setFirstName(request.getParameter("firstname"));
		newUserInfo.setLastName(request.getParameter("lastname"));
		newUserInfo.setDob(request.getParameter("dob"));
		newUserInfo.setMobile(request.getParameter("mobile"));
		newUserInfo.setEmail(request.getParameter("email"));
		newUserInfo.setCity(request.getParameter("city"));
		newUserInfo.setState(request.getParameter("state"));
		newUserInfo.setCountry(request.getParameter("country"));
		newUserInfo.setPincode(request.getParameter("pin"));
		newUserInfo.setAddress(request.getParameter("address"));
		
		User newUser = new User();
		newUser.setUsername(request.getParameter("username"));
		newUser.setPassword(request.getParameter("password"));
		
		
		boolean userInfoFlag = connDao.registerNewUserInfo(newUserInfo);
		boolean userFlag = connDao.registerNewUser(newUser);
		
		request.removeAttribute("newUser");
		
		if(userFlag && userInfoFlag)
		{
			request.setAttribute("newUserName", request.getParameter("username"));
			request.setAttribute("newPassword", request.getParameter("password"));
			request.setAttribute("userCreation", "success");
		}
		else
		{
			request.setAttribute("userCreation", "failed");
		}
	
		return "newuserui.jsp";
	}
	
	private String register(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		/* write the code to redirect to register page */
		
		return null;
	}
	private String displaystatus(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code the display the loan status based on the given application
		   number 
		*/
		//HttpSession session = request.getSession();
		String apploan = request.getParameter("lappnum");
		//int apploan1 = Integer.parseInt(apploan);
			System.out.println(apploan);	
	    String appstatus = connDao.getLoanStatus(apploan);
		
		request.setAttribute("appstatus", appstatus);
		
		return "displayloanstatus.jsp";
	}

	private String editloan(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	/* write a code to return to editloan page */
		System.out.println("Edit Loan");
        HttpSession session= request.getSession();
        
        LoanInfo loanData = connDao.getLoanInfoByAppNumber(request.getParameter("applicationnumber"));
		request.setAttribute("loanData", loanData);
		
		String status = connDao.getLoanById(request.getParameter("applicationnumber"), session.getAttribute("username").toString());
		request.setAttribute("status", status);

		
		session.setAttribute("applicationNum", request.getParameter("applicationnumber"));
		
		String view = "editloanui.jsp";
		
		return view;
	}
		
	

	private String trackloan(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	/* write a code to return to trackloan page */
		
		return null;
	}

	private String application(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	/* write a code to return to trackloan page */
		HttpSession session = request.getSession();
		session.setAttribute("username", session.getAttribute("username").toString());
		return "application.jsp";
	}
}