package com.iceman.yangtze.manager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.iceman.yangtze.BaseActivity;
import com.iceman.yangtze.model.CtripDialogExchangeModel;
import com.iceman.yangtze.model.CtripDialogType;
import com.iceman.yangtze.widget.BaseDialogFragmentV2;
import com.iceman.yangtze.widget.CustomerDialogFragment;
import com.iceman.yangtze.widget.ExcuteInfoDialogFragment;
import com.iceman.yangtze.widget.ProcessDialogFragment;
import com.iceman.yangtze.widget.SingleInfoDialogFragment;

public class DialogManager {
	public final static int DIALOG_REQUEST_CODE = 0x2001;

	/**
	 * 弹框方法
	 * 
	 * fragment与baseActivityV2不可同时为NULL
	 * 
	 * @param fragmentManager
	 *            (必传字段)
	 * @param ctripDialogExchangeModel
	 *            (必传字段)
	 * @param fragment
	 *            (选传)
	 * @return CtripBaseDialogFragmentV2对象
	 */
	public static BaseDialogFragmentV2 showDialogFragment(FragmentManager fragmentManager,
			CtripDialogExchangeModel ctripDialogExchangeModel, Fragment fragment) {

		BaseDialogFragmentV2 baseDialogFragment = null;
		if (ctripDialogExchangeModel != null) {
			Bundle bundle = new Bundle();
			bundle.putSerializable("CtripHDBaseDialogFragment", ctripDialogExchangeModel.ctripDialogExchangeModelBuilder);
			CtripDialogType ctripHDDialogType = ctripDialogExchangeModel.getDialogType();
			if (ctripHDDialogType == CtripDialogType.SINGLE) {
				baseDialogFragment = SingleInfoDialogFragment.getInstance(bundle);
			} else if (ctripHDDialogType == CtripDialogType.EXCUTE) {
				baseDialogFragment = ExcuteInfoDialogFragment.getInstance(bundle);
			} else if (ctripHDDialogType == CtripDialogType.CUSTOMER) {
				baseDialogFragment = CustomerDialogFragment.getInstance(bundle);
			} else if (ctripHDDialogType == CtripDialogType.PROGRESS) {
				baseDialogFragment = ProcessDialogFragment.getInstance(bundle);
			}
		}
		if (baseDialogFragment != null) {
			baseDialogFragment.compatibilityListener = ctripDialogExchangeModel.compatibilityListener;
			baseDialogFragment.compatibilityNegativeListener = ctripDialogExchangeModel.compatibilityNegativeListener;
			baseDialogFragment.compatibilityPositiveListener = ctripDialogExchangeModel.compatibilityPositiveListener;
		}
		if (baseDialogFragment != null) {
			if (fragment != null) {
				baseDialogFragment.setTargetFragment(fragment, DIALOG_REQUEST_CODE);
			}
			FragmentTransaction ft = fragmentManager.beginTransaction();
			ft.add(baseDialogFragment, ctripDialogExchangeModel.getTag());
			ft.commitAllowingStateLoss();
		}
		return baseDialogFragment;
	}
}
