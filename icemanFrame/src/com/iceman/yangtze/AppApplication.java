package com.iceman.yangtze;

import java.security.MessageDigest;
import java.util.Timer;

import android.app.Application;

public class AppApplication extends Application {
	private static AppApplication sAppInstance;
	private boolean hasPosition = false;
	private Timer mLocationTimer;

	@Override
	public void onCreate() {
		super.onCreate();
		sAppInstance = this;
    }

	public static AppApplication getInstance() {
		return sAppInstance;
	}

	/** 使用当前位置 */
}
