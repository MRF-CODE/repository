package com.hzyc.ccs.model;

public class UsersWithBLOBs extends Users {
    private byte[] img;

    private byte[] img1;

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public byte[] getImg1() {
        return img1;
    }

    public void setImg1(byte[] img1) {
        this.img1 = img1;
    }
}