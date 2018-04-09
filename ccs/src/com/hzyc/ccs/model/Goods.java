package com.hzyc.ccs.model;

public class Goods {
    private String goodCode;

    private String goodName;

    private String goodPrice;

    private String goodSize;

    private String goodBkind;

    private String goodSkind;

    private String goodBz;

    private Integer goodState;
    private String number;
    private String totalPrice;
    
    

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	//每页显示几行
      private int perPageLine;
      //从第几行开始显示
      private int startLine;
    public int getPerPageLine() {
		return perPageLine;
	}

	public void setPerPageLine(int perPageLine) {
		this.perPageLine = perPageLine;
	}

	public int getStartLine() {
		return startLine;
	}

	public void setStartLine(int startLine) {
		this.startLine = startLine;
	}

	public String getGoodCode() {
        return goodCode;
    }

    public void setGoodCode(String goodCode) {
        this.goodCode = goodCode == null ? null : goodCode.trim();
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName == null ? null : goodName.trim();
    }

    public String getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(String goodPrice) {
        this.goodPrice = goodPrice == null ? null : goodPrice.trim();
    }

    public String getGoodSize() {
        return goodSize;
    }

    public void setGoodSize(String goodSize) {
        this.goodSize = goodSize == null ? null : goodSize.trim();
    }

    public String getGoodBkind() {
        return goodBkind;
    }

    public void setGoodBkind(String goodBkind) {
        this.goodBkind = goodBkind == null ? null : goodBkind.trim();
    }

    public String getGoodSkind() {
        return goodSkind;
    }

    public void setGoodSkind(String goodSkind) {
        this.goodSkind = goodSkind == null ? null : goodSkind.trim();
    }

    public String getGoodBz() {
        return goodBz;
    }

    public void setGoodBz(String goodBz) {
        this.goodBz = goodBz == null ? null : goodBz.trim();
    }

    public Integer getGoodState() {
        return goodState;
    }

    public void setGoodState(Integer goodState) {
        this.goodState = goodState;
    }
}