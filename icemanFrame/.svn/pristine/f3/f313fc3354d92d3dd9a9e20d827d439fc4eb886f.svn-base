package com.iceman.yangtze.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * @ClassName: IWReboundScrollView
 * @Description:仿IOS的可以下拉、上拉回弹效果的ScrollView
 * @author ted
 * @date 2014年6月22日 下午1:36:59
 * 
 */
public class IWReboundScrollView extends ScrollView {
	/** MOVE_SPEED:移动速率，手指实际移动距离与视图移动距离的转化，该数值可以修改 */
	private static final float MOVE_SPEED = 0.5f;
	/** ANIM_TIME:回弹需要的时间 */
	private static final int ANIM_TIME = 300;
	/** childView:ScrollView内部要显示的内容 */
	private View childView;
	/** pointY:手指按下时的Y值 */
	private float pointY;
	/** originalLayInfo:原始布局信息，记录正常显示时ChildView的相对位置信息 */
	private Rect originalLayInfo = new Rect();
	/** canPullDown:是否可以继续下拉 */
	private boolean canPullDown = false;
	/** canPullUp:是否可以继续上拉 */
	private boolean canPullUp = false;
	/** isMoved:布局是否移动过 */
	private boolean isMoved = false;

	public IWReboundScrollView(Context context) {
		super(context);
	}

	public IWReboundScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onFinishInflate() {
		if (getChildCount() > 0) {
			childView = getChildAt(0);
		}
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);

		if (childView == null)
			return;
		/** 在第一次视图加载完成时记录下来子布局的位置信息，在将来恢复时可以使用 */
		originalLayInfo.set(childView.getLeft(), childView.getTop(), childView.getRight(), childView.getBottom());
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (childView == null) {
			return super.dispatchTouchEvent(ev);
		}
		/** 当前的点击是否在ScrollView之外。觉得这里的判断不是很准确 */
		boolean isTouchOutOfScrollView = ev.getY() >= this.getHeight() || ev.getY() <= 0;

		/** 如果移动到了当前ScrollView控件之外 */
		if (isTouchOutOfScrollView) {
			/** 如果当前contentView已经被移动, 首先把布局移到原位置, 然后消费点这个事件 */
			if (isMoved) {
				boundBack();
				return true;
			}
		}
		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			/** 判断是否可以上拉和下拉 */
			canPullDown = isCanPullDown();
			canPullUp = isCanPullUp();
			/** 记录按下时的Y值 */
			pointY = ev.getY();
			break;
		case MotionEvent.ACTION_UP:
			boundBack();
			break;
		case MotionEvent.ACTION_MOVE:
			/** 在移动的过程中， 既没有滚动到可以上拉的程度， 也没有滚动到可以下拉的程度 */
			if (!canPullDown && !canPullUp) {
				pointY = ev.getY();
				canPullDown = isCanPullDown();
				canPullUp = isCanPullUp();
				break;
			}
			/** 计算手指移动的距离 */
			float nowY = ev.getY();
			int deltaY = (int) (nowY - pointY);

			/** 是否应该移动布局 1、可以下拉， 并且手指向下移动 2、可以上拉， 并且手指向上移动 3、既可以上拉也可以下拉（这种情况出现在ScrollView包裹的控件比ScrollView还小） */
			boolean shouldMove = (canPullDown && deltaY > 0) || (canPullUp && deltaY < 0) || (canPullUp && canPullDown);
			if (shouldMove) {
				/** 计算偏移量 */
				int offset = (int) (deltaY * MOVE_SPEED);
				/** 随着手指的移动而移动布局 */
				childView.layout(originalLayInfo.left, originalLayInfo.top + offset, originalLayInfo.right, originalLayInfo.bottom + offset);
				isMoved = true;
			}

			break;
		default:
			break;
		}

		return super.dispatchTouchEvent(ev);
	}

	/**
	 * 将内容布局移动到原位置 可以在UP事件中调用, 也可以在其他需要的地方调用, 如手指移动到当前ScrollView外时
	 */
	private void boundBack() {
		if (!isMoved)
			return;
		/** 开启动画 */
		TranslateAnimation anim = new TranslateAnimation(0, 0, childView.getTop(), originalLayInfo.top);
		anim.setDuration(ANIM_TIME);
		childView.startAnimation(anim);
		/** 设置回到正常的布局位置 */
		childView.layout(originalLayInfo.left, originalLayInfo.top, originalLayInfo.right, originalLayInfo.bottom);
		/** 将标志位设回false */
		canPullDown = false;
		canPullUp = false;
		isMoved = false;
	}

	/**
	 * @Title: isCanPullDown
	 * @Description:是否可以下拉
	 * @param @return
	 * @return boolean
	 */
	private boolean isCanPullDown() {
		/** scrollview没有向下拉过 或者 */
		return getScrollY() == 0 || childView.getHeight() < getHeight() + getScrollY();
	}

	/**
	 * @Title: isCanPullUp
	 * @Description:是否可以上拉
	 * @param @return
	 * @return boolean
	 */
	private boolean isCanPullUp() {
		return childView.getHeight() <= getHeight() + getScrollY();
	}

}
