package com.hzyc.ccs.model;

public class StoreSign {
    private Integer id;

    private String sellStore;

    private String flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSellStore() {
        return sellStore;
    }

    public void setSellStore(String sellStore) {
        this.sellStore = sellStore == null ? null : sellStore.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }
}