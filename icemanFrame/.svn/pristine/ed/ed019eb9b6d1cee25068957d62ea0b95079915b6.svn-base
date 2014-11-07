package com.iceman.yangtze.widget;

import android.support.v4.app.Fragment;
import com.iceman.yangtze.ServerActivity;
import com.iceman.yangtze.ServerFragment;
import net.tsz.afinal.http.HttpHandler;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iceman.yangtze.R;
import com.iceman.yangtze.interfaces.BaseServerInterface;
import com.iceman.yangtze.model.CtripDialogExchangeModel;
import com.iceman.yangtze.model.CtripDialogExchangeModel.CtripDialogExchangeModelBuilder;
import com.iceman.yangtze.util.StringUtil;

/**
 * 实现显示加载进度框效果。
 * 
 */
public class ProcessDialogFragment extends BaseDialogFragmentV2 implements BaseServerInterface {

	private TextView mDlgContent;
	private View mCancelButton;
	private boolean bIsBussinessCancelable;// 服务是否可取消
	private String netTag;

	public static ProcessDialogFragment getInstance(Bundle bundle) {
		ProcessDialogFragment baseDialogFragment = new ProcessDialogFragment();
		baseDialogFragment.setArguments(bundle);
		return baseDialogFragment;
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
				mContentTxt = ctripDialogExchangeModel.getDialogContext();
				bIsBussinessCancelable = ctripDialogExchangeModel.isBussinessCancleable();
				netTag = ctripDialogExchangeModel.getNetTag();
			}
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View layoutView = inflater.inflate(R.layout.process_load_data_layout, container, false);
		layoutView.setOnClickListener(mSpaceClickListener);

		mDlgContent = (TextView) layoutView.findViewById(R.id.tip);
		if (!StringUtil.emptyOrNull(mContentTxt)) {
			mDlgContent.setText(mContentTxt);
		}

		mCancelButton = layoutView.findViewById(R.id.btn_cancel);
		mCancelButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dismissSelf();
				if (getTargetFragment() != null) {
					Fragment fra = getTargetFragment();
					if (fra instanceof ServerFragment) {
						((ServerFragment) fra).getRequestQueue().cancelAll(netTag);
					}
				} else {
					((ServerActivity) getActivity()).getRequestQueue().cancelAll(netTag);
				}
			}
		});
		if (!bIsBussinessCancelable) {
			mCancelButton.setVisibility(View.GONE);
		} else {
			mCancelButton.setVisibility(View.VISIBLE);
		}
		return layoutView;
	}

	@Override
	public void dismiss() {
		super.dismiss();
	}

	@Override
	public void dismissSelf() {
		super.dismissSelf();
	}

	@Override
	public void bussinessSuccess(String token) {
		// TODO Auto-generated method stub
		dismissSelf();
	}

	@Override
	public void bussinessFail(String token) {
		// TODO Auto-generated method stub
		dismissSelf();
	}

	@Override
	public void bussinessStart() {
		// TODO Auto-generated method stub

	}

	public void setContentText(String content) {
		mDlgContent.setText(content);
	}
}
