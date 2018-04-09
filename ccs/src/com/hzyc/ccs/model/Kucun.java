package com.hzyc.ccs.model;

public class Kucun {
    private Integer id;

    private String goodCode;

    private String goodNumber;

    private String storeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodCode() {
        return goodCode;
    }

    public void setGoodCode(String goodCode) {
        this.goodCode = goodCode == null ? null : goodCode.trim();
    }

    public String getGoodNumber() {
        return goodNumber;
    }

    public void setGoodNumber(String goodNumber) {
        this.goodNumber = goodNumber == null ? null : goodNumber.trim();
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }
}