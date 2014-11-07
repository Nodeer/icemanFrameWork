package com.iceman.yangtze.widget;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.iceman.yangtze.R;
import com.iceman.yangtze.util.DeviceUtil;

/**
 * 
 * <p>
 * 封装InfoBar功能，用于显示信息Logo + 标题 + 内容 + Arrow的式样，具体显示效果如下：
 * </p>
 * <p>
 * ----------------------------------------
 * </p>
 * <p>
 * Logo *Label Value Arrow
 * </p>
 * <p>
 * ----------------------------------------
 * </p>
 * <p>
 * Logo - 需要显示的logo图片
 * </p>
 * <p>
 * Label - 标签文字，可设置必选项标识 "*"
 * </p>
 * <p>
 * Value - 当前设置/回显的内容，可设置特定的格式以支持多行显示
 * </p>
 * <p>
 * Arrow - 为箭头图片，默认向右
 * </p>
 * <p>
 * Value内容部分自适应显示宽度，剩余空间由Label标题部分自适应占满。
 * </p>
 * 
 */
public class InfoBar extends LinearLayout {
	private Drawable mIconDrawable;// icon
	private int nIconHeight = 0;// 图片高
	private int nIconWidth = 0;// 图片宽
	protected static final int ARROW_DIRECTION_RIGHT = 0;
	protected static final int ARROW_DIRECTION_BOTTOM = 1;
	protected static final int ARROW_DIRECTION_UP = 2;

	protected int nArrowDirection;// 箭头方向 0向右 1向下

	/**
	 * 显示Label标题的子控件
	 */
	protected XsTextView mLabelText;
	/**
	 * 显示Value内容的子控件
	 */
	protected XsTextView mValueText;

	// 默认值
	/**
	 * Label控件的默认Text式样
	 * 
	 * @see {@link #mLabelText}
	 */
	protected int nLabelDefaultStyle = R.style.text_15_black;
	/**
	 * Value控件的默认Text式样
	 * 
	 * @see {@link #mValueText}
	 */
	protected int nValueDefaultStyle = R.style.text_15_black;
	/**
	 * Value控件的默认Text Hint颜色
	 * 
	 * @see {@link #mValueText}
	 */
	protected int nHintColorDefault = R.color.ui_edit_hint;
	/**
	 * 用于设置Label控件内部，Logo图片与显示文字之间的空隙
	 * 
	 * @see XsTextView#setCompoundDrawablePadding(int)
	 */
	protected int nDrawablePadding;

	public InfoBar(Context context) {
		this(context, null);
	}

	public InfoBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public InfoBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs);

		setOrientation(LinearLayout.HORIZONTAL);
		setGravity(Gravity.CENTER_VERTICAL);

		// 添加2个textview，设置固定参数
		createChildViews();

		initFromAttributes(context, attrs, defStyle);
		setEnabled(true);
		setClickable(true);
		setFocusable(true);
	}

	private void initFromAttributes(Context context, AttributeSet attrs, int defStyle) {
		// 通过xml style设置默认参数
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.InfoBar);

		if (a != null) {
			nDrawablePadding = a.getDimensionPixelSize(R.styleable.InfoBar_infobar_drawable_padding, 0);
			mIconDrawable = a.getDrawable(R.styleable.InfoBar_infobar_drawable);
			nIconWidth = a.getDimensionPixelSize(R.styleable.InfoBar_infobar_icon_width, 0);
			nIconHeight = a.getDimensionPixelSize(R.styleable.InfoBar_infobar_icon_height, 0);
			setIconDrawable(mIconDrawable, nIconWidth, nIconHeight);

			String labelText = a.getString(R.styleable.InfoBar_infobar_title_text);
			boolean isMandatory = a.getBoolean(R.styleable.InfoBar_isMandatory, false);
			setLabelText(labelText, isMandatory);

			int labelStyleId = a.getResourceId(R.styleable.InfoBar_infobar_title_text_appearance, nLabelDefaultStyle);
			setLabelStyle(labelStyleId);

			String valueText = a.getString(R.styleable.InfoBar_infobar_value_text);
			setValueText(valueText);

			int valueStyleId = a.getResourceId(R.styleable.InfoBar_infobar_value_text_appearance, nValueDefaultStyle);
			setValueStyle(valueStyleId);

			String hintText = a.getString(R.styleable.InfoBar_infobar_value_hint);
			setValueHintText(hintText);

			int nValueHintColorId = a.getResourceId(R.styleable.InfoBar_infobar_value_hint_color, nHintColorDefault);
			setValueHintColor(nValueHintColorId);

			nArrowDirection = a.getInt(R.styleable.InfoBar_arrow_direction, ARROW_DIRECTION_RIGHT);
			boolean hasArrow = a.getBoolean(R.styleable.InfoBar_hasArrow, true);
			setHasArrow(hasArrow);

			a.recycle();
		}
	}

	/**
	 * 创建child view对象，并添加到父容器中
	 * <p>
	 * Value内容部分自适应显示宽度，剩余空间由Label标题部分自适应占满。
	 * </p>
	 * 
	 */
	protected void createChildViews() {
		setGravity(Gravity.CENTER_VERTICAL);

		LayoutParams labelParams = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f);
		mLabelText = new XsTextView(getContext());
		mLabelText.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
		addView(mLabelText, labelParams);

		LayoutParams valueParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		mValueText = new XsTextView(getContext());
		mValueText.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
		mValueText.setLineSpacing(3.4f, 1.0f);
		addView(mValueText, valueParams);
	}

	/**
	 * 设置Label和Value的宽度比
	 * 
	 * @param labelWeight
	 *            Label子控件的宽度权重
	 * @param valueWeight
	 *            Value子控件的宽度权重
	 * 
	 * @see {@link #mLabelText}, {@link #mValueText}
	 */
	public void setWeight(float labelWeight, float valueWeight) {
		LayoutParams labelParams = new LayoutParams(0, LayoutParams.WRAP_CONTENT, labelWeight);
		mLabelText.setLayoutParams(labelParams);
		LayoutParams valueParams = new LayoutParams(0, LayoutParams.WRAP_CONTENT, valueWeight);
		mValueText.setLayoutParams(valueParams);
	}

	/**
	 * 设置Label的宽度， Value占用剩余的位置
	 * 
	 * @param width
	 *            Label的显示宽度(in pixel format)
	 */
	public void setLabelWidth(int width) {
		LayoutParams labelParams = new LayoutParams(width, LayoutParams.WRAP_CONTENT);
		mLabelText.setLayoutParams(labelParams);
		LayoutParams valueParams = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
		mValueText.setLayoutParams(valueParams);
	}

	/**
	 * 设置Label文字
	 * 
	 * @param text
	 *            需要显示的Label文本
	 */
	public void setLabelText(String text) {
		setLabelText(text, false);
	}

	/**
	 * 设置Label文字
	 * 
	 * @param text
	 *            需要显示的Label文本
	 * @param isMandatory
	 *            是否是必填项
	 */
	public void setLabelText(String text, boolean isMandatory) {
		if (text != null) {
			// if (isMandatory) {
			// SpannableStringBuilder labelString = new
			// SpannableStringBuilder("*");
			// labelString.append(text);
			// labelString.setSpan(new TextAppearanceSpan(getContext(),
			// R.style.text_14_f85d00),
			// 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			//
			// mLabelText.setText(labelString);
			// } else {
			// mLabelText.setText(text);
			// }
			if (isMandatory) {
				mLabelText.setCompoundDrawable(getResources().getDrawable(R.drawable.icon_required), 0, 0, 0);
			} else if (mIconDrawable != null) {
				// mLabelText.setCompoundDrawable(mIconDrawable, 0, iconWidth,
				// iconHeight);
				setIconDrawable(mIconDrawable, nIconWidth, nIconHeight);
			} else {
				mLabelText.setCompoundDrawable(null, 0, 0, 0);
			}
			mLabelText.setText(text);
		}
	}

	/**
	 * 设置Lable的Text式样
	 * 
	 * @param style
	 *            Label显示的Text式样resource id
	 */
	public void setLabelStyle(int style) {
		if (style != 0) {
			mLabelText.setTextAppearance(getContext(), style);
		}
	}

	/**
	 * 设置Logo 图片
	 * 
	 * @param resid
	 *            Logo图片的resource id
	 */
	public void setIconDrawable(int resid) {
		if (resid > 0) {
			setIconDrawable(getResources().getDrawable(resid));
		}
	}

	/**
	 * 设置Logo图片
	 * 
	 * @param drawable
	 *            Logo图片的资源对象
	 */
	public void setIconDrawable(Drawable drawable) {
		// if (drawable != null) {
		// mLabelText.setCompoundDrawablePadding(nDrawablePadding);
		// mLabelText.setCompoundDrawable(drawable, 0, iconWidth, iconHeight);
		// }
		setIconDrawable(drawable, 0, 0);
	}

	/**
	 * 设置Logo图片
	 * 
	 * @param drawable
	 *            Logo图片的资源对象
	 * @param width
	 *            Logo图片显示的宽度(in pixel format), 传 0 则使用图片的实际宽度
	 * @param height
	 *            Logo图片显示的高度(in pixel format), 传 0 则使用图片的实际高度
	 */
	public void setIconDrawable(Drawable drawable, int width, int height) {
		mIconDrawable = drawable;
		nIconWidth = width;
		nIconHeight = height;

		mLabelText.setCompoundDrawablePadding(nDrawablePadding);
		mLabelText.setCompoundDrawable(drawable, 0, nIconWidth, nIconHeight);
	}

	/**
	 * 设置Value文字
	 * 
	 * @param text
	 *            需要显示的Value文本
	 */
	public void setValueText(String text) {
		if (mValueText != null) {
			mValueText.setText(text);
		}
	}

	/**
	 * 设置Value文字
	 * 
	 * @param text
	 *            需要显示的Value文本
	 */
	public void setValueText(CharSequence text) {
		if (mValueText != null) {
			mValueText.setText(text);
		}
	}

/**
	 * 设置Value文字（多行）
	 * 
	 * @param list 需要显示的Value文本集合, ArrayList<'String'>
	 */
	public void setValueText(ArrayList<String> list) {
		if (list != null && list.size() > 0) {
			String[] values = new String[list.size()];
			values = list.toArray(values);
			setValueText(values);
		} else {
			setValueText("");
		}
	}

	/**
	 * 设置Value文字（多行）
	 * 
	 * @param values
	 *            需要显示的Value文本数组
	 */
	public void setValueText(String[] values) {
		if (values != null && values.length > 0) {
			StringBuilder sb = new StringBuilder();
			int length = values.length;
			for (int i = 0; i < length; i++) {
				sb.append(values[i]);
				if (i < (length - 1)) {
					sb.append('\n');
				}
			}
			setValueText(sb.toString());
		} else {
			setValueText("");
		}
	}

	/**
	 * 设置Value字体式样
	 * 
	 * @param style
	 *            Value显示的Text式样resource id
	 */
	public void setValueStyle(int style) {
		if (style != 0 && mValueText != null) {
			mValueText.setTextAppearance(getContext(), style);
		}
	}

	/**
	 * 设置Value的Hint文字
	 * 
	 * @param text
	 *            Value 显示的hint文本
	 */
	public void setValueHintText(String text) {
		if (mValueText != null) {
			mValueText.setHint(text);
		}
	}

	/**
	 * 设置Value的Hint的字体颜色
	 * 
	 * @param colorId
	 *            Value的hint文本字体颜色值(color的resourceId，例如R.color.xxx)
	 */
	public void setValueHintColor(int colorId) {
		if (mValueText != null) {
			mValueText.setHintTextColor(getContext().getResources().getColor(colorId));
		}
	}

	/**
	 * 设置右侧Arrow的显示方向
	 * 
	 * @param arrowDirection
	 *            0：向右 1：向下 2：向上。调用此方法后自动显示Arrow
	 */
	public void setArrowType(int arrowDirection) {
		if (nArrowDirection != arrowDirection) {
			nArrowDirection = arrowDirection;
			setHasArrow(true);
		}
	}

	/**
	 * 设置是否显示Arrow 不掉用此接口，默认会显示
	 * 
	 * @param hasArrow
	 *            true代表显示Arrow，false代表不显示Arrow
	 */
	public void setHasArrow(boolean hasArrow) {
		if (mValueText == null) {
			return;
		}

		if (hasArrow) {
			Drawable arrowDrawable = null;
			switch (nArrowDirection) {
				case ARROW_DIRECTION_RIGHT :
					arrowDrawable = getResources().getDrawable(R.drawable.icon_arrow);
					break;
				case ARROW_DIRECTION_BOTTOM :
					arrowDrawable = getResources().getDrawable(R.drawable.icon_arrowx);
					break;
				case ARROW_DIRECTION_UP :
					arrowDrawable = getResources().getDrawable(R.drawable.icon_arrows);
					break;
				default :
					break;
			}
			mValueText.setCompoundDrawable(arrowDrawable, 2, 0, 0);
			mValueText.setCompoundDrawablePadding(DeviceUtil.getPixelFromDip(2.0f));
		} else {
			mValueText.setCompoundDrawables(null, null, null, null);
		}
	}

	/**
	 * 
	 * @return 获取Value子控件对象
	 * @see {@link #mValueText}
	 */
	public XsTextView getmValueText() {
		return mValueText;
	}

	/**
	 * 
	 * 设置Label子控件是否是单行显示
	 * 
	 * @param isSingleLine
	 *            true代表单行显示，false代表多行显示
	 * @see {@link #mLabelText}
	 */
	public void setTitleSingleLine(boolean isSingleLine) {
		mLabelText.setSingleLine(isSingleLine);
		mLabelText.setEllipsize(TruncateAt.END);
	}

	/**
	 * 
	 * 设置Value子控件是否是单行显示
	 * 
	 * @param isSingleLine
	 *            true代表单行显示，false代表多行显示
	 * @see {@link #mValueText}
	 */
	public void setValueSingleLine(boolean isSingleLine) {
		mValueText.setSingleLine(isSingleLine);
		mValueText.setEllipsize(TruncateAt.END);
	}

	/**
	 * 
	 * 设置Value子控件内部，显示文本与右侧Arrow之间的空隙
	 * 
	 * @param padding
	 *            空隙值(in dip format)
	 */
	public void setValueTextPadding(float padding) {
		mValueText.setCompoundDrawablePadding(DeviceUtil.getPixelFromDip(padding));
	}

	/**
	 * 设置Value子空间文字的对齐属性，默认为水平右对齐+垂直居中
	 * 
	 * @param gravity
	 *            对齐属性
	 */
	public void setValueGravity(int gravity) {
		mValueText.setGravity(gravity);
	}

}