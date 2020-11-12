package com.iiht.evaluation.eloan.dto;

public class Applyloan {

	   private String appname;
	   private String loanamount;
       private String appdate;
       private String indorg;
       private String salbus;
       private String taxindi;
       private String address;
       private String phone;
       private String email;
       private String appnum;
       private String username;
       
 
	/**
	 * @param appname
	 * @param loanamount
	 * @param appdate
	 * @param indorg
	 * @param salbus
	 * @param taxindi
	 * @param address
	 * @param phone
	 * @param email
	 * @param appnum
	 * @param username
	 */
	public Applyloan(String appname, String loanamount, String appdate, String indorg, String salbus, String taxindi,
			String address, String phone, String email, String appnum, String username) {
		super();
		this.appname = appname;
		this.loanamount = loanamount;
		this.appdate = appdate;
		this.indorg = indorg;
		this.salbus = salbus;
		this.taxindi = taxindi;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.appnum = appnum;
		this.username = username;
	}

	public Applyloan(String appname, String loanamount, String appdate, String indorg, String salbus, String taxindi,
			String address, String phone, String email, String username) {
		super();
		this.appname = appname;
		this.loanamount = loanamount;
		this.appdate = appdate;
		this.indorg = indorg;
		this.salbus = salbus;
		this.taxindi = taxindi;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.username = username;
	 
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAppnum() {
		return appnum;
	}

	public void setAppnum(String appnum) {
		this.appnum = appnum;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public String getLoanamount() {
		return loanamount;
	}

	public void setLoanamount(String loanamount) {
		this.loanamount = loanamount;
	}

	public String getAppdate() {
		return appdate;
	}

	public void setAppdate(String appdate) {
		this.appdate = appdate;
	}

	public String getIndorg() {
		return indorg;
	}

	public void setIndorg(String indorg) {
		this.indorg = indorg;
	}

	public String getSalbus() {
		return salbus;
	}

	public void setSalbus(String salbus) {
		this.salbus = salbus;
	}

	public String getTaxindi() {
		return taxindi;
	}

	public void setTaxindi(String taxindi) {
		this.taxindi = taxindi;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
              
       
       
}
