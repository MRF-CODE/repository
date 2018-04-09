package com.hzyc.ccs.client.model;

public class VipRecord {
	/*codevarchar(20)��������Ա�ţ�
	typevarchar(40)��ֵ��������
	moneyvarchar(40)���
	give_moneyvarchar(40)��ֵ�����ͽ��
	operate_datevarchar(40)����ʱ��
	give_goodvarchar(100)��Ʒ��ע
	is_receivevarchar(4)�Ƿ���ȡ��Ʒ*/
	private String id;
	
	//��Ʒ��ȡʱ��
	private String lingquDate;
	
	public String getLingquDate() {
		return lingquDate;
	}
	public void setLingquDate(String lingquDate) {
		this.lingquDate = lingquDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private String code;
	
	private String type;
	private String money;
	private String giveMoney;
	private String operateDate;
	private String giveGood;
	private String isReceive;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getGiveMoney() {
		return giveMoney;
	}
	public void setGiveMoney(String giveMoney) {
		this.giveMoney = giveMoney;
	}
	public String getOperateDate() {
		return operateDate;
	}
	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
	}
	public String getGiveGood() {
		return giveGood;
	}
	public void setGiveGood(String giveGood) {
		this.giveGood = giveGood;
	}
	public String getIsReceive() {
		return isReceive;
	}
	public void setIsReceive(String isReceive) {
		this.isReceive = isReceive;
	}
	
}
