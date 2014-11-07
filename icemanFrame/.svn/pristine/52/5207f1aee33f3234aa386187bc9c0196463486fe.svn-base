/** 
* @ProjectName:CYFrameAndroid  
* @Title: AspectRatioImageView.java 
* @Package com.cyou.cyframeandroid.widget 
* @Description: TODO(用一句话描述该文件做什么) 
* @author niuhb niuhaobo@cyou-inc.com   
* @date 2013-9-6 下午6:25:53 
* @version V1.0   
* Copyright (c) 2013搜狐公司-版权所有
*/ 
package com.iceman.yangtze.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/** 
 * @ClassName: AspectRatioImageView 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author niuhb niuhaobo@cyou-inc.com
 * @date 2013-9-6 下午6:25:53  
 */
public class XsImageView extends ImageView {
    public XsImageView(Context context) {
        super(context);
    }
 
    public XsImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
 
    public XsImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
 
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
         
        int height = width * getDrawable().getIntrinsicHeight() / getDrawable().getIntrinsicWidth();
        setMeasuredDimension(width, height);
    }
}
