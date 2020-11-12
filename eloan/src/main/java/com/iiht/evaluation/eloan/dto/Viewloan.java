package com.iiht.evaluation.eloan.dto;

public class Viewloan {

	private String apploan;
	private String appstatus;
	
	

	/**
	 * @param apploan
	 * @param appstatus
	 */
	public Viewloan(String apploan, String appstatus) {
		super();
		this.apploan = apploan;
		this.appstatus = appstatus;
	}
	public Viewloan(String appstatus) {
		super();
		
		this.appstatus = appstatus;
	}

	public String getAppstatus() {
		return appstatus;
	}

	public void setAppstatus(String appstatus) {
		this.appstatus = appstatus;
	}

	public String getApploan() {
		return apploan;
	}

	public void setApploan(String apploan) {
		this.apploan = apploan;
	}


	
	
}
