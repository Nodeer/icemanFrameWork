package com.iceman.yangtze.manager;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.iceman.yangtze.AppApplication;

public class CustomInputMethodManager {
	/**
	 * 隐藏输入框
	 * 
	 * @param view
	 */
	public static void hideSoftInput(EditText view) {
		final android.view.inputmethod.InputMethodManager imm = (android.view.inputmethod.InputMethodManager) AppApplication.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE);
		final IMMResult result = new IMMResult();
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0, result);
	}

	/**
	 * 显示 输入框
	 * 
	 * @param view
	 */
	public static void showSoftInput(EditText view) {
		final android.view.inputmethod.InputMethodManager imm = (android.view.inputmethod.InputMethodManager) AppApplication.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE);
		final IMMResult result = new IMMResult();
		imm.showSoftInput(view, 0, result);
		view.postDelayed(new Runnable() {
			@Override
			public void run() {
				int res = result.getResult();
				if (res != android.view.inputmethod.InputMethodManager.RESULT_SHOWN && res != android.view.inputmethod.InputMethodManager.RESULT_UNCHANGED_HIDDEN) {
					imm.toggleSoftInput(android.view.inputmethod.InputMethodManager.SHOW_FORCED, 0);
				}
			}
		}, 500);
		view.requestFocus();
	}
}
