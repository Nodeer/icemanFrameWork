package com.iceman.yangtze.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.iceman.yangtze.R;
import com.iceman.yangtze.manager.CustomInputMethodManager;
import com.iceman.yangtze.util.DeviceUtil;

/**
 * 实现标题 + 输入框的式样。 <br/>
 * 标题可以指定必填项 * 指示；输入框为EditText，可点击编辑输入。
 * 
 * @author lbie modify by xuxin at 20131212 version 5.3,
 *         延住订单房间栏，扩展CtripEditableInfoBar
 *         ,不影响现在有的功能模块,使当前自定义bar可以支持选中状态（5.3延住订单有此需求）
 */
public class CtripEditableInfoBar extends LinearLayout {

	private static final int TITLE_DEFAULT_STYLE = R.style.text_15_black;
	private static final int EDITTEXT_DEFAULT_STYLE = R.style.text_16_555555;
	private static final int EDITTEXT_HINT_COLOR = R.color.ui_edit_hint;

	private XsTextView mTitleTextView; // 标题TextView
	private String mTiltleValue; // 标题值
	private boolean bIsNecessary; // 是否是必填项
	private int nTitleStyle = TITLE_DEFAULT_STYLE; // 标题样式

	private CtripEditText mEditText; // Value输入框
	private String mHintValue;// hint值
	private int nEditStyle; // 输入框样式
	private int nEditInputType; // 输入类型
	private int nEditMaxLength; // 输入最大长度
	private int nHintColor = EDITTEXT_HINT_COLOR; // hint颜色值

	@SuppressWarnings("unused")
	private boolean bIsNeedClear; // 是否需要清除功能
	private boolean bHasArrow; // 需要右箭头
	private Drawable mIconDrawable;// icon
	private int nIconHeight = 0;// icon高
	private int nIconWidth = 0;// icon宽
	private int nDrawableDirection = 0;// icon位于title text的方向
	protected int nDrawablePadding;
	/**
	 * 以下4个为 扩展功能 modify at 2013/12/15 by xuxin 不影响之前的功能，增加可以点击，选中（延住订单有此需求）
	 * version 5.3,
	 */
	private SelectCheckRoomListener mTitleViewCallBackListener;// titelview
																// 选择的回调
	// 选择bar,是否可以选择，(因为考虑到只有一个房间号，则不能选择)
	private boolean isCanSelect = true;
	// 当前选中的状态 true :已选中，false：未选中
	private boolean isRadioSelect = false;// 记录当前选择的状态，editview设置为false
	// 选择，回调接口
	/**
	 * @author xu_x add by xuxin 2013/12/15 version 5.3,
	 */
	public interface SelectCheckRoomListener {
		public void sendEventCallBack(CtripEditableInfoBar roomName);// 当前是未选中状态，选择时，要勾选了
		public void unSelectEventCallBack(CtripEditableInfoBar roomName);// 当前是选中状态，取消选中时
	}
	/**
	 * add by xuxin 2013/12/15 version 5.3, 失去焦点则显示这个右边可以编辑图标，提示用户可以编辑
	 * 右边编辑图标可见性，酒店延住订单有此需求 不影响现在功能模块，只是扩展功能
	 */
	private OnFocusChangeListener mCtripEditorFocusChangedListener = new OnFocusChangeListener() {

		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			if (!hasFocus) {
				if (!isRadioSelect) {
					hidenRightEditIcon(View.INVISIBLE);
				}
			} else {
				hidenRightEditIcon(View.VISIBLE);
			}
		}
	};
	/**
	 * 控制右边的清除图标可见性， 酒店延住订单有此需求 add by xuxin 2013/12/15 version 5.3,
	 */
	private TextWatcher mRoomInputTextWatch = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {

		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {

		}

		@Override
		public void afterTextChanged(Editable s) {
			showClearButton(false);
		}
	};
	public CtripEditableInfoBar(Context context) {
		this(context, null);
	}

	public CtripEditableInfoBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		initFromAttributes(context, attrs);
		setupChildViews(context);
	}

	private void initFromAttributes(Context context, AttributeSet attrs) {
		if (attrs != null) {
			TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CtripEditableInfoBar);
			bIsNecessary = ta.getBoolean(R.styleable.CtripEditableInfoBar_editableinfo_is_necessary, false);
			bHasArrow = ta.getBoolean(R.styleable.CtripEditableInfoBar_editableinfo_need_arrow, false);
			bIsNeedClear = ta.getBoolean(R.styleable.CtripEditableInfoBar_editableinfo_need_clear, true);
			nTitleStyle = ta.getResourceId(R.styleable.CtripEditableInfoBar_editableinfo_title_appearance, TITLE_DEFAULT_STYLE);
			mTiltleValue = ta.getString(R.styleable.CtripEditableInfoBar_editableinfo_title_value);
			nEditStyle = ta.getResourceId(R.styleable.CtripEditableInfoBar_editableinfo_edit_appearance, EDITTEXT_DEFAULT_STYLE);
			mHintValue = ta.getString(R.styleable.CtripEditableInfoBar_editableinfo_hint_value);
			nEditInputType = ta.getInt(R.styleable.CtripEditableInfoBar_editableinfo_inputType, InputType.TYPE_CLASS_TEXT);
			nHintColor = ta.getResourceId(R.styleable.CtripEditableInfoBar_editableinfo_hint_color, EDITTEXT_HINT_COLOR);
			nEditMaxLength = ta.getInt(R.styleable.CtripEditableInfoBar_editableinfo_edit_maxLength, -1);
			mIconDrawable = ta.getDrawable(R.styleable.CtripEditableInfoBar_editableinfo_drawable);
			nDrawablePadding = ta.getDimensionPixelSize(R.styleable.CtripEditableInfoBar_editableinfo_drawable_padding, DeviceUtil.getPixelFromDip(8));
			nIconWidth = ta.getDimensionPixelSize(R.styleable.CtripEditableInfoBar_editableinfo_drawable_width, 0);
			nIconHeight = ta.getDimensionPixelSize(R.styleable.CtripEditableInfoBar_editableinfo_drawable_height, 0);
			nDrawableDirection = ta.getInt(R.styleable.CtripEditableInfoBar_editableinfo_drawable_direction, 0);
			ta.recycle();
		} else {
			nDrawablePadding = DeviceUtil.getPixelFromDip(8);
		}
	}

	private void setupChildViews(Context context) {
		setOrientation(LinearLayout.HORIZONTAL);
		setGravity(Gravity.CENTER_VERTICAL);

		mTitleTextView = new XsTextView(context);
		mTitleTextView.setFocusable(true);
		mTitleTextView.setFocusableInTouchMode(true);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
		mTitleTextView.setGravity(Gravity.CENTER_VERTICAL);
		mTitleTextView.setTextAppearance(getContext(), nTitleStyle);
		if (mIconDrawable != null) {
			mTitleTextView.setCompoundDrawable(mIconDrawable, nDrawableDirection, nIconWidth, nIconHeight);
		}
		mTitleTextView.setCompoundDrawablePadding(nDrawablePadding);
		// mTitleTextView.setTypeface(Typeface.DEFAULT_BOLD);
		setTitleText(mTiltleValue);
		addView(mTitleTextView, lp);

		mEditText = new CtripEditText(context);
		mEditText.setEditorHint(mHintValue);
		mEditText.setInputType(nEditInputType);
		mEditText.setEditTextStyle(nEditStyle);
		mEditText.setGravity(Gravity.CENTER_VERTICAL);
		setEditorHintColor(getResources().getColor(nHintColor));
		// 默认不显示输入框
		// mEditText.setBackgroundDrawable(null);
		mEditText.setBackgroundResource(0);
		if (nEditMaxLength != -1) {
			mEditText.setEditorFilters(new InputFilter[]{new InputFilter.LengthFilter(nEditMaxLength)});
		}
		lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f);
		addView(mEditText, lp);

		if (bHasArrow) {
			ImageView arrow = new ImageView(context);
			arrow.setImageResource(R.drawable.icon_arrow);
			lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lp.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
			addView(arrow, lp);
		}
	}

	/**
	 * 设置Title和Edittext的比例
	 * 
	 * @param titleWeight
	 *            Title占宽的比例
	 * @param editWeiht
	 *            Edittext占宽比例
	 */
	public void setLayoutParams(float titleWeight, float editWeiht) {
		LayoutParams labelParams = new LayoutParams(0, LayoutParams.WRAP_CONTENT, titleWeight);
		mTitleTextView.setLayoutParams(labelParams);
		labelParams = new LayoutParams(0, LayoutParams.WRAP_CONTENT, editWeiht);
		mEditText.setLayoutParams(labelParams);
	}

	/**
	 * 
	 * @return 判断该项是否是必填项
	 */
	public boolean isNecessary() {
		return bIsNecessary;
	}

	/**
	 * 设置是否为必填项
	 * 
	 * @param isNecessary
	 *            true-是，false-否
	 */
	public void setIsNecessary(boolean isNecessary) {
		bIsNecessary = isNecessary;
	}

	// public boolean isNeedClear() {
	// return bIsNeedClear;
	// }

	/**
	 * 添加Edittext的文本改变事件监听对象
	 * 
	 *            监听对象
	 */
	public void setEditorWatchListener(TextWatcher textWatcher) {
		mEditText.setEditorWatchListener(textWatcher);
	}

	/**
	 * 设置Edittext的输入类型
	 * 
	 * @param inputType
	 *            输入类型
	 */
	public void setInputType(int inputType) {
		mEditText.setInputType(inputType);
	}

	/**
	 * 设置Title文本样式
	 * 
	 * @param style
	 *            文本式样
	 */
	public void setTitleStyle(int style) {
		mTitleTextView.setTextAppearance(getContext(), style);
	}

	/**
	 * 设置Edittext文本样式
	 * 
	 * @param style
	 *            文本式样
	 */
	public void setEditorTextStyle(int style) {
		mEditText.setEditTextStyle(style);
	}

	/**
	 * 设置Title显示的文本
	 * 
	 * @param resource
	 *            显示文本
	 */
	public void setTitleText(int resource) {
		if (resource != 0) {
			setTitleText(getResources().getString(resource));
		}
	}

	/**
	 * 设置Title显示的文本
	 * 
	 * @param text
	 *            显示文本
	 */
	public void setTitleText(String text) {
		// if (bIsNecessary) {
		// SpannableStringBuilder labelStr = new SpannableStringBuilder("*");
		// labelStr.append(text);
		// labelStr.setSpan(new TextAppearanceSpan(getContext(),
		// R.style.text_14_f85d00), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		// mTitleTextView.setText(labelStr);
		// } else {
		// mTitleTextView.setText(text);
		// }
		if (bIsNecessary) {
			mTitleTextView.setCompoundDrawable(getResources().getDrawable(R.drawable.icon_required), 0, 0, 0);
		}
		mTitleTextView.setText(text);
	}

	/**
	 * 设置Edittext初始显示的文本
	 * 
	 * @param text
	 *            显示文本
	 */
	public void setEditorText(String text) {
		mEditText.setEditorText(text);
	}

	/**
	 * 
	 * @return 获取当前Edittext的输入文本
	 */
	public String getEditorText() {
		return mEditText.getEditorText();
	}

	/**
	 * 清除Edittext输入的文本
	 */
	public void cleanEditorText() {
		mEditText.setEditorText("");
	}

	/**
	 * 设置Edittext的hint文本
	 * 
	 * @param resource
	 *            hint文本
	 */
	public void setEditorHint(int resource) {
		setEditorHint(getResources().getString(resource));
	}

	/**
	 * 设置Edittext的hint文本
	 * 
	 * @param text
	 *            hint文本
	 */
	public void setEditorHint(String text) {
		mEditText.setEditorHint(text);
	}

	/**
	 * 设置Edittext的hint颜色
	 * 
	 * @param color
	 *            颜色值
	 */
	public void setEditorHintColor(int color) {
		mEditText.setEditorHintColor(color);
	}

	/**
	 * 设置输入框外的背景图片
	 * 
	 * @param bg
	 *            背景图片
	 */
	@SuppressWarnings("deprecation")
	public void setEditFrameBg(Drawable bg) {
		mEditText.setBackgroundDrawable(bg);
	}

	/**
	 * 移除Edittext的文本改变事件监听对象
	 * 
	 *            监听对象
	 */
	public void removeEditorWatchListener(TextWatcher textWatcher) {
		if (textWatcher != null) {
			mEditText.removeEditorWatchListener(textWatcher);
		}
	}

	/**
	 * 设置Edittext的文本输入过滤器
	 * 
	 * @param filters
	 *            过滤器数组对象
	 */
	public void setEditorFilters(InputFilter[] filters) {
		if (filters != null) {
			mEditText.setEditorFilters(filters);
		}
	}

	/**
	 * 设置Edittext最大输入长度
	 * 
	 * @param maxLength
	 *            最大长度
	 */
	public void setMaxLength(int maxLength) {
		if (mEditText != null) {
			mEditText.setInputMaxLength(maxLength);
		}
	}

	/**
	 * 设置是否显示title文本view
	 * 
	 * @param isShow
	 *            true - 显示, false - 不显示
	 */
	public void showTitleTextView(boolean isShow) {
		if (mTitleTextView != null) {
			mTitleTextView.setVisibility(isShow ? View.VISIBLE : View.GONE);
		}
	}

	/**
	 * 方法描述 getEditText:获取到CtripEditText，提供给SetActionCode方法
	 * 
	 * @return
	 * @author w_xiong 2013-12-4 For V5.2
	 * @Modify By
	 */
	public CtripEditText getEditText() {
		return mEditText;
	}

	/**
	 * 
	 * @return 获取Edittext控件对象
	 */
	public EditText getmEditText() {
		return mEditText.getmEditText();
	}

	/**
	 * 设置Title部分的显示宽度
	 * 
	 * @param width
	 *            宽度(in pixel format)
	 */
	public void setLabelWidth(int width) {
		LayoutParams labelParams = new LayoutParams(width, LayoutParams.WRAP_CONTENT);
		mTitleTextView.setLayoutParams(labelParams);
		LayoutParams valueParams = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
		mEditText.setLayoutParams(valueParams);
	}

	@Override
	public void clearFocus() {
		if (mEditText != null) {
			View clearBtn = mEditText.findViewById(CtripEditText.ID_CLEAR_BUTTON);
			if (clearBtn != null) {
				clearBtn.setVisibility(View.GONE);
			}
		}
		super.clearFocus();
	}

	public void requestEditFocus() {
		if (mEditText != null) {
			mEditText.requestFocus();

			mEditText.requestFocus();
            CustomInputMethodManager.showSoftInput(mEditText.getmEditText());
			// InputMethodManager imm = (InputMethodManager)
			// (getContext().getSystemService(Context.INPUT_METHOD_SERVICE));
			// imm.showSoftInput(mEditText, InputMethodManager.SHOW_IMPLICIT);

		}
	}

	public void showClearButton(boolean show) {
		mEditText.showClearButton(show);
	}

	/**
	 * 以下代码是扩展功能，增加了此控件可选功能，已经回调监听， （酒店延住需要） add by xuxin 2013/12/15 version
	 * 5.3, 后台代码可以添加
	 * 
	 * @param titleDrawable
	 *            如果只有一间就选中了不可修改
	 */
	public void setTitleViewDrawable(Drawable titleDrawable, boolean selectMe) {
		mTitleTextView.setFocusable(false);
		mTitleTextView.setFocusableInTouchMode(false);
		getmEditText().setEnabled(selectMe ? true : false);// 选择可编辑，不选择就不能编辑
		isRadioSelect = selectMe;// 记录当前是否选择状态
		if (selectMe) {// 第一间为选中
			setTitleAndValueStyle(R.style.text_15_1084bc, R.style.text_14_1084bc);
		} else {
			// 可选但是不选择，是333333，在后面有设置不能选择时的颜色titlecccccc
			setTitleAndValueStyle(R.style.text_15_333333, R.style.text_14_333333);
		}
		showClearButton(false);
		mEditText.getmEditText().addTextChangedListener(mRoomInputTextWatch);
		mEditText.getmEditText().setOnFocusChangeListener(mCtripEditorFocusChangedListener);// 失去焦点，显示右边的编辑小图标
		setRightEditIcon(getResources().getDrawable(R.drawable.icon_common_edit));// 右边的图标
		hidenRightEditIcon(selectMe ? View.VISIBLE : View.INVISIBLE);
		if (titleDrawable != null) {
			mTitleTextView.setCompoundDrawable(titleDrawable, 0, nIconWidth, nIconHeight);
			mTitleTextView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// CtripActionLogUtil.logCode("ClickCode");//475
					// TODO Auto-generated method stub
					if (isCanSelect) {// 可以选择
						if (isRadioSelect) {// 取消勾选
							if (mTitleViewCallBackListener != null) {
								mTitleViewCallBackListener.unSelectEventCallBack(CtripEditableInfoBar.this);// 如果当前有些房间号是不能选择的，这是取消选择就可以重置为可选了
							}
						} else {// 开始选择，勾选
							if (mTitleViewCallBackListener != null) {
								mTitleViewCallBackListener.sendEventCallBack(CtripEditableInfoBar.this);
							}
						}
					}
				}
			});
		}
	}
	/**
	 * 设置状态
	 * 
	 * @param titleStyle
	 * @param valueStyle
	 *            add by xuxin 2013/12/15 version 5.3,
	 */
	public void setTitleAndValueStyle(int titleStyle, int valueStyle) {
		setTitleStyle(titleStyle);
		getmEditText().setTextAppearance(getContext(), valueStyle);
		getmEditText().setHintTextColor(getResources().getColor(R.color.ui_edit_hint));
	}
	/**
	 * 设置此行是否可以选择， (因为延住订单有需求，一行不可以修改选择)
	 * 
	 */
	public void setCanSelect(boolean mIsCanSelect) {
		isCanSelect = mIsCanSelect;
		if (!mIsCanSelect) {// 不能选择则不能编辑，否则可以选择，就可编辑房间号
			mEditText.getmEditText().setEnabled(false);// 1间不可修改
		}
	}
	/**
	 * 获取当前的inforbar是否可选
	 * 
	 * @return add xuxin 2013/12/18
	 */
	public boolean getCanSelect() {
		return isCanSelect;
	}
	/**
	 * 获取当前的inforbar的状态是否是选中
	 * 
	 * @return add xuxin 2013/12/18
	 */
	public boolean getCurrentSelectStatus() {
		return isRadioSelect;
	}
	/**
	 * 设置当前的inforbar的状态是否是选中 add by xuxin 2013/12/15 version 5.3,
	 */
	public void setCurrentSelectStatus(boolean isSelect) {
		isRadioSelect = isSelect;
	}
	/**
	 * 添加右边的部分 * add xuxin 2013/12/18
	 * 
	 *            For V5.3
	 * @param rightEidtIcon
	 */
	public void setRightEditIcon(Drawable rightEidtIcon) {
		DisplayMetrics dm = getResources().getDisplayMetrics();
		if (rightEidtIcon != null) {
			ImageView arrow = new ImageView(getContext());
			arrow.setVisibility(View.INVISIBLE);// 初始化为GONE
			arrow.setImageDrawable(rightEidtIcon);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lp.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
			lp.rightMargin = DeviceUtil.getPixelFromDip(10);
			addView(arrow, lp);
		}
	}
	/**
	 * add by xuxin 20131212 /17:30 For V5.3 设置右边编辑图标 就是一个可编辑的提示图标没啥动作
	 * 
	 * 如果有下发的数据房间好，则显示，否则用户输入编辑完后显示
	 */
	public void hidenRightEditIcon(int visibleVar) {
		for (int i = 0; i < getChildCount(); i++) {//
			View findView = getChildAt(i);
			if (findView instanceof ImageView) {
				((ImageView) findView).setVisibility(visibleVar);
			}
		}
	}

	/**
	 * modify by xuxin 20131212 /17:30 设置textview不可点击选择, 延住订单使用 add by xuxin
	 * 2013/12/15 For V5.3
	 */
	public void setEnable() {
		mEditText.getmEditText().setEnabled(false);
		mTitleTextView.setEnabled(false);
	}
	/**
	 * 选择了对应的，回调在各自页面处理 modify by xuxin 20131212 /17:30 For V5.3
	 * 
	 * @param callBackListener
	 */
	public void setSelectTitleViewListener(SelectCheckRoomListener callBackListener) {
		mTitleViewCallBackListener = callBackListener;
	}
	/**
	 * 方法描述 getTitleTextView:获取到mTitleTextView,共延住房间选择使用
	 * 
	 * @return
	 * @author xuxin 2013-12-13 For V5.3
	 * @Modify By
	 */
	public XsTextView getTitleTextView() {
		return mTitleTextView;
	}

}