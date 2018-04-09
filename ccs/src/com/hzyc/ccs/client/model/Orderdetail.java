package com.hzyc.ccs.client.model;

public class Orderdetail {
    private Integer id;

    private String ddCode;

    private String goodCode;

    private String number;
    
    private String yingShouMoney;

    public String getYingShouMoney() {
		return yingShouMoney;
	}

	public void setYingShouMoney(String yingShouMoney) {
		this.yingShouMoney = yingShouMoney;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDdCode() {
        return ddCode;
    }

    public void setDdCode(String ddCode) {
        this.ddCode = ddCode == null ? null : ddCode.trim();
    }

    public String getGoodCode() {
        return goodCode;
    }

    public void setGoodCode(String goodCode) {
        this.goodCode = goodCode == null ? null : goodCode.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }
}