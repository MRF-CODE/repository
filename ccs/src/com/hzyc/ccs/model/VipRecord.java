package com.hzyc.ccs.model;

public class VipRecord {
    private String id;

    private String code;

    private String type;

    private String money;

    private String giveMoney;

    private String giveGood;

    private String isReceive;
    private String operateDate;
    private String lingquDate;
    public String getLingquDate() {
		return lingquDate;
	}

	public void setLingquDate(String lingquDate) {
		this.lingquDate = lingquDate;
	}

	public String getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money == null ? null : money.trim();
    }

    public String getGiveMoney() {
        return giveMoney;
    }

    public void setGiveMoney(String giveMoney) {
        this.giveMoney = giveMoney == null ? null : giveMoney.trim();
    }

    public String getGiveGood() {
        return giveGood;
    }

    public void setGiveGood(String giveGood) {
        this.giveGood = giveGood == null ? null : giveGood.trim();
    }

    public String getIsReceive() {
        return isReceive;
    }

    public void setIsReceive(String isRecevice) {
        this.isReceive = isRecevice == null ? null : isRecevice.trim();
    }
}