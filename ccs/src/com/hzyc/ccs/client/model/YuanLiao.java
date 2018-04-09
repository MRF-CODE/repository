package com.hzyc.ccs.client.model;
public class YuanLiao {
    private String ylCode;

    private String ylName;

    private String ylPrice;

    private String ylNumber;

    private String ylBz;

    public String getYlCode() {
        return ylCode;
    }

    public void setYlCode(String ylCode) {
        this.ylCode = ylCode == null ? null : ylCode.trim();
    }

    public String getYlName() {
        return ylName;
    }

    public void setYlName(String ylName) {
        this.ylName = ylName == null ? null : ylName.trim();
    }

    public String getYlPrice() {
        return ylPrice;
    }

    public void setYlPrice(String ylPrice) {
        this.ylPrice = ylPrice == null ? null : ylPrice.trim();
    }

    public String getYlNumber() {
        return ylNumber;
    }

    public void setYlNumber(String ylNumber) {
        this.ylNumber = ylNumber == null ? null : ylNumber.trim();
    }

    public String getYlBz() {
        return ylBz;
    }

    public void setYlBz(String ylBz) {
        this.ylBz = ylBz == null ? null : ylBz.trim();
    }
}