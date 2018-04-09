package com.hzyc.ccs.client.model;

/**
 * User
 * 
 * @author shaoshuai
 *
 */
public class UserExtened {
	
	private String uname;
	private String rname;
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getDpname() {
		return dpname;
	}
	public void setDpname(String dpname) {
		this.dpname = dpname;
	}
	public String getTrue_name() {
		return true_name;
	}
	public void setTrue_name(String truename) {
		this.true_name = truename;
	}
	private String dpname;
	private String true_name;
}
