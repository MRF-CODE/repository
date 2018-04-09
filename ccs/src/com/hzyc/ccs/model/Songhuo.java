package com.hzyc.ccs.model;

public class Songhuo {
    private String songhuoCode;

    private String ddCode;

    public String getSonghuoCode() {
        return songhuoCode;
    }

    public void setSonghuoCode(String songhuoCode) {
        this.songhuoCode = songhuoCode == null ? null : songhuoCode.trim();
    }

    public String getDdCode() {
        return ddCode;
    }

    public void setDdCode(String ddCode) {
        this.ddCode = ddCode == null ? null : ddCode.trim();
    }
}