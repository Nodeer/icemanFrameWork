package com.iceman.yangtze.widget;

import java.util.List;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.iceman.yangtze.R;
import com.iceman.yangtze.util.DeviceUtil;

/**
 * 带有2个按钮的Tab Group Buttton ，切换时带有动画效果
 *
 * @author zhiwen.nan
 * @version 5.3
 * @see android.widget.RadioGroup
 * @since 2013.12.16
 */
public class CtripTabGroupButton extends LinearLayout {

    private RadioGroup  mRadioGroup;
    private RadioButton mRadioButton0;
    private RadioButton mRadioButton1;
    private RadioButton mRadioButton2;
    private View mAnimView0;
    private View mAnimView1;
    private View mAnimView2;
    private int mWidth;
    private int mTabSize;
    private Animation mAnimation;
    private int mIndex;
    private OnTabItemSelectedListener mOnTabItemSelectedListener;

    /**
     * Tab Group 回调接口
     */
    public interface OnTabItemSelectedListener {

        /**
         * 当点击button时的外部事件处理
         *
         * @param whichButton 对应点击的那个Item button
         */
        void onTabItemClicked(int whichButton);
    }

    /**
     * 设置 TabGroup对外回调接口，供外部回调使用
     *
     * @param onTabItemSelectedListener TabGroup 对外回调接口
     */
    public void setOnTabItemSelectedListener(OnTabItemSelectedListener onTabItemSelectedListener) {
        mOnTabItemSelectedListener = onTabItemSelectedListener;
    }

    /**
     * 设置每个Item的Text Value
     *
     * @param itemArray tab item array
     */
    public void setTabItemArrayText(List<String> itemArray) {
        if (itemArray.size() > 0 && itemArray.size() <= 3) {
            mRadioButton0.setText(itemArray.get(0));
            mRadioButton1.setText(itemArray.get(1));
        }
        if (itemArray.size() == 3) {
            mRadioButton2.setText(itemArray.get(2));
        }

    }
    
    /**
     * 设置默认选项卡
     * @param position 0：第一项 1：第二项 2：第三项
     */
    public void setDefaultTab(int position){
        switch (position)  {
            case 0:
                mRadioGroup.check(R.id.radioButton0);
                break;
            case 1:
                mRadioGroup.check(R.id.radioButton1);
                break;
            case 2:
                mRadioGroup.check(R.id.radioButton2);
                break;
            default:
                break;

        }

    }

    public CtripTabGroupButton(Context context) {
        this(context, null);
    }

    public CtripTabGroupButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUpViews(context, attrs);
    }

    private void setUpViews(Context context, AttributeSet attrs) {
        View view = inflate(getContext(), R.layout.tab_group_buttton, null);
        mRadioGroup = (RadioGroup) view.findViewById(R.id.radio_group_switch);
        mRadioButton0 = (RadioButton) view.findViewById(R.id.radioButton0);
        mRadioButton1 = (RadioButton) view.findViewById(R.id.radioButton1);
        mRadioButton2 = (RadioButton) view.findViewById(R.id.radioButton2);
        mAnimView0 = view.findViewById(R.id.anim_view0);
        mAnimView1 = view.findViewById(R.id.anim_view1);
        mAnimView2 = view.findViewById(R.id.anim_view2);

        addView(view);
        initAttribute(context, attrs);
        init();
    }

    private void initAttribute(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CtripTabGroupButton);
        mTabSize = typedArray.getInt(R.styleable.CtripTabGroupButton_tab_size, 2);
        if (mTabSize == 3) {
            mAnimView2.setVisibility(View.INVISIBLE);
        } else {
            mAnimView2.setVisibility(View.GONE);
        }

    }

    private void init() {
        int[] screenInfo = DeviceUtil.getScreenSize(getResources().getDisplayMetrics());
        mWidth = screenInfo[0];
        if (mTabSize == 2) {
            mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.radioButton0:
                            mAnimation = new TranslateAnimation(mWidth / 2, 0, 0, 0);
                            startAnimation();
                            if (mOnTabItemSelectedListener != null) {
                                mOnTabItemSelectedListener.onTabItemClicked(0);
                            }
                            break;
                        case R.id.radioButton1:
                            mAnimation = new TranslateAnimation(0, mWidth / 2, 0, 0);
                            startAnimation();
                            if (mOnTabItemSelectedListener != null) {
                                mOnTabItemSelectedListener.onTabItemClicked(1);
                            }
                            break;
                        default:
                            break;
                    }

                }
            });
        } else if (mTabSize == 3) {
            mRadioButton2.setVisibility(View.VISIBLE);
            mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    int transimitionWidth = mWidth / 3;
                    switch (checkedId) {
                        case R.id.radioButton0:
                            if (mIndex == 2) {
                                transimitionWidth = mWidth / 3 * 2;
                            }
                            mAnimation = new TranslateAnimation(transimitionWidth, 0, 0, 0);
                            startAnimation();
                            if (mOnTabItemSelectedListener != null) {
                                mOnTabItemSelectedListener.onTabItemClicked(0);
                            }
                            mIndex = 0;
                            break;
                        case R.id.radioButton1:
                            if (mIndex == 0) {
                                mAnimation = new TranslateAnimation(0, mWidth / 3, 0, 0);
                            } else if (mIndex == 2) {
                                mAnimation = new TranslateAnimation(mWidth / 3 * 2, mWidth / 3, 0, 0);
                            }

                            startAnimation();
                            if (mOnTabItemSelectedListener != null) {
                                mOnTabItemSelectedListener.onTabItemClicked(1);
                            }
                            mIndex = 1;
                            break;
                        case R.id.radioButton2:
                            if (mIndex == 0) {
                                mAnimation = new TranslateAnimation(0, mWidth / 3 * 2, 0, 0);
                            } else if (mIndex == 1) {
                                mAnimation = new TranslateAnimation(mWidth / 3, mWidth / 3 * 2, 0, 0);
                            }

                            startAnimation();
                            if (mOnTabItemSelectedListener != null) {
                                mOnTabItemSelectedListener.onTabItemClicked(2);
                            }
                            mIndex = 2;
                            break;
                        default:
                            break;
                    }

                }
            });
        }


    }


    private void startAnimation() {

        // True:图片停在动画结束位置
        mAnimation.setFillAfter(true);
        mAnimation.setDuration(300);
        mAnimView0.startAnimation(mAnimation);
    }


}
