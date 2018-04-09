package com.hzyc.ccs.client.model;

public class VipKind {
    private Integer id;

    private String hyKindCode;

    private String kind;

    private String zhekou;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHyKindCode() {
        return hyKindCode;
    }

    public void setHyKindCode(String hyKindCode) {
        this.hyKindCode = hyKindCode == null ? null : hyKindCode.trim();
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind == null ? null : kind.trim();
    }

    public String getZhekou() {
        return zhekou;
    }

    public void setZhekou(String zhekou) {
        this.zhekou = zhekou == null ? null : zhekou.trim();
    }
}