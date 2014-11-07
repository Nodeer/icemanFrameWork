package com.iceman.yangtze.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.iceman.yangtze.R;
import com.iceman.yangtze.interfaces.BaseServerInterface;

public class CtripLoadingLayout extends FrameLayout implements BaseServerInterface {

	/**
	 * 是否有错误布局
	 * */
	private boolean bHasErrorLayout = false;
	/**
	 * 加载中布局id
	 * */
	private int loadingLayoutId;
	/** 加载中视图 */
	public View loadingLayout;
	/** 加载中文字 */
	private TextView loadingTextView;
	/**
	 * 一般错误布局id
	 * */
	private int errorLayoutNormalId;
	/**
	 * 无数据错误布局id
	 * */
	private int errorLayoutNoDataId;
	/** 错误布局 */
	protected View errorLayout;
	/** 服务回调 */
	private LoadingCallBackListener callBackListener;
	/** 错误文字提示 */
	private TextView errorText;
	/** 错误图片提示 */
	private ImageView errorIv;
	/** 刷新点击监听 */
	private OnClickListener refreashClickListener;
	public boolean isDetachFromWindow = false;

	public CtripLoadingLayout(Context context) {
		super(context.getApplicationContext());
		setUpPartProcessLayout();
	}

	public CtripLoadingLayout(Context context, AttributeSet attrs) {
		super(context.getApplicationContext(), attrs);
		if (attrs != null) {
			TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CtripLoadingLayout);
			if (a != null) {
				bHasErrorLayout = a.getBoolean(R.styleable.CtripLoadingLayout_show_error_layout, true);
				loadingLayoutId = a.getResourceId(R.styleable.CtripLoadingLayout_loading_layout, R.layout.list_view_loading_indicator);
				errorLayoutNormalId = a.getResourceId(R.styleable.CtripLoadingLayout_error_layout_normal_fail, R.layout.load_error_layout_normal_default);
				errorLayoutNoDataId = a.getResourceId(R.styleable.CtripLoadingLayout_error_layout_no_data_fail, R.layout.load_error_layout_nodata_default);
				a.recycle();
			}
		}
		setUpPartProcessLayout();
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		// isAttachToWindow = true;
	}

	@Override
	protected void onDetachedFromWindow() {
		isDetachFromWindow = true;
		super.onDetachedFromWindow();
	}

	/**
	 * 功能描述:创建局部刷新布局&错误布局
	 */
	protected void setUpPartProcessLayout() {
		LayoutInflater infalter = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		loadingLayout = infalter.inflate(loadingLayoutId, null);
		if (loadingLayout != null) {
			loadingTextView = (TextView) loadingLayout.findViewById(R.id.load_layout_loading_text);
		}

		addView(loadingLayout, new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, Gravity.CENTER));
	}

	/**
	 * 显示加载中转圈
	 */
	public void showProcess() {
		if (loadingLayout != null) {
			loadingLayout.setVisibility(View.VISIBLE);
			loadingLayout.bringToFront();
		}
		if (bHasErrorLayout && errorLayout != null) {
			errorLayout.setVisibility(View.GONE);
		}
	}

	/**
	 * 显示加载中转圈
	 * 
	 * @param token
	 *            加载服务的Token
	 */
	public void showProcess(String token) {
		showProcess();
	}

	/**
	 * 隐藏加载中的转圈显示
	 */
	public void removeProcess() {
		if (loadingLayout != null) {
			loadingLayout.setVisibility(View.GONE);
		}
	}

	/**
	 * 显示加载失败后的错误信息
	 */
	public void showError() {
		if (bHasErrorLayout && errorLayout != null) {
			if (loadingLayout != null) {
				loadingLayout.setVisibility(View.GONE);
			}
			errorLayout.setVisibility(View.VISIBLE);
			errorLayout.bringToFront();
		}
	}

	/**
	 * 隐藏加载失败后的错误信息，且不发送服务
	 */
	public void hideError() {
		if (bHasErrorLayout && errorLayout != null) {
			errorLayout.setVisibility(View.GONE);
		}
	}

	/**
	 * 显示加载成功后，但无数据时的错误信息
	 */
	public void showNoDataError() {
		if (errorLayout != null) {
			removeView(errorLayout);
		}
		if (errorLayoutNoDataId > 0) {
			LayoutInflater infalter = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			errorLayout = infalter.inflate(errorLayoutNoDataId, null);
			if (errorLayout != null) {
				View refreashBtn = errorLayout.findViewById(R.id.load_layout_refreash_btn);
				if (refreashBtn != null && refreashClickListener != null) {
					refreashBtn.setOnClickListener(refreashClickListener);
				}
				errorText = (TextView) errorLayout.findViewById(R.id.load_layout_error_text);
				errorIv = (ImageView) errorLayout.findViewById(R.id.listview_error_pic);
				addView(errorLayout, new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, Gravity.CENTER));

				showError();
			}
		}
	}

	/**
	 * 设置服务加载失败
	 * 
	 * @param token
	 *            失败服务的Token
	 * @param responseModel
	 *            失败服务的response model
	 */
	public void sendFail(String result) {
		removeProcess();
		showErrorInfo(result);
		if (callBackListener != null && !isDetachFromWindow) {
			callBackListener.businessFail(result);
		}
	}

	private void showErrorInfo(String token) {
		LayoutInflater infalter = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		errorLayout = infalter.inflate(errorLayoutNormalId, null);
		if (errorLayout != null) {
			View refreashBtn = errorLayout.findViewById(R.id.load_layout_refreash_btn);
			if (refreashBtn != null && refreashClickListener != null) {
				refreashBtn.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						refreashClickListener.onClick(v);

					}
				});
			}
			errorText = (TextView) errorLayout.findViewById(R.id.load_layout_error_text);
			if (errorText != null) {
				errorText.setText(token);
			}
			addView(errorLayout, new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, Gravity.CENTER));
			showError();
		}

	}

	/**
	 * 设置服务加载成功
	 * 
	 * @param token
	 *            成功服务的Token
	 * @param responseModel
	 *            成功服务的response model
	 */
	public void sendSuccess(String token) {
		removeProcess();
		if (bHasErrorLayout && errorLayout != null) {
			errorLayout.setVisibility(View.GONE);
		}
		if (callBackListener != null && !isDetachFromWindow) {
			callBackListener.businessSuccess(token);
		}
	}

	/**
	 * 更新加载中的提示文案
	 * 
	 * @param loadingText
	 *            提示文案
	 */
	public void updateLoadingText(String loadingText) {
		if (loadingTextView != null) {
			loadingTextView.setText(loadingText);
		}
	}

	/**
	 * 更新出错时显示的文案
	 * 
	 * @param errorString
	 *            错误文案
	 */
	public void updateErrorText(String errorString) {
		if (errorText != null) {
			errorText.setText(errorString);
		}
	}

	/**
	 * 更新出错时显示的图片
	 * 
	 * @param errorPic
	 *            无哦粗图片的resource id
	 */
	public void updataErrorPic(int errorPic) {
		if (errorIv != null) {
			errorIv.setImageResource(errorPic);
		}
	}

	/**
	 * 设置发送服务结果监听
	 * 
	 * @param listener
	 *            监听对象
	 */
	public void setCallBackListener(LoadingCallBackListener callBackListener) {
		this.callBackListener = callBackListener;
	}

	/**
	 * 设置错误时，点击刷新按钮的响应listener
	 * 
	 * @param listener
	 *            点击监听对象
	 */
	public void setRefreshClickListener(OnClickListener refreashClickListener) {
		this.refreashClickListener = refreashClickListener;
	}

	@Override
	public void bussinessStart() {
		// TODO Auto-generated method stub
		showProcess();
	}

	@Override
	public void bussinessSuccess(String data) {
		// TODO Auto-generated method stub
		sendSuccess(data);
	}

	@Override
	public void bussinessFail(String data) {
		// TODO Auto-generated method stub
		sendFail(data);
	}
}
