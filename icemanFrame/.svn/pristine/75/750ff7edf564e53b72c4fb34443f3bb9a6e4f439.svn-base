package com.iceman.yangtze.widget;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;

import com.iceman.yangtze.BaseActivity;
import com.iceman.yangtze.R;
import com.iceman.yangtze.manager.FragmentExchangeController;
import com.iceman.yangtze.model.CtripDialogExchangeModel;
import com.iceman.yangtze.model.CtripDialogExchangeModel.CtripDialogExchangeModelBuilder;

public class BaseDialogFragmentV2 extends DialogFragment {
	public final static String TAG = "CtripHDBaseDialogFragment";
	protected String mDialogTag;// 标记
	protected String mTitleTxt;// 标题
	protected String mPositiveBtnTxt;// 确认操作
	protected String mNegativeBtnTxt;// 取消操作
	protected String mSingleBtnTxt;// 单个button文字
	protected String mContentTxt;// 内容
	public boolean bIsBackable;// 是否back取消
	public boolean bIsSpaceable;// 是否空白取消
	public OnClickListener compatibilityListener;// 错误弹框 按键点击事件
	public OnClickListener compatibilityPositiveListener, compatibilityNegativeListener;
	public int gravity = Gravity.CENTER;
	protected OnClickListener mSpaceClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (bIsSpaceable) {
				Fragment tarFragment = getTargetFragment();
				Activity activity = getActivity();
				dismissSelf();
			}
		}
	};

	public static BaseDialogFragmentV2 getInstance(Bundle bundle) {
		BaseDialogFragmentV2 baseDialogFragment = new BaseDialogFragmentV2();
		baseDialogFragment.setArguments(bundle);
		return baseDialogFragment;
	}

	public BaseDialogFragmentV2() {

	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(DialogFragment.STYLE_NO_FRAME, R.style.ThemeHolo);
		if (getArguments() != null) {
			Bundle bundle = getArguments();
			CtripDialogExchangeModel ctripDialogExchangeModel = ((CtripDialogExchangeModelBuilder) bundle.getSerializable(TAG)).creat();
			if (ctripDialogExchangeModel != null) {
				mDialogTag = ctripDialogExchangeModel.getTag();
				bIsBackable = ctripDialogExchangeModel.isBackable();
				bIsSpaceable = ctripDialogExchangeModel.isSpaceable();
				mContentTxt = ctripDialogExchangeModel.getDialogContext();
				setCancelable(bIsBackable);
			}
		}
	}
	@Override
	public void dismiss() {
		super.dismissAllowingStateLoss();
	}

	@Override
	public int show(FragmentTransaction transaction, String tag) {
		return show(transaction, tag, true);
	}

	public int show(FragmentTransaction transaction, String tag, boolean allowStateLoss) {
		transaction.add(this, tag);
		int mBackStackId = allowStateLoss ? transaction.commitAllowingStateLoss() : transaction.commit();
		return mBackStackId;
	}

	public void dismissSelf() {
		FragmentExchangeController.removeFragment(getFragmentManager(), this);
	}

}
