package com.hzyc.ccs.model;

public class Vip {
    private String hyCode;

    private String hyKindCode;

    private String hyTel;

    private String hyName;

    private String hyCard;

    private String hyPhoto;

    private String hyTotal;

    private String hyRemainMoney;

    private String hyBirthday;

    private String hyCunt;

    private String hyBz;

    private String hyJf;

    private String hyDate;

    private String hyExpense;
    
    private String hyCuntL;
    
    private String hyCuntR;
    
    private String hyDateL;
    
    private String hyDateR;
    
    private String hyExpenseL;
    
    private String hyExpenseR;
    private String hyAddress;
  //会员折扣
    private String zhekou;
    
    public String getZhekou() {
		return zhekou;
	}

	public void setZhekou(String zhekou) {
		this.zhekou = zhekou;
	}

	//每页显示几行
    private int perPageLine;
    //从第几行开始显示
    private int startLine;
    
    
   
	public String getHyAddress() {
		return hyAddress;
	}

	public void setHyAddress(String hyAddress) {
		this.hyAddress = hyAddress;
	}

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

	public String getHyCuntL() {
		return hyCuntL;
	}

	public void setHyCuntL(String hyCuntL) {
		this.hyCuntL = hyCuntL;
	}

	public String getHyCuntR() {
		return hyCuntR;
	}

	public void setHyCuntR(String hyCuntR) {
		this.hyCuntR = hyCuntR;
	}

	public String getHyDateL() {
		return hyDateL;
	}

	public void setHyDateL(String hyDateL) {
		this.hyDateL = hyDateL;
	}

	public String getHyDateR() {
		return hyDateR;
	}

	public void setHyDateR(String hyDateR) {
		this.hyDateR = hyDateR;
	}

	public String getHyExpenseL() {
		return hyExpenseL;
	}

	public void setHyExpenseL(String hyExpenseL) {
		this.hyExpenseL = hyExpenseL;
	}

	public String getHyExpenseR() {
		return hyExpenseR;
	}

	public void setHyExpenseR(String hyExpenseR) {
		this.hyExpenseR = hyExpenseR;
	}

	public String getHyCode() {
        return hyCode;
    }

    public void setHyCode(String hyCode) {
        this.hyCode = hyCode == null ? null : hyCode.trim();
    }

    public String getHyKindCode() {
        return hyKindCode;
    }

    public void setHyKindCode(String hyKindCode) {
        this.hyKindCode = hyKindCode == null ? null : hyKindCode.trim();
    }

    public String getHyTel() {
        return hyTel;
    }

    public void setHyTel(String hyTel) {
        this.hyTel = hyTel == null ? null : hyTel.trim();
    }

    public String getHyName() {
        return hyName;
    }

    public void setHyName(String hyName) {
        this.hyName = hyName == null ? null : hyName.trim();
    }

    public String getHyCard() {
        return hyCard;
    }

    public void setHyCard(String hyCard) {
        this.hyCard = hyCard == null ? null : hyCard.trim();
    }

    public String getHyPhoto() {
        return hyPhoto;
    }

    public void setHyPhoto(String hyPhoto) {
        this.hyPhoto = hyPhoto == null ? null : hyPhoto.trim();
    }

    public String getHyTotal() {
        return hyTotal;
    }

    public void setHyTotal(String hyTotal) {
        this.hyTotal = hyTotal == null ? null : hyTotal.trim();
    }

    public String getHyRemainMoney() {
        return hyRemainMoney;
    }

    public void setHyRemainMoney(String hyRemainMoney) {
        this.hyRemainMoney = hyRemainMoney == null ? null : hyRemainMoney.trim();
    }

    public String getHyBirthday() {
        return hyBirthday;
    }

    public void setHyBirthday(String hyBirthday) {
        this.hyBirthday = hyBirthday == null ? null : hyBirthday.trim();
    }

    public String getHyCunt() {
        return hyCunt;
    }

    public void setHyCunt(String hyCunt) {
        this.hyCunt = hyCunt == null ? null : hyCunt.trim();
    }

    public String getHyBz() {
        return hyBz;
    }

    public void setHyBz(String hyBz) {
        this.hyBz = hyBz == null ? null : hyBz.trim();
    }

    public String getHyJf() {
        return hyJf;
    }

    public void setHyJf(String hyJf) {
        this.hyJf = hyJf == null ? null : hyJf.trim();
    }

    public String getHyDate() {
        return hyDate;
    }

    public void setHyDate(String hyDate) {
        this.hyDate = hyDate == null ? null : hyDate.trim();
    }

    public String getHyExpense() {
        return hyExpense;
    }

    public void setHyExpense(String hyExpense) {
        this.hyExpense = hyExpense == null ? null : hyExpense.trim();
    }
}