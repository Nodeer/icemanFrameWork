package com.iceman.yangtze.widget;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;

/**
 * 
 * 继承Button，根据设置的背景Drawable资源，生成带有按下变暗效果的state drawable，并设置给控件自身显示。
 * 
 */
public class AutoPressColorButton extends android.widget.Button {

	public AutoPressColorButton(Context context) {
		super(context);
	}

	public AutoPressColorButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        if(isInEditMode()){
            return;
        }
        applyStateDrawable();
	}

	/**
	 * 
	 * <p>
	 * 生成StateDrawable背景资源，并设置给控件自身。
	 * </p>
	 * <p>
	 * 通过代码动态创建的CtripButton对象，可通过此接口。
	 * </p>
	 * 
	 */
	@SuppressWarnings("deprecation")
	public void applyStateDrawable() {
		Drawable bgNormal = getBackground();

		if (bgNormal != null && bgNormal instanceof BitmapDrawable) {
			Drawable bgPressed = GreyBitmapPool.getInstance().getGaryBitmap(((BitmapDrawable) bgNormal).getBitmap(), false);

			setBackgroundDrawable(getStateDrawable(bgNormal, bgPressed));
		}
	}

	private StateListDrawable getStateDrawable(Drawable bgNormal, Drawable bgPressed) {
		StateListDrawable sd = new StateListDrawable();
		sd.addState(new int[]{android.R.attr.state_pressed}, bgPressed);
		sd.addState(new int[]{}, bgNormal);
		return sd;
	}
}