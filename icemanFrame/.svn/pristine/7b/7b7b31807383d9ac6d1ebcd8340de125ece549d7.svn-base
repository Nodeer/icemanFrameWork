package com.iceman.yangtze.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.iceman.yangtze.AppApplication;

public class SharePreUtil {
	private SharedPreferences sp = null;
	private Editor edit = null;
	public static final String SHARE_PREFER_FILENAME = "yangtzeu";
	private static SharePreUtil sInstance = new SharePreUtil(AppApplication.getInstance());

	public static SharePreUtil getInstance() {
		return sInstance;
	}

	public static void init(Context context) {
		sInstance = new SharePreUtil(context);
	}

	public SharePreUtil(Context context) {
		sp = context.getSharedPreferences(SHARE_PREFER_FILENAME, Context.MODE_PRIVATE);
		edit = sp.edit();
	}

	// Boolean
	public void setBooleanValue(String key, boolean value) {
		edit.putBoolean(key, value);
		edit.commit();
	}

	// Integer
	public void setIntValue(String key, int value) {
		edit.putInt(key, value);
		edit.commit();
	}

	// Long
	public void setLongValue(String key, long value) {
		edit.putLong(key, value);
		edit.commit();
	}

	// String
	public void setStringValue(String key, String value) {
		edit.putString(key, value);
		edit.commit();
	}

	// Get

	// Boolean
	public boolean getBooleanValue(String key, boolean defaultValue) {
		return sp.getBoolean(key, defaultValue);
	}

	// Integer
	public int getIntValue(String key, int defaultValue) {
		return sp.getInt(key, defaultValue);
	}

	// Long
	public long getLongValue(String key, long defaultValue) {
		return sp.getLong(key, defaultValue);
	}

	// String
	public String getStringValue(String key, String defaultValue) {
		return sp.getString(key, defaultValue);
	}

	// Delete
	public void remove(String key) {
		edit.remove(key);
		edit.commit();
	}

	public void clear() {
		edit.clear();
		edit.commit();
	}

}
