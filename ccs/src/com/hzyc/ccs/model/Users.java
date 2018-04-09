package com.hzyc.ccs.model;

public class Users {
    private Integer userid;

    private String uname;

    private String userpw;

    private String storeName;

    private String trueName;

    private String imgName;

    private String imgName1;
    //每页显示几行
    private int perPageLine;
    //从第几行开始显示
    private int startLine;
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

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	//权限
    private String permission;
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getUserpw() {
        return userpw;
    }

    public void setUserpw(String userpw) {
        this.userpw = userpw == null ? null : userpw.trim();
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName == null ? null : trueName.trim();
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName == null ? null : imgName.trim();
    }

    public String getImgName1() {
        return imgName1;
    }

    public void setImgName1(String imgName1) {
        this.imgName1 = imgName1 == null ? null : imgName1.trim();
    }
}