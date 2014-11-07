package com.iceman.yangtze.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

/**
 * 实现点击后的磁贴效果以及在控件内部指定位置显示消息提醒功能。
 */
public class HomeButtonView extends RelativeLayout{

	private static final float SCALE_FACTOR = 0.95f;
	private static final long SCALE_ANIM_DURATION = 200l;

	private boolean bIsButtonDown;
	private ScaleAnimation mScaleInAnim;
	private ScaleAnimation mScaleOutAnim;
	private float nMoveOffset;

	public HomeButtonView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public HomeButtonView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initialize();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			bIsButtonDown = true;
			showScaleInAnim();
			break;
		case MotionEvent.ACTION_UP:
			if (isTouchAvailable(event)) {
				showScaleOutAnim();
			}
			break;
		case MotionEvent.ACTION_MOVE:
			if (!isTouchAvailable(event)) {
				if (bIsButtonDown) {
					bIsButtonDown = false;
					showScaleOutAnim();
				}
			}
			break;
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_OUTSIDE:
			bIsButtonDown = false;
			showScaleOutAnim();
			break;
		default:
			break;
		}
		return true;
	}

	private void initialize() {
		nMoveOffset = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10.0f, getResources().getDisplayMetrics());

		ScaleAnimation scaleInAnim = new ScaleAnimation(1.0f, SCALE_FACTOR, 1.0f, SCALE_FACTOR, 1, 0.5f, 1, 0.5f);
		scaleInAnim.setFillAfter(true);
		scaleInAnim.setDuration(SCALE_ANIM_DURATION);
		mScaleInAnim = scaleInAnim;

		ScaleAnimation scaleOutAnim = new ScaleAnimation(SCALE_FACTOR, 1.0f, SCALE_FACTOR, 1.0f, 1, 0.5f, 1, 0.5f);
		scaleOutAnim.setFillAfter(true);
		scaleOutAnim.setDuration(SCALE_ANIM_DURATION);
		mScaleOutAnim = scaleOutAnim;
		mScaleOutAnim.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				if (bIsButtonDown) {
					performClick();
				}
				bIsButtonDown = false;
			}
		});
	}

	private void showScaleInAnim() {
		invalidate();
		startAnimation(mScaleInAnim);
	}

	private void showScaleOutAnim() {
		invalidate();
		startAnimation(mScaleOutAnim);
	}

	private boolean isTouchAvailable(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();

		if ((x < -nMoveOffset) || (y < -nMoveOffset)
				|| (x >= nMoveOffset + getWidth()) || (y >= nMoveOffset + getHeight())) {
			return false;
		}
		return true;
	}
}