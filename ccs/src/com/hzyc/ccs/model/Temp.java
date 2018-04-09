package com.hzyc.ccs.model;

/**
 * @author Administrator
 *这个model使用来封装会员的总消费，会员的数量，会员的总充值金额，会员的总余额
 */
public class Temp {
	private String totalExpense = "0";
	private String totalCunt = "0";
	private String totaladd = "0";
	private String totalRemain = "0";
	public String getTotalExpense() {
		return totalExpense;
	}
	public void setTotalExpense(String totalExpense) {
		this.totalExpense = totalExpense;
	}
	public String getTotalCunt() {
		return totalCunt;
	}
	public void setTotalCunt(String totalCunt) {
		this.totalCunt = totalCunt;
	}
	public String getTotaladd() {
		return totaladd;
	}
	public void setTotaladd(String totaladd) {
		this.totaladd = totaladd;
	}
	public String getTotalRemain() {
		return totalRemain;
	}
	public void setTotalRemain(String totalRemain) {
		this.totalRemain = totalRemain;
	}
	
}
