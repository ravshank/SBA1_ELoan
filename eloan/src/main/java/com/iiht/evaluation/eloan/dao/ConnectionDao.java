package com.iiht.evaluation.eloan.dao;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;

import com.iiht.evaluation.eloan.dto.Applyloan;
import com.iiht.evaluation.eloan.dto.Displaystatus;
import com.iiht.evaluation.eloan.dto.LoanDto;
import com.iiht.evaluation.eloan.model.ApprovedLoan;
import com.iiht.evaluation.eloan.model.LoanInfo;
import com.iiht.evaluation.eloan.model.User;

import com.iiht.evaluation.eloan.dto.UserInfo;
import com.iiht.evaluation.eloan.dto.Viewloan;




public class ConnectionDao {
	private static final long serialVersionUID = 1L;
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public ConnectionDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }
	
	/*======================= SQL queries used in DAO file ====================*/
	static final String FETCH_USER_CRED = "select user_name, user_password from mortgage.user;";
	static final String FETCH_USER_INFO = "select * from mortgage.userinfo where user_name='%s'";
	static final String FETCH_LOAN_NUM = "select APPLICATION_NUMBER from mortgage.apply_loan where FULL_NAME='%s'";
	static final String FETCH_LOAN_STATUS = "select STATUS from mortgage.apply_loan where APPLICATION_NUMBER=%s";
	static final String INS_USRINFO="INSERT INTO mortgage.UserInfo(USER_NAME,USER_FIRSTNAME,USER_LASTNAME,MOBILE,EMAIL,CITY,STATE,COUNTRY,ZIPCODE,CUSTOMER_ADDRESS,DOB) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	static final String INS_USRCRD = "INSERT INTO mortgage.User(USER_NAME,USER_PASSWORD) VALUES(?,?)";
	static final String FETCH_LOAN_ALLINFO_BY_ID = "select * from mortgage.apply_loan where APPLICATION_NUMBER=%s";
	static final String FETCH_LOAN_INFO_BY_ID = "select status from mortgage.apply_loan where APPLICATION_NUMBER='%s' and user_name='%s'";
	static final String FETCH_LOAN = "select * from mortgage.apply_loan";
	/*=========================================================================*/
	public  Connection connect() throws SQLException {
		//if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		
		return jdbcConnection;
	}

	public void disconnect() throws SQLException {
		//if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		
	}
	
	// put the relevant DAO methods here..
	public boolean addNewLoanApplicant(Applyloan applyloan)
	{
		Connection conn = null;
		PreparedStatement pst = null;
		String query = "Insert into mortgage.apply_loan (FULL_NAME, LOAN_AMOUNT, APPLICATION_DATE, APPLICANT_TYPE, SAL_BUSINESS, TAX_INDICATOR, ADDRESS, PHONE, EMAIL, STATUS, user_name) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try {
        	jdbcConnection = this.connect();
            pst = jdbcConnection.prepareStatement(query);    	
            
            pst.setString(1, applyloan.getAppname());
            pst.setString(2, applyloan.getLoanamount());
            pst.setString(3, applyloan.getAppdate());
            pst.setString(4, applyloan.getIndorg());
            pst.setString(5, applyloan.getSalbus());
            pst.setString(6, applyloan.getTaxindi());
            pst.setString(7, applyloan.getAddress());
            pst.setString(8, applyloan.getPhone());
            pst.setString(9, applyloan.getEmail());
            pst.setString(10, "Submitted");
            pst.setString(11, applyloan.getUsername());
            
            int recupdated = pst.executeUpdate();
			
			 System.out.println("num of rec updated "+recupdated);
				/* Log.info("Record updated succesfully"); */
			
                        
            System.out.println("Successfully Inserted"); 
            return recupdated == 1; 
	}
        catch(Exception e) {
			/*
			 * Log.error("Unable to Fetch Apply Loan info");
			 * Log.debug("Error :"+e.getMessage());
			 */        	return false; 
        }
        finally {
        	try 
        	{
        		pst.close();
        	    jdbcConnection.close();
        	}
        	catch(Exception e) 
        	{
        		//Log.debug("error : "+e.getMessage());
        	}
        		}
        	}
	
	public String getLoanNumber(String userName) 
	{
		Connection conn = null;
		Statement stmt = null;
		String appnum = null;
			
		
        try {
        	jdbcConnection = this.connect();
        	stmt = jdbcConnection.createStatement();
        	ResultSet rs = stmt.executeQuery(String.format(FETCH_LOAN_NUM, userName));        	
        	while(rs.next())
			{
				System.out.println(rs.getString("APPLICATION_NUMBER"));
				
				appnum = rs.getString("APPLICATION_NUMBER");
				System.out.println("appnum is :" + appnum);
			}
			return appnum;
	    }
        catch(Exception e) {
        	System.out.println("Unable to fetch Loan number");
        	System.out.println("Error : "+e.getMessage());
			return appnum;
        }
        finally 
		{
			try 
			{
				stmt.close();
				this.disconnect();
			} 
			catch (SQLException e) 
			{
				System.out.println("Error : "+ e.getMessage());
			}
		}
}

	public String getLoanStatus(String apploan) {
		// TODO Auto-generated method stub
		
		Statement stmt = null;
		
		String appstatus = null;
		
		
		 try {
	        	jdbcConnection = this.connect();
	        	stmt = jdbcConnection.createStatement();
	        	ResultSet rs = stmt.executeQuery(String.format(FETCH_LOAN_STATUS, apploan));        	
	        	while(rs.next())
	        	{
					System.out.println(rs.getString("STATUS"));
					
					appstatus = rs.getString("STATUS");
					System.out.println("Loan "+apploan+" is :" + appstatus);
					}
				return appstatus ;
		 }
	        catch(Exception e) {
	        	System.out.println("Unable to fetch Loan status");
	        	System.out.println("Error : "+e.getMessage());
				return appstatus;
	        }
	        finally 
			{
				try 
				{
					stmt.close();
					this.disconnect();
				} 
				catch (SQLException e) 
				{
					System.out.println("Error : "+ e.getMessage());
				}
			}
	}
		


public HashMap<String, String> getUserCredData()
{
	Statement stmt = null;
	HashMap<String, String> cred_map = new HashMap<String, String>();
	
	try
	{
		jdbcConnection = this.connect();
		stmt = jdbcConnection.createStatement();
		ResultSet rs = stmt.executeQuery(FETCH_USER_CRED);

		while(rs.next())
		{
			cred_map.put(rs.getString("user_name"), rs.getString("user_password"));
		}
		return cred_map;
	} 
	catch(Exception e)
	{
		System.out.println("Unable to fetch User credential data");
		System.out.println("Error : "+e.getMessage());
		return cred_map;
	}
	finally 
	{
		try 
		{
			stmt.close();
			this.disconnect();
		} 
		catch (SQLException e) 
		{
			System.out.println("Error : "+ e.getMessage());
		}
	}
}


public UserInfo getUserInfo(String userName) 
{
		Statement stmt = null;
		UserInfo userInfo = null;

		try
		{
			jdbcConnection = this.connect();
			stmt = jdbcConnection.createStatement();
			ResultSet rs = stmt.executeQuery(String.format(FETCH_USER_INFO,userName));
			while(rs.next())
			{
				System.out.println(rs.getString("USER_FIRSTNAME")+"-"+rs.getString("USER_LASTNAME"));
				
				userInfo = new UserInfo(rs.getString("USER_NAME"), rs.getString("USER_FIRSTNAME"), 
						rs.getString("USER_LASTNAME"), rs.getString("DOB"), rs.getString("MOBILE"), rs.getString("EMAIL"), 
						rs.getString("CITY"), rs.getString("STATE"), rs.getString("COUNTRY"), 
						rs.getString("ZIPCODE"), rs.getString("CUSTOMER_ADDRESS"));
				
			}
			
			return userInfo;
		} 
		catch(Exception e)
		{
			System.out.println("Unable to fetch User Info data");
			System.out.println("Error : "+e.getMessage());
			return userInfo;
		}
		finally 
		{
			try 
			{
				stmt.close();
				this.disconnect();
			} 
			catch (SQLException e) 
			{
				System.out.println("Error : "+ e.getMessage());
			}
		}
}


public boolean registerNewUserInfo(UserInfo userInfo) {
	
	PreparedStatement pst = null;
	boolean successFlag = false;

	try {
			jdbcConnection = this.connect();
			pst = jdbcConnection.prepareStatement(INS_USRINFO);
			pst.setString(1, userInfo.getUsername());
			pst.setString(2, userInfo.getFirstName());
			pst.setString(3, userInfo.getLastName());
			pst.setString(4, userInfo.getMobile());
			pst.setString(5, userInfo.getEmail());
			pst.setString(6, userInfo.getCity());
			pst.setString(7, userInfo.getState());
			pst.setString(8, userInfo.getCountry());
			pst.setString(9, userInfo.getPincode());
			pst.setString(10, userInfo.getAddress());
			pst.setString(11, userInfo.getDob());
			int updatedRows = pst.executeUpdate();
			
			successFlag = updatedRows==1;
			
		}catch(SQLException exp) {
			System.out.println(exp.getMessage());
		}
		finally {
			try {
				pst.close();
				this.disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	return successFlag;
}

public boolean registerNewUser(User user) {
	
	PreparedStatement pst = null;
	boolean successFlag = false;
	
	try {
			jdbcConnection = this.connect();
			pst = jdbcConnection.prepareStatement(INS_USRCRD);

			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			
			int updatedRows = pst.executeUpdate();
			
			successFlag = updatedRows==1;
			
		}catch(SQLException exp) {
			System.out.println(exp.getMessage());
		}
		finally {
			try {
				pst.close();
				this.disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	return successFlag;
}

public LoanInfo getLoanInfoByAppNumber(String applNo) {
	
	
	Statement stmt = null;
	LoanInfo loanInfo = null;
System.out.println("fff");
	try
	{
		jdbcConnection = this.connect();
		stmt = jdbcConnection.createStatement();
		ResultSet rs = stmt.executeQuery(String.format(FETCH_LOAN_ALLINFO_BY_ID,applNo));
		while(rs.next())
		{
			

			loanInfo = new LoanInfo(rs.getString("APPLICATION_NUMBER"), rs.getString("FULL_NAME"), rs.getString("LOAN_AMOUNT"), 
					rs.getString("APPLICATION_DATE"), rs.getString("APPLICANT_TYPE"), rs.getString("SAL_BUSINESS"), rs.getString("TAX_INDICATOR"), 
					rs.getString("ADDRESS"), rs.getString("EMAIL"), rs.getString("PHONE"), rs.getString("STATUS"), rs.getString("user_name"));
		}
		return loanInfo;
	} 
	catch(Exception e)
	{
		System.out.println("Unable to fetch Loan Info data");
		System.out.println("Error : "+e.getMessage());
		return loanInfo;
	}
	finally 
	{
		try 
		{
			stmt.close();
			this.disconnect();
		} 
		catch (SQLException e) 
		{
			System.out.println("Error : "+ e.getMessage());
		}
	}
	
	
	
}


public String getLoanById(String applno, String userName) {
	
	Statement stmt = null;
	String status = "";
	try
	{
		jdbcConnection = this.connect();
		stmt = jdbcConnection.createStatement();
		ResultSet rs = stmt.executeQuery(String.format(FETCH_LOAN_INFO_BY_ID,applno, userName));
		while(rs.next())
		{
			status = rs.getString("status");
		}
		return status;
	} 
	catch(Exception e)
	{
		System.out.println("Unable to fetch Status data");
		System.out.println("Error : "+e.getMessage());
		return status;
	}
	finally 
	{
		try 
		{
			stmt.close();
			this.disconnect();
		} 
		catch (SQLException e) 
		{
			System.out.println("Error : "+ e.getMessage());
		}
	}
	
	
	
}

public ArrayList<LoanInfo> getLoanInfo() {
	
	
	Statement stmt = null;
	LoanInfo loanInfo = null;
	ArrayList<LoanInfo> list_loans =new ArrayList<LoanInfo>();

	try
	{
		jdbcConnection = this.connect();
		stmt = jdbcConnection.createStatement();
		ResultSet rs = stmt.executeQuery(String.format(FETCH_LOAN));
		while(rs.next())
		{
			loanInfo = new LoanInfo(rs.getString("APPLICATION_NUMBER"), rs.getString("FULL_NAME"), rs.getString("LOAN_AMOUNT"), 
					rs.getString("APPLICATION_DATE"), rs.getString("APPLICANT_TYPE"), rs.getString("SAL_BUSINESS"), rs.getString("TAX_INDICATOR"), 
					rs.getString("ADDRESS"), rs.getString("EMAIL"), rs.getString("PHONE"), rs.getString("STATUS"), rs.getString("user_name"));
			list_loans.add(loanInfo);
		}
		
		return list_loans;
	} 
	catch(Exception e)
	{
		System.out.println("Unable to fetch Loan Info data");
		System.out.println("Error : "+e.getMessage());
		return list_loans;
	}
	finally 
	{
		try 
		{
			stmt.close();
			this.disconnect();
		} 
		catch (SQLException e) 
		{
			System.out.println("Error : "+ e.getMessage());
		}
	}
}
}
