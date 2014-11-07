package com.iceman.yangtze.bean;

/** 用户个人信息bean */
public class UserInfoBean {
	private static UserInfoBean sInstance = new UserInfoBean();
	public static UserInfoBean getInstance() {
		return sInstance;
	}
	public String name = "";
	public String no = "";
	public String className = "";
	public String sex = "";
	public String sfzNo = "";
	public String img = "";
	public boolean isLogin = false;

}
