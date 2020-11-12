package com.iiht.evaluation.eloan.model;

public class LoanInfo {
	private String applno;
	 private String purpose;
	 private String amtrequest;
	 private String doa;
	 private String bstructure;
	 private String bindicator;
	 private String address;
	 @Override
	public String toString() {
		return "LoanInfo [applno=" + applno + ", amtrequest=" + amtrequest + ", doa=" + doa
				+ ", bstructure=" + bstructure + ", bindicator=" + bindicator + ", address=" + address + ", email="
				+ email + ", mobile=" + mobile + ", status=" + status + "]";
	}
	private String email;
	 private String mobile;
	 private String status;
	 private String username;
	 private String salbus;
	 public String getSalbus() {
		return salbus;
	}
	public void setSalbus(String salbus) {
		this.salbus = salbus;
	}
	public LoanInfo(String applno, String purpose, String amtrequest, String doa, String bstructure, String salbus, String bindicator,
			String address, String email, String mobile,String status, String username) {
		super();
		this.applno = applno;
		this.purpose = purpose;
		this.amtrequest = amtrequest;
		this.doa = doa;
		this.bstructure = bstructure;
		this.bindicator = bindicator;
		this.address = address;
		this.email = email;
		this.mobile = mobile;
		this.status=status;
		this.username = username;
		this.salbus = salbus;
	}

	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getApplno() {
		return applno;
	}
	public void setApplno(String applno) {
		this.applno = applno;
	}

	/*
	 * public String getPurpose() { return purpose; } public void setPurpose(String
	 * purpose) { this.purpose = purpose; }
	 */
	public String getAmtrequest() {
		return amtrequest;
	}
	public void setAmtrequest(String amtrequest) {
		this.amtrequest = amtrequest;
	}
	public String getDoa() {
		return doa;
	}
	public void setDoa(String doa) {
		this.doa = doa;
	}
	public String getBstructure() {
		return bstructure;
	}
	public void setBstructure(String bstructure) {
		this.bstructure = bstructure;
	}
	public String getBindicator() {
		return bindicator;
	}
	public void setBindicator(String bindicator) {
		this.bindicator = bindicator;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setStatus(String status) {
		this.status=status;
	}
	public String getStatus() {
		return status;
	}

}