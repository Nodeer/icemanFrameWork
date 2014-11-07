/** 
 * @ProjectName:CYFrameAndroid  
 * @Title: Device.java 
 * @Package com.cyou.cyframeandroid.util 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author niuhb niuhaobo@cyou-inc.com   
 * @date 2013-8-22 下午3:49:37 
 * @version V1.0   
 * Copyright (c) 2013搜狐公司-版权所有
 */
package com.iceman.yangtze.util;

import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

import com.iceman.yangtze.AppApplication;

/**
 * @ClassName: Device
 * @Description: 获取设备相关数据
 * @author niuhb niuhaobo@cyou-inc.com
 * @date 2013-8-22 下午3:49:37
 */
public class DeviceUtil {

	/**
	 * @Title: getScreem
	 * @Description: 获取屏幕分辨率 比如480*800
	 * @param: @param ctx
	 * @param: @return 设定文件
	 * @return: String 返回类型
	 * @date: 2013-8-22 下午5:15:54
	 */
	@SuppressWarnings("deprecation")
	public static String getScreem(Activity ctx) {
		WindowManager mWindowManager = ctx.getWindowManager();
		Display display = mWindowManager.getDefaultDisplay();
		int diaplayWidth = display.getWidth();
		int diaplayHeight = display.getHeight();
		return diaplayWidth + "*" + diaplayHeight;
	}

	/**
	 * @Title: getDeviceId
	 * @Description: 获取设备ID
	 * @param: @param ctx
	 * @param: @return 设定文件
	 * @return: String 返回类型
	 * @date: 2013-8-22 下午5:16:28
	 */
	public static String getDeviceId(Context ctx) {
		TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
		String deviceId = tm.getDeviceId();
		if (deviceId == null || deviceId.trim().length() == 0) {
			deviceId = String.valueOf(System.currentTimeMillis());
		}
		return deviceId;
	}

	/**
	 * 获取屏幕的宽和高的工具类 wangqing 2013-6-14 DisplayMetrics
	 * 
	 * @param context
	 * @return
	 */
	public static DisplayMetrics getAppWidthAndHeight(Context context) {
		// 这个可以用于1.5
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(dm);
		return dm;
	}

	/**
	 * 获取手机设备的 IMEI code wangqing 2013-6-14 String
	 * 
	 * @param context
	 * @return
	 */
	public static String getPhoneIMEICode(Context context) {

		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		String IMEI = telephonyManager.getDeviceId();

		return IMEI;

	}

	/**
	 * @Title: getNetType
	 * @Description: 获取网络类型，wifi 及 手机网络的详细类型
	 * @param: @param ctx
	 * @param: @return 设定文件
	 * @return: String 返回类型
	 * @date: 2013-8-22 下午5:16:48
	 */
	public static String getNetType(Context ctx) {
		ConnectivityManager connManager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = connManager.getActiveNetworkInfo();
		if (info != null) {
			if (info.getType() == ConnectivityManager.TYPE_WIFI) {
				return "WIFI";
			} else {
				return info.getSubtypeName();
			}
		} else {
			return "NONE";
		}
	}

	/**
	 * @Title: getPkgVersion
	 * @Description: 获取指定pkg的版本
	 * @param: @param pkg
	 * @param: @param ctx
	 * @param: @return 设定文件
	 * @return: int 返回类型
	 * @date: 2013-8-22 下午5:17:26
	 */
	public static int getPkgVersion(String pkg, Context ctx) {

		PackageInfo pi = getPackageInfo(ctx);
		return pi.versionCode;
	}

	public static PackageInfo getPackageInfo(Context ctx) {

		PackageManager pm = ctx.getPackageManager();
		PackageInfo pi = null;
		try {
			pi = pm.getPackageInfo(ctx.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pi;
	}

	public static String getPKgVersionName(Context ctx) {
		PackageInfo pi = getPackageInfo(ctx);
		return pi.versionName;

	}

	public static int getPkgVersion(Context ctx) {
		String pkg = ctx.getPackageName();
		return getPkgVersion(pkg, ctx);
	}

	/**
	 * @Title: addDeviceInfo
	 * @Description: 意见反馈中增加设备信息
	 * @param: @param map
	 * @param: @param ctx 设定文件
	 * @return: void 返回类型
	 * @date: 2013-8-22 下午5:17:45
	 */
	public static void addDeviceInfo(Map<String, Object> map, Activity ctx) {
		map.put("version", getPkgVersion(ctx));
		map.put("mobileModel", Build.MODEL);
		map.put("os", Build.VERSION.RELEASE);
		map.put("screen", getScreem(ctx));
		map.put("network", getNetType(ctx));
		map.put("deviceId", getDeviceId(ctx));
		// TODO 渠道号打包时怎么处理 需修改
		map.put("channel", "ch");
	}

	/**
	 * Dip转换为实际屏幕的像素值
	 * 
	 * @param dm
	 *            设备显示对象描述
	 * @param dip
	 *            dip值
	 * @return 匹配当前屏幕的像素值
	 */
	public static int getPixelFromDip(float dip) {
		AppApplication app = AppApplication.getInstance();
		return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, app.getResources().getDisplayMetrics()) + 0.5f);
	}

	/**
	 * 获取屏幕的宽高
	 * 
	 * @param dm
	 *            设备显示对象描述
	 * @return int数组, int[0] - width, int[1] - height
	 */
	public static int[] getScreenSize(DisplayMetrics dm) {
		int[] result = new int[2];
		result[0] = dm.widthPixels;
		result[1] = dm.heightPixels;
		return result;
	}

}
