package com.iceman.yangtze;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;

import com.iceman.yangtze.manager.AppController;

public class BaseActivity extends FragmentActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (this instanceof InitActivity) {
			// 防止初始化页面起不来
		} else {
			if (!AppController.isHomeAlive) {
//				finish();
			}
		}
	}

	protected void goHome() {
		if (this instanceof InitActivity /*|| this instanceof MainActivity*/) {

		} else {
//			Intent intent = new Intent(AppApplication.getInstance(), MainActivity.class);
//			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//			AppController.isHomeAlive = true;
//			startActivity(intent);
//			finish();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (KeyEvent.KEYCODE_BACK == keyCode) {
			if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
				finish();
				return true;
			} else {
				try {
					getSupportFragmentManager().popBackStackImmediate();
				} catch (Exception e) {
					// TODO: handle exception
				}
				return true;
			}
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

}
