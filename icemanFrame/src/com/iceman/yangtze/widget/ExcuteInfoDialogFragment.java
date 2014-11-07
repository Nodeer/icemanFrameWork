package com.iceman.yangtze.widget;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iceman.yangtze.R;
import com.iceman.yangtze.interfaces.ExcuteDialogFragmentCallBack;
import com.iceman.yangtze.model.CtripDialogExchangeModel;
import com.iceman.yangtze.model.CtripDialogExchangeModel.CtripDialogExchangeModelBuilder;
import com.iceman.yangtze.util.StringUtil;

public class ExcuteInfoDialogFragment extends BaseDialogFragmentV2 {

	private TextView mDlgContent, mBtnLeft, mRightBtn;
	private OnClickListener mExcuitePositiveListener, mExcuiteNegativeListener;
	public static ExcuteInfoDialogFragment getInstance(Bundle bundle) {
		ExcuteInfoDialogFragment baseDialogFragment = new ExcuteInfoDialogFragment();
		baseDialogFragment.setArguments(bundle);
		return baseDialogFragment;
	}

	public ExcuteInfoDialogFragment() {

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			Bundle bundle = getArguments();
			CtripDialogExchangeModel ctripDialogExchangeModel = ((CtripDialogExchangeModelBuilder) bundle.getSerializable(TAG)).creat();
			if (ctripDialogExchangeModel != null) {
				mDialogTag = ctripDialogExchangeModel.getTag();
				mTitleTxt = ctripDialogExchangeModel.getDialogTitle();
				mPositiveBtnTxt = ctripDialogExchangeModel.getPostiveText();
				mNegativeBtnTxt = ctripDialogExchangeModel.getNegativeText();
				mContentTxt = ctripDialogExchangeModel.getDialogContext();
				gravity = ctripDialogExchangeModel.getGravity();
			}
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.dialog_excute_layout, container, false);
		view.setOnClickListener(mSpaceClickListener);
		mDlgContent = (TextView) view.findViewById(R.id.content_text);
		if (!StringUtil.emptyOrNull(mContentTxt)) {
			mDlgContent.setText(mContentTxt);
			if (gravity != -1) {
				mDlgContent.setGravity(gravity);
			}
			if (getActivity() != null && mContentTxt.contains("确认退出")) {
				mDlgContent.setTextAppearance(getActivity(), R.style.text_22_666666_sdw);
			}
		}
		mBtnLeft = (TextView) view.findViewById(R.id.lef_btn);
		mRightBtn = (TextView) view.findViewById(R.id.right_btn);

		mExcuitePositiveListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// CtripDialogFragmentCallBack callBack =
				// getCtripDialogFragmentCallBack();
				Fragment tarFragment = getTargetFragment();
				Activity activity = getActivity();
				dismissSelf();
				if (tarFragment != null && tarFragment instanceof ExcuteDialogFragmentCallBack) {
					((ExcuteDialogFragmentCallBack) tarFragment).onPositiveBtnClick(mDialogTag);
				} else if (activity != null && activity instanceof ExcuteDialogFragmentCallBack) {
					((ExcuteDialogFragmentCallBack) activity).onPositiveBtnClick(mDialogTag);
				}
			}
		};

		mExcuiteNegativeListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragment tarFragment = getTargetFragment();
				Activity activity = getActivity();
				dismissSelf();
				if (tarFragment != null && tarFragment instanceof ExcuteDialogFragmentCallBack) {
					((ExcuteDialogFragmentCallBack) tarFragment).onNegtiveBtnClick(mDialogTag);
				} else if (activity != null && activity instanceof ExcuteDialogFragmentCallBack) {
					((ExcuteDialogFragmentCallBack) activity).onNegtiveBtnClick(mDialogTag);
				}
			}
		};

		if (android.os.Build.VERSION.SDK_INT >= 14) {
			if (!StringUtil.emptyOrNull(mPositiveBtnTxt)) {
				mRightBtn.setText(mPositiveBtnTxt);
			} else {
				mRightBtn.setText(R.string.ok);
			}
			mRightBtn.setOnClickListener(mExcuitePositiveListener);
			mRightBtn.setBackgroundResource(R.drawable.btn_dialog_selector);

			if (!StringUtil.emptyOrNull(mNegativeBtnTxt)) {
				mBtnLeft.setText(mNegativeBtnTxt);
			} else {
				mBtnLeft.setText(R.string.cancel);
			}
			mBtnLeft.setOnClickListener(mExcuiteNegativeListener);
			mBtnLeft.setBackgroundResource(R.drawable.btn_dialog_selector);
		} else {
			if (!StringUtil.emptyOrNull(mPositiveBtnTxt)) {
				mBtnLeft.setText(mPositiveBtnTxt);
			} else {
				mBtnLeft.setText(R.string.ok);
			}
			mBtnLeft.setOnClickListener(mExcuitePositiveListener);
			mBtnLeft.setBackgroundResource(R.drawable.btn_dialog_selector);

			if (!StringUtil.emptyOrNull(mNegativeBtnTxt)) {
				mRightBtn.setText(mNegativeBtnTxt);
			} else {
				mRightBtn.setText(R.string.cancel);
			}
			mRightBtn.setOnClickListener(mExcuiteNegativeListener);
			mRightBtn.setBackgroundResource(R.drawable.btn_dialog_selector);
		}
		return view;
	}
}
