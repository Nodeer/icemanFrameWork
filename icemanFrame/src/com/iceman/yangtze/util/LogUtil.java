package com.iceman.yangtze.util;

import android.util.Log;

import com.iceman.yangtze.manager.AppController;

public class LogUtil {
	public static void i(String s) {
		if (s == null) {
			s = "null";
		}
		if (AppController.isDebug) {
			i(s, false);
		}
	}
	public static void e(String s) {
		if (s == null) {
			s = "null";
		}
		if (AppController.isDebug) {
			e(s, false);
		}
	}
	public static void d(String s) {
		if (s == null) {
			s = "null";
		}
		if (AppController.isDebug) {
			d(s, false);
		}
	}
	public static void i(String s, boolean isWrite) {
		if (s == null) {
			s = "null";
		}
		if (AppController.isDebug) {
			Log.i("debug", s);
			if (isWrite) {
				FileUtil.printToFile(s, "log.txt");
			}
		}
	}
	public static void e(String s, boolean isWrite) {
		if (s == null) {
			s = "null";
		}
		if (AppController.isDebug) {
			Log.e("debug", s);
			if (isWrite) {
				FileUtil.printToFile(s, "log.txt");
			}
		}
	}
	public static void d(String s, boolean isWrite) {
		if (s == null) {
			s = "null";
		}
		if (AppController.isDebug) {
			Log.d("debug", s);
			if (isWrite) {
				FileUtil.printToFile(s, "log.txt");
			}
		}
	}
}
