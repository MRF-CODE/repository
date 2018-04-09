package com.hzyc.ccs.client.model;

import java.util.List;

public class Orders {
	
	/**
	 * ż�����ڷ�װ�ö������ж�����Ʒ
	 * */
	private String allGoods;
	
	private String chooseGood;
	
	
	public String getChooseGood() {
		return chooseGood;
	}

	public void setChooseGood(String chooseGood) {
		this.chooseGood = chooseGood;
	}

	private List<String> setList;
    public String getSetList() {
    	String temp = setList.toString();
		return temp.replaceAll(",", "+").substring(1,temp.length()-1);
	}

	public void setSetList(List<String> setList) {
		this.setList = setList;
	}

	public String getAllGoods() {
		return allGoods;
	}

	public void setAllGoods(String allGoods) {
		this.allGoods = allGoods;
	}

	private String ddCode;

    private String ddTime;

    private String customerName;

    private String customerTel;

    private String customerAddress;

    private String fork;

    private String hat;

    private String songhuoDate;

    private String songhuoTime;

    private String customerBz;

    private String payState;

    private String songhuoState;

    private String zuhezhifuState;

    private String yingshouMoney;

    private String payWay1;

    private String payWay1ShishouMoney;

    private String payJifenMoney;

    private String payWay2;

    private String payWay2ShishouMoney;

    private String zhaolingMoney;

    private String sellStore;

    private String cashier;

    private String songhuoPeople;

    private String peisongfei;

    private String vipCode;
    
    private String chanPinBz;

    private String weikuan;
    
    private String jiFenToMoney;
    
    private String zhuoHao;

    //��ѯʱ����ڵĶ���---��ʼʱ��
    private String startTime;
    
    //��ֹʱ��
    private String endTime;
    /**
     * ��ȡ������ѯ��ʼʱ��
     * @return
     */
    public String getStartTime() {
		return startTime;
	}

	/**����������ѯ��ʼʱ��
	 * @param startTime ��ʼʱ��
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**��ȡ������ѯ��ֹʱ��
	 * @param startTime ��ʼʱ��
	 */
	public String getEndTime() {
		return endTime;
	}
	/**����������ѯ��ֹʱ��
	 * @param startTime ��ʼʱ��
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getZhuoHao() {
		return zhuoHao;
	}

	public void setZhuoHao(String zhuoHao) {
		this.zhuoHao = zhuoHao;
	}

	public String getJiFenToMoney() {
		return jiFenToMoney;
	}

	public void setJiFenToMoney(String jiFenToMoney) {
		this.jiFenToMoney = jiFenToMoney;
	}

	public String getChanPinBz() {
		return chanPinBz;
	}

	public void setChanPinBz(String chanPinBz) {
		this.chanPinBz = chanPinBz;
	}

	public String getVipCode() {
		return vipCode;
	}

	public void setVipCode(String vipCode) {
		this.vipCode = vipCode;
	}



    public String getWeikuan() {
		return weikuan;
	}

	public void setWeikuan(String weikuan) {
		this.weikuan = weikuan;
	}

	public String getDdCode() {

        return ddCode;
    }

    public void setDdCode(String ddCode) {
        this.ddCode = ddCode == null ? null : ddCode.trim();
    }

    public String getDdTime() {
        return ddTime;
    }

    public void setDdTime(String ddTime) {
        this.ddTime = ddTime == null ? null : ddTime.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel == null ? null : customerTel.trim();
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress == null ? null : customerAddress.trim();
    }

    public String getFork() {
        return fork;
    }

    public void setFork(String fork) {
        this.fork = fork == null ? null : fork.trim();
    }

    public String getHat() {
        return hat;
    }

    public void setHat(String hat) {
        this.hat = hat == null ? null : hat.trim();
    }

    public String getSonghuoDate() {
        return songhuoDate;
    }

    public void setSonghuoDate(String songhuoDate) {
        this.songhuoDate = songhuoDate == null ? null : songhuoDate.trim();
    }

    public String getSonghuoTime() {
        return songhuoTime;
    }

    public void setSonghuoTime(String songhuoTime) {
        this.songhuoTime = songhuoTime == null ? null : songhuoTime.trim();
    }

    public String getCustomerBz() {
        return customerBz;
    }

    public void setCustomerBz(String customerBz) {
        this.customerBz = customerBz == null ? null : customerBz.trim();
    }

    public String getPayState() {
        return payState;
    }

    public void setPayState(String payState) {
        this.payState = payState == null ? null : payState.trim();
    }

    public String getSonghuoState() {
        return songhuoState;
    }

    public void setSonghuoState(String songhuoState) {
        this.songhuoState = songhuoState == null ? null : songhuoState.trim();
    }

    public String getZuhezhifuState() {
        return zuhezhifuState;
    }

    public void setZuhezhifuState(String zuhezhifuState) {
        this.zuhezhifuState = zuhezhifuState == null ? null : zuhezhifuState.trim();
    }

    public String getYingshouMoney() {
        return yingshouMoney;
    }

    public void setYingshouMoney(String yingshouMoney) {
        this.yingshouMoney = yingshouMoney == null ? null : yingshouMoney.trim();
    }

    public String getPayWay1() {
        return payWay1;
    }

    public void setPayWay1(String payWay1) {
        this.payWay1 = payWay1 == null ? null : payWay1.trim();
    }

    public String getPayWay1ShishouMoney() {
        return payWay1ShishouMoney;
    }

    public void setPayWay1ShishouMoney(String payWay1ShishouMoney) {
        this.payWay1ShishouMoney = payWay1ShishouMoney == null ? null : payWay1ShishouMoney.trim();
    }

    public String getPayJifenMoney() {
        return payJifenMoney;
    }

    public void setPayJifenMoney(String payJifenMoney) {
        this.payJifenMoney = payJifenMoney == null ? null : payJifenMoney.trim();
    }

    public String getPayWay2() {
        return payWay2;
    }

    public void setPayWay2(String payWay2) {
        this.payWay2 = payWay2 == null ? null : payWay2.trim();
    }

    public String getPayWay2ShishouMoney() {
        return payWay2ShishouMoney;
    }

    public void setPayWay2ShishouMoney(String payWay2ShishouMoney) {
        this.payWay2ShishouMoney = payWay2ShishouMoney == null ? null : payWay2ShishouMoney.trim();
    }

    public String getZhaolingMoney() {
        return zhaolingMoney;
    }

    public void setZhaolingMoney(String zhaolingMoney) {
        this.zhaolingMoney = zhaolingMoney == null ? null : zhaolingMoney.trim();
    }

    public String getSellStore() {
        return sellStore;
    }

    public void setSellStore(String sellStore) {
        this.sellStore = sellStore == null ? null : sellStore.trim();
    }

    public String getCashier() {
        return cashier;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier == null ? null : cashier.trim();
    }

    public String getSonghuoPeople() {
        return songhuoPeople;
    }

    public void setSonghuoPeople(String songhuoPeople) {
        this.songhuoPeople = songhuoPeople == null ? null : songhuoPeople.trim();
    }

    public String getPeisongfei() {
        return peisongfei;
    }

    public void setPeisongfei(String peisongfei) {
        this.peisongfei = peisongfei == null ? null : peisongfei.trim();
    }
}