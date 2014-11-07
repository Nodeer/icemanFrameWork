package com.iceman.yangtze.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iceman.yangtze.R;
import com.iceman.yangtze.util.DeviceUtil;
import com.iceman.yangtze.util.StringUtil;

/**
 * <p>
 * 封装Title控件，具体显示效果如下：
 * <p>
 * -----------------------------------------
 * </p>
 * <p>
 * LeftButton Title RightButton
 * </p>
 * <p>
 * -----------------------------------------
 * </p>
 * <p>
 * Left Button - title左侧按钮
 * </p>
 * <p>
 * Title - 显示的title文字
 * </p>
 * <p>
 * Right Button - title右侧按钮
 * </p>
 */
public class XsTitleView extends android.widget.RelativeLayout implements View.OnClickListener {
	/** 标题栏文字 */
	public static final int TITLE_TEXT_DEFAULT_STYLE = R.style.text_18_ffffff;
	/** 标题栏文字多行 */
	public static final int TITLE_TEXT_DEFAULT_STYLE_SMALL = R.style.text_14_ffffff;
	/** 标题文字左右padding */
	public static final int PADDING_TITLE = 70;
	/** 标题栏按钮宽度 */
	public static final int BTN_DEFAULT_WIDTH = 55;
	@SuppressWarnings("unused")
	private static final int BTN_DEFAULT_HEIGHT = 34;
	/* ID */
	private static final int ID_LEFT_BUTTON = 0x1001;
	private static final int ID_RIGHT_BUTTON = 0x1002;
	/** 右侧按钮文字样式 */
	private static final int BTN_TEXT_DEFAULT_STYLE = R.style.text_14_ffffff;
	/** 右侧按钮小文字样式 */
	private static final int BTN_TEXT_SMALL_STYLE = R.style.text_12_ffffff;
	private static final int LEFT_BTN_DEFAULT_BG = R.drawable.btn_title_left_bg_selector;
	private static final int RIGHT_BTN_DEFAULT_BG = R.drawable.btn_title_right_bg_selector;

	private static final int BTN_ICON_DEFAULT_SIZE = 22;
	private static final int RIGHT_BTN_ICON_DEFAULT_MARGIN = 15;

	// 中间Title TextView
	private TextView mTitleTextView;
	private String mTitleText;
	private int nTextStyleId;

	// 两侧Button View
	private int nBtnTextStyleId;

	// 左侧Button View
	private boolean bShowLeftBtn;
	private View mLeftBtnView;
	private String mBtnLeftText;
	private Drawable mBtnLeftBgDrawable;
	private Drawable mBtnLeftDrawable;

	@SuppressWarnings("unused")
	private int nBtnLeftWidth;
	@SuppressWarnings("unused")
	private int nBtnLeftHeight;

	// 右侧Button View
	private boolean bShowRightBtnBg;
	private View mRightBtnView;
	private String mBtnRightText;
	private Drawable mBtnRightDrawable;
	private Drawable mBtnBgDrawable;

	private int nBtnRightWidth;// 右侧按钮大小，默认值为BTN_ICON_DEFAULT_SIZE，背景强制设为BTN_DEFAULT_WIDTH。
	private int nBtnRightHeight;// 右侧按钮大小，默认值为BTN_ICON_DEFAULT_SIZE，背景强制设为LayoutParams.MATCH_PARENT
	private int nBtnTextPadding;

	@SuppressWarnings("unused")
	private boolean bShowShadow;
	/** 标题宽度 */
	private int nTitleWidth;

	private OnTitleClickListener mOnTitleClickListener;

	public interface OnTitleClickListener {
		void onLogoClick(View v);

		void onButtonClick(View v);
	}

	public XsTitleView(Context context) {
		this(context, null);
	}

	public XsTitleView(Context context, AttributeSet attrs) {
		super(context, attrs);

		initFromAttributes(context, attrs);
		setupChildViews(context);
	}

	private void initFromAttributes(Context context, AttributeSet attrs) {
		if (attrs != null) {
			TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.XsTitleView);

			mTitleText = a.getString(R.styleable.XsTitleView_title_text);
			nTextStyleId = a.getResourceId(R.styleable.XsTitleView_title_text_appearance, TITLE_TEXT_DEFAULT_STYLE);

			bShowLeftBtn = a.getBoolean(R.styleable.XsTitleView_title_show_left_btn, true);
			if (bShowLeftBtn) {
				mBtnLeftText = a.getString(R.styleable.XsTitleView_title_btn_left_text);

				nBtnLeftWidth = a.getDimensionPixelSize(R.styleable.XsTitleView_title_btn_left_width, 0);
				nBtnLeftHeight = a.getDimensionPixelSize(R.styleable.XsTitleView_title_btn_left_height, 0);

				mBtnLeftDrawable = a.getDrawable(R.styleable.XsTitleView_title_btn_left_drawable);
				if (mBtnLeftDrawable == null) {
					mBtnLeftDrawable = getResources().getDrawable(R.drawable.btn_back_arrow);
				}
				mBtnLeftBgDrawable = a.getDrawable(R.styleable.XsTitleView_title_btn_left_bg);
				if (mBtnLeftBgDrawable == null) {
					mBtnLeftBgDrawable = getResources().getDrawable(LEFT_BTN_DEFAULT_BG);
				}
			}

			bShowRightBtnBg = a.getBoolean(R.styleable.XsTitleView_title_show_right_btn_bg, false);
			mBtnRightText = a.getString(R.styleable.XsTitleView_title_btn_text);
			mBtnRightDrawable = a.getDrawable(R.styleable.XsTitleView_title_btn_drawable);
			final DisplayMetrics dm = context.getResources().getDisplayMetrics();
			int nDefaultIconSize = DeviceUtil.getPixelFromDip(BTN_ICON_DEFAULT_SIZE);
			nBtnRightWidth = a.getDimensionPixelSize(R.styleable.XsTitleView_title_btn_width, nDefaultIconSize);
			nBtnRightHeight = a.getDimensionPixelSize(R.styleable.XsTitleView_title_btn_height, nDefaultIconSize);
			nBtnTextPadding = a.getDimensionPixelSize(R.styleable.XsTitleView_title_btn_text_padding, 0);

			nBtnTextStyleId = a.getResourceId(R.styleable.XsTitleView_title_btn_text_appearance, -1);
			mBtnBgDrawable = a.getDrawable(R.styleable.XsTitleView_title_btn_bg);

			bShowShadow = a.getBoolean(R.styleable.XsTitleView_title_bg_show_shadow, true);

			a.recycle();

			// 设置默认背景图
			if (getBackground() == null) {
				// setBackgroundResource(bShowShadow ?
				// R.drawable.bar_title_shadow : R.drawable.bar_title);
				setBackgroundResource(R.drawable.pic_titlebar);
			}
		} else {
			bShowShadow = false;
			// setBackgroundResource(R.drawable.bar_title);
			setBackgroundResource(R.drawable.pic_titlebar);

			mTitleText = null;
			nTextStyleId = TITLE_TEXT_DEFAULT_STYLE;

			mBtnLeftText = null;
			mBtnLeftBgDrawable = getResources().getDrawable(LEFT_BTN_DEFAULT_BG);
			mBtnRightText = null;
			mBtnBgDrawable = getResources().getDrawable(RIGHT_BTN_DEFAULT_BG);
			nBtnRightWidth = LayoutParams.WRAP_CONTENT;
			nBtnRightHeight = LayoutParams.WRAP_CONTENT;
			nBtnTextStyleId = -1;
		}
	}

	@SuppressWarnings("deprecation")
	private void setupChildViews(Context context) {
		final DisplayMetrics dm = context.getResources().getDisplayMetrics();

		// Create title_text
		TextView titleView = new TextView(context);
		titleView.setGravity(Gravity.CENTER);
		/* modify reason 标题过长折行小字显示 */
		// titleView.setSingleLine(true);
		// titleView.setText(mTitleText);
		titleView.setEllipsize(TextUtils.TruncateAt.END);
		titleView.setTextAppearance(context, nTextStyleId);
		titleView.setClickable(false);

		nTitleWidth = dm.widthPixels - DeviceUtil.getPixelFromDip(PADDING_TITLE) * 2;
		LayoutParams lp = new LayoutParams(nTitleWidth, LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		addView(titleView, lp);
		mTitleTextView = titleView;
		if (!StringUtil.emptyOrNull(mTitleText)) {
			setTitleText(mTitleText);
		}
		// Create title_left_button
		int leftBtnWidth = LayoutParams.WRAP_CONTENT;
		int leftBtnHeight = LayoutParams.MATCH_PARENT;

		if (bShowLeftBtn) {
			if (TextUtils.isEmpty(mBtnLeftText)) {
				View btnImgView = new View(context);

				if (mBtnLeftDrawable == null) {
					mBtnLeftDrawable = getResources().getDrawable(R.drawable.btn_back_arrow);
				}
				btnImgView.setBackgroundDrawable(mBtnLeftDrawable);

				int iconSize = DeviceUtil.getPixelFromDip(BTN_ICON_DEFAULT_SIZE);

				FrameLayout containerLayout = new FrameLayout(context);
				if (mBtnLeftBgDrawable == null) {
					mBtnLeftBgDrawable = getResources().getDrawable(LEFT_BTN_DEFAULT_BG);
				}
				containerLayout.setBackgroundDrawable(mBtnLeftBgDrawable);

				FrameLayout.LayoutParams subLp = new FrameLayout.LayoutParams(iconSize, iconSize);
				subLp.gravity = Gravity.CENTER;
				containerLayout.addView(btnImgView, subLp);

				leftBtnWidth = DeviceUtil.getPixelFromDip(BTN_DEFAULT_WIDTH);
				mLeftBtnView = containerLayout;
			} else {
				TextView btnLeftView = new TextView(context);
				btnLeftView.setId(ID_LEFT_BUTTON);
				btnLeftView.setGravity(Gravity.CENTER);
				btnLeftView.setSingleLine(true);
				btnLeftView.setEllipsize(TextUtils.TruncateAt.END);
				btnLeftView.setText(mBtnLeftText);
				if (nBtnTextStyleId != -1) {
					// 用户自定义style
					btnLeftView.setTextAppearance(context, nBtnTextStyleId);
				} else {
					btnLeftView.setTextAppearance(context, BTN_TEXT_DEFAULT_STYLE);
				}

				if (mBtnLeftBgDrawable == null) {
					mBtnLeftBgDrawable = context.getResources().getDrawable(LEFT_BTN_DEFAULT_BG);
					btnLeftView.setBackgroundDrawable(mBtnLeftBgDrawable);
				}

				mLeftBtnView = btnLeftView;
			}
		} else {
			mLeftBtnView = null;
		}

		if (mLeftBtnView != null) {
			mLeftBtnView.setId(ID_LEFT_BUTTON);
			mLeftBtnView.setClickable(true);
			mLeftBtnView.setOnClickListener(this);

			lp = new LayoutParams(leftBtnWidth, leftBtnHeight);
			lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
			lp.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
			addView(mLeftBtnView, lp);
		}

		// Create title_right_button
		int rightMarginPixels = 0;
		if (TextUtils.isEmpty(mBtnRightText) && mBtnRightDrawable != null) {
			View btnImgView = new View(context);
			btnImgView.setBackgroundDrawable(mBtnRightDrawable);
			if (bShowRightBtnBg) {
				// 需要显示背景，传入的右侧按钮大小设置给image，背景大小设为默认值
				int iconHeight = nBtnRightHeight;
				int iconWidth = nBtnRightWidth;

				FrameLayout containerLayout = new FrameLayout(context);
				if (mBtnBgDrawable == null) {
					mBtnBgDrawable = getResources().getDrawable(RIGHT_BTN_DEFAULT_BG);
				}
				containerLayout.setBackgroundDrawable(mBtnBgDrawable);

				FrameLayout.LayoutParams subLp = new FrameLayout.LayoutParams(iconWidth, iconHeight);
				subLp.gravity = Gravity.CENTER;
				containerLayout.addView(btnImgView, subLp);

				nBtnRightWidth = DeviceUtil.getPixelFromDip(BTN_DEFAULT_WIDTH);
				nBtnRightHeight = LayoutParams.MATCH_PARENT;
				mRightBtnView = containerLayout;
			} else {
				// 不用背景的image需要设置margin值
				rightMarginPixels = DeviceUtil.getPixelFromDip(RIGHT_BTN_ICON_DEFAULT_MARGIN);
				mRightBtnView = btnImgView;
			}
		} else if (!TextUtils.isEmpty(mBtnRightText)) {
			// TextView btnTextView = new TextView(context);
			// btnTextView.setGravity(Gravity.CENTER);
			// btnTextView.setPadding(nBtnTextPadding, 0, nBtnTextPadding, 0);
			// btnTextView.setSingleLine(true);
			// btnTextView.setEllipsize(TextUtils.TruncateAt.END);
			// btnTextView.setText(mBtnRightText);
			// btnTextView.setTextAppearance(context, nBtnTextStyleId);
			// btnTextView.setTextColor(getResources().getColorStateList(R.color.title_btn_comm_text_selector));
			//
			// if (mBtnBgDrawable == null) {
			// mBtnBgDrawable =
			// context.getResources().getDrawable(RIGHT_BTN_DEFAULT_BG);
			// nBtnRightWidth = DeviceUtil.getPixelFromDip(dm,
			// BTN_DEFAULT_WIDTH);
			// nBtnRightHeight = LayoutParams.MATCH_PARENT;
			// }
			//
			// btnTextView.setBackgroundDrawable(mBtnBgDrawable);
			//
			// mRightBtnView = btnTextView;
			setTitleButtonText(mBtnRightText);
		}

		if (mRightBtnView != null && mRightBtnView.getParent() == null) {
			mRightBtnView.setId(ID_RIGHT_BUTTON);
			mRightBtnView.setClickable(true);
			mRightBtnView.setOnClickListener(this);
			lp = new LayoutParams(nBtnRightWidth, nBtnRightHeight);
			lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
			lp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
			lp.rightMargin = rightMarginPixels;
			addView(mRightBtnView, lp);
		}
	}

	@Override
	public void onClick(View v) {

		// CtripActionLogUtil.logCode("ClickCode");//329
		switch (v.getId()) {
			case ID_LEFT_BUTTON :
				if (mOnTitleClickListener != null) {
					mOnTitleClickListener.onLogoClick(v);
				}
				sendKeyBackEvent();
				break;
			case ID_RIGHT_BUTTON :
				if (mOnTitleClickListener != null) {
					mOnTitleClickListener.onButtonClick(v);
				}
				break;
			default :
				// do nothing
				break;
		}
	}

	private void sendKeyBackEvent() {
		final Context context = getContext();
		if (context instanceof Activity) {
			KeyEvent keyEvent = new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK);
			((Activity) context).onKeyDown(KeyEvent.KEYCODE_BACK, keyEvent);
		}
	}

	/**
	 * 设置控件点击响应的通知listener
	 * 
	 * @param listener
	 *            响应事件对象
	 */
	public void setOnTitleClickListener(OnTitleClickListener listener) {
		mOnTitleClickListener = listener;
	}

	/**
	 * 设置title显示的文本
	 * 
	 * @param resource
	 *            显示文本的resource id
	 */
	public void setTitleText(int resource) {
		if (resource != 0) {
			setTitleText(getResources().getString(resource));
		} else {
			setTitleText(null);
		}
	}

	/**
	 * 设置title显示的文本 Modify For V5.1 by DingJunYing 需求编号511202超长用小字显示
	 * 
	 * @param text
	 *            显示的Title文本
	 */
	public void setTitleText(CharSequence text) {
		if (text == null) {
			return;
		}
		if (isOverTextView(text.toString(), mTitleTextView, nTitleWidth)) {
			// 超长的标题用小字显示
			mTitleTextView.setTextAppearance(getContext(), TITLE_TEXT_DEFAULT_STYLE_SMALL);
			if (isOverTextView(text.toString(), mTitleTextView, nTitleWidth)) {
				// 小字多行显示的左对齐
				mTitleTextView.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
			} else {
				// 小字一行显示的居中
				mTitleTextView.setGravity(Gravity.CENTER);
			}
		} else {
			mTitleTextView.setTextAppearance(getContext(), TITLE_TEXT_DEFAULT_STYLE);
			mTitleTextView.setGravity(Gravity.CENTER);
		}
		mTitleTextView.setText(text);
	}

	/**
	 * 设置title显示的最大行数
	 * 
	 * @param lines
	 *            显示文本的行数
	 */
	public void setTitleTextMultiLine(int lines) {
		if (lines <= 0 || mTitleTextView == null) {
			return;
		}

		if (lines > 1) {
			mTitleTextView.setSingleLine(false);
			mTitleTextView.setMaxLines(lines);
		} else {
			mTitleTextView.setSingleLine(true);
		}
		mTitleTextView.setEllipsize(TextUtils.TruncateAt.END);
	}

	/**
	 * 设置title left button显示的文本
	 * 
	 * @param resource
	 *            显示文本的resource id
	 * @description 如果使用者将title button替换为自定义的view，则此接口无效
	 */
	public void setTitleLeftButtonText(int resource) {
		if (resource != 0) {
			setTitleLeftButtonText(getResources().getString(resource));
		} else {
			setTitleLeftButtonText(null);
		}
	}

	/**
	 * 设置title left button显示的文本
	 * 
	 * @param text
	 *            显示的文本
	 * @description 如果使用者将title button替换为自定义的view，则此接口无效
	 */
	@SuppressWarnings("deprecation")
	public void setTitleLeftButtonText(CharSequence text) {
		if (mLeftBtnView instanceof TextView) {
			TextView textView = (TextView) mLeftBtnView;
			textView.setText(text);
			if (text == null || text.length() == 0) {
				textView.setOnClickListener(null);
				textView.setBackgroundDrawable(null);
			} else {
				textView.setOnClickListener(this);
				if (mBtnBgDrawable != null) {
					textView.setBackgroundDrawable(mBtnBgDrawable);
				}
			}
		}
	}

	/**
	 * 设置title right button显示的文本
	 * 
	 * @param resource
	 *            显示文本的resource id
	 * @description 如果使用者将title button替换为自定义的view，则此接口无效
	 */
	public void setTitleButtonText(int resource) {
		if (resource != 0) {
			setTitleButtonText(getResources().getString(resource));
		} else {
			setTitleButtonText(null);
		}
	}

	/**
	 * 设置title right button显示的文本
	 * 
	 * @param text
	 *            显示文本
	 * @description 如果使用者将title button替换为自定义的view，则此接口无效
	 */
	@SuppressWarnings("deprecation")
	public void setTitleButtonText(CharSequence text) {
		if (text == null || text.length() == 0) {
			return;
		}
		final DisplayMetrics dm = getResources().getDisplayMetrics();
		if (mRightBtnView == null) {
			TextView textView = new TextView(getContext());
			textView.setGravity(Gravity.CENTER);
			textView.setPadding(nBtnTextPadding, 0, nBtnTextPadding, 0);
			textView.setSingleLine(true);
			textView.setEllipsize(TextUtils.TruncateAt.END);
			textView.setId(ID_RIGHT_BUTTON);
			// textView.setTextAppearance(getContext(), nBtnTextStyleId);
			textView.setTextColor(getResources().getColorStateList(R.color.title_btn_comm_text_selector));
			textView.setClickable(true);

			mRightBtnView = textView;
			nBtnRightWidth = DeviceUtil.getPixelFromDip(BTN_DEFAULT_WIDTH);
			nBtnRightHeight = LayoutParams.MATCH_PARENT;

			mRightBtnView.setOnClickListener(this);
			LayoutParams lp = new LayoutParams(nBtnRightWidth, nBtnRightHeight);
			lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
			lp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
			addView(mRightBtnView, lp);
		}

		if (mRightBtnView instanceof TextView) {
			TextView textView = (TextView) mRightBtnView;
			// 去除系统控件文字边距
			textView.setIncludeFontPadding(false);
			/* Modify by DingJunYing for v5.1 reason UI需求 */
			// 设置按钮文字样式
			if (nBtnTextStyleId == -1) {
				// 用户没有自定义样式
				// 正常显示使用默认字体
				textView.setTextAppearance(getContext(), BTN_TEXT_DEFAULT_STYLE);
				// 文字过长使用小号字体
				if (isOverTextView(text.toString(), textView, nBtnRightWidth)) {
					textView.setTextAppearance(getContext(), BTN_TEXT_SMALL_STYLE);
				}
			} else {
				// 用户定义了按钮文字样式
				textView.setTextAppearance(getContext(), nBtnTextStyleId);
			}
			textView.setText(text);

			textView.setOnClickListener(null);
			textView.setBackgroundDrawable(null);
			textView.setOnClickListener(this);
			if (mBtnBgDrawable == null) {
				mBtnBgDrawable = getResources().getDrawable(RIGHT_BTN_DEFAULT_BG);
				nBtnRightWidth = DeviceUtil.getPixelFromDip(BTN_DEFAULT_WIDTH);
				nBtnRightHeight = LayoutParams.MATCH_PARENT;
				LayoutParams lp = (LayoutParams) textView.getLayoutParams();
				lp.width = nBtnRightWidth;
				lp.height = nBtnRightHeight;
				textView.setLayoutParams(lp);
			}
			textView.setBackgroundDrawable(mBtnBgDrawable);
		}
	}

	/**
	 * 设置title right button显示的图片
	 * 
	 * @param resource
	 *            显示图片的resource id
	 * @description 如果使用者将title button替换为自定义的view，则此接口无效
	 */
	public void setTitleButtonDrawable(int resource) {
		if (resource != 0) {
			setTitleButtonDrawable(getResources().getDrawable(resource));
		} else {
			setTitleButtonDrawable(null);
		}
	}

	/**
	 * 设置title right button显示的图片
	 * 
	 * @param drawable
	 *            显示的图片
	 * @description 如果使用者将title button替换为自定义的view，则此接口无效
	 */
	@SuppressWarnings("deprecation")
	private void setTitleButtonDrawable(Drawable drawable) {
		if (mRightBtnView instanceof TextView) {
			TextView textView = (TextView) mRightBtnView;
			textView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
			if (mBtnBgDrawable != null) {
				textView.setBackgroundDrawable(mBtnBgDrawable);
			}
		} else if (mRightBtnView instanceof ImageView) {
			ImageView imageView = (ImageView) mRightBtnView;
			imageView.setImageDrawable(drawable);
			if (mBtnBgDrawable != null && imageView.getBackground() == null) {
				imageView.setBackgroundDrawable(mBtnBgDrawable);
			}
		}
	}

	/**
	 * 设置title right button的背景
	 * 
	 * @param resource
	 *            显示背景的resource id
	 * @description 如果使用者将title button替换为自定义的view，则此接口可能无效
	 */
	public void setTitleButtonBackground(int resource) {
		if (resource != 0) {
			setTitleButtonBackground(getResources().getDrawable(resource));
		} else {
			setTitleButtonDrawable(null);
		}
	}

	/**
	 * 设置title right button的背景
	 * 
	 * @param drawable
	 *            显示的背景图片
	 * @description 如果使用者将title button替换为自定义的view，则此接口可能无效
	 */
	@SuppressWarnings("deprecation")
	public void setTitleButtonBackground(Drawable drawable) {
		if (mRightBtnView instanceof TextView || mRightBtnView instanceof ImageView) {
			if (mBtnBgDrawable != null) {
				mBtnBgDrawable.setCallback(null);
				mBtnBgDrawable = null;
			}
			mBtnBgDrawable = drawable;
			mRightBtnView.setBackgroundDrawable(drawable);
			if (drawable != null) {
				int width = mRightBtnView.getMeasuredWidth();
				if (width > 0) {
					LayoutParams lp = (LayoutParams) mRightBtnView.getLayoutParams();
					lp.width = width;
					mRightBtnView.setLayoutParams(lp);
				}
			}
		}
	}

	/**
	 * 设置title right button对象是否有效
	 * 
	 * @param enable
	 *            true有效， false无效
	 */
	public void setTitleButtonEnable(boolean enable) {
		mRightBtnView.setEnabled(enable);
	}

	/**
	 * 设置title right button对象是否显示
	 * 
	 * @param visible
	 *            true显示， false不显示
	 */
	public void setTitleButtonVisible(boolean visible) {
		setTitleButtonVisible(visible, false);
	}

	/**
	 * 设置title right button对象是否显示，并是否附加动画效果
	 * 
	 * @param visible
	 *            true显示， false不显示
	 * @param withAnimaion
	 *            true附带动画， false不附带动画
	 */
	public void setTitleButtonVisible(boolean visible, boolean withAnimaion) {
		if (visible) {
			if (mRightBtnView.getVisibility() != VISIBLE && withAnimaion) {
				mRightBtnView.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
			}
			mRightBtnView.setVisibility(View.VISIBLE);
		} else {
			if (mRightBtnView.getVisibility() == VISIBLE && withAnimaion) {
				mRightBtnView.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_out));
			}
			mRightBtnView.setVisibility(View.GONE);
		}
	}
	public void setLeftButtonVisible(boolean visible) {
		if (visible) {
			mLeftBtnView.setVisibility(View.VISIBLE);
		} else {
			mLeftBtnView.setVisibility(View.GONE);
		}
	}

	/**
	 * 提供一个自定义的view对象, 替换显示Title Button对象
	 * 
	 * @param view
	 *            需要替换的view对象
	 * @description view对象内部属性需自行设置
	 */
	public void setTitleBtnView(View view) {
		setTitleBtnView(view, 0, 0);
	}

	/**
	 * 提供一个自定义的view对象, 并替换显示Title Button对象 可指定view对象的显示宽高
	 * 
	 * @param view
	 *            需要替换的view对象
	 * @param width
	 *            view对象的显示宽度，单位dip
	 * @param height
	 *            view对象的显示高度，单位dip
	 * @description view对象内部属性需自行设置
	 */
	public void setTitleBtnView(View view, float width, float height) {
		// Remove Title Button first
		if (mRightBtnView != null) {
			mRightBtnView.setOnClickListener(null);
			removeView(mRightBtnView);
			mRightBtnView = null;
		}

		if (view == null) {
			return;
		}

		// final int marginPixels =
		// DeviceUtil.getPixelFromDip(getResources().getDisplayMetrics(),
		// BUTTON_MARGIN);
		final int widthPixels = DeviceUtil.getPixelFromDip(width);
		final int heightPixels = DeviceUtil.getPixelFromDip(height);
		LayoutParams lp = new LayoutParams(widthPixels, heightPixels);
		lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
		lp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
		// lp.rightMargin = marginPixels;
		addView(view, lp);

		view.setOnClickListener(this);
		mRightBtnView = view;
	}

	/**
	 * 标题中间两个按键使用
	 * 
	 * @param view
	 * @author chyan
	 */
	public void setTitleView(View view) {
		LayoutParams lParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		lParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
		lParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
		addView(view, lParams);
	}

	/**
	 * 是否超出显示范围
	 * 
	 * @param text
	 *            写入的string
	 * @param textView
	 *            TextView控件
	 * @param len
	 *            控件宽度dip
	 * @return
	 */
	private boolean isOverTextView(String text, TextView textView, int len) {
		TextPaint textPaint = textView.getPaint();
		int needWidth = (int) textPaint.measureText(text.toString());
		if (needWidth >= len) {
			return true;
		} else {
			return false;
		}
	}

	public static class SimpleTitleClickListener implements OnTitleClickListener {

		@Override
		public void onLogoClick(View v) {

		}

		@Override
		public void onButtonClick(View v) {

		}
	}
}