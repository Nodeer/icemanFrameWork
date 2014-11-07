/** 
 * @ProjectName:CYFrameAndroid  
 * @Title: CYouGridView.java 
 * @Package com.cyou.cyframeandroid.widget 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuqi qiliu_17173@cyou-inc.com   
 * @date 2013-8-21 下午5:58:46 
 * @version V1.0   
 * Copyright (c) 2013搜狐公司-版权所有
 */
package com.iceman.yangtze.widget;

import android.widget.ListView;

/**
 * @Description: TODO(自定义的listview可以嵌套在其他控件之中完全展示出来)
 */
public class XsListView extends ListView {
	public XsListView(android.content.Context context,
			android.util.AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * 设置不滚动
	 */
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);

	}

}
