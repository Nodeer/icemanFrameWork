package com.iceman.yangtze.widget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.iceman.yangtze.R;
import com.iceman.yangtze.util.DeviceUtil;
import com.iceman.yangtze.util.StringUtil;

/**
 * 下拉列表（替代滚轮） 回调方法：setOnDropdownItemClickListener(OnDropdownItemClickListener
 * dropdownItemClickListener);
 * 
 * @author junyingding
 * 
 *         edit by junyingding for v5.3 2013-12-19 change UI
 *         替换勾样式，去除选中文字高亮，更改标题背景
 * 
 */
public class CtripDropdownListView extends LinearLayout{

	public interface OnDropdownViewCancelListener {
		/**
		 * 当Listview取消选择并退出时，调用通知监听者
		 */
		public void onDropdownViewCanceled();
	}

	public interface OnDropdownItemClickListener {
		/**
		 * 当Listview选择Item以后，调用通知监听者
		 * 
		 * @param index
		 *            选中项的index值
		 * @param object
		 *            选中项对应的数据
		 */
		public void onDropdownItemClick(int index, Object object);
	}

	protected static final int LISTVIEW_MARGIN = 20;
	protected static final int ITEM_PADDING = 20;

	private BaseDialogFragmentV2 mBaseDialogFragment;
	protected int nItemHeight;
	protected int nIndex = -1;// 选中的序号
	protected boolean bIsShowCheck = true;// 是否显示选中状态
	// private Drawable mCheckDrawable;// 勾
	private XsTextView mTitleText;// 标题栏
	private View mTitleLine;// 标题栏下分隔线
	private XsTextView mBottomText;// 底部说明栏
	private float nLines = 5.5f;// 列表显示行数
	protected int nTextSize;// 列表文字大小
	protected int[] nEnabledIndexs = new int[] {};
	protected ListView mListView;

	protected boolean bIsShowHighlight = true;
	protected int nColorResId = R.color.bg_dropdown_list_item_selector;

	private boolean bIsItemSelected = false;
	private OnDropdownViewCancelListener mDropDownViewCalceledListener;
	private OnDropdownItemClickListener mDropdownItemClickListener;

	protected ArrayList<Object> mObjectList;
	protected Object mObject;
	protected ListAdapter mListAdapter;

	@SuppressWarnings("deprecation")
	public CtripDropdownListView(Context context) {
		super(context);

//		mCheckDrawable = getResources().getDrawable(R.drawable.icon_gou);
		nItemHeight = DeviceUtil.getPixelFromDip(44);
		// nTextSize = DeviceUtil.getPixelFromDip(11);
		setItemTextSize(17);
		setOrientation(LinearLayout.VERTICAL);
		setBackgroundResource(R.drawable.bg_alert);
		// title
		mTitleText = new XsTextView(context);
		mTitleText.setGravity(Gravity.CENTER_VERTICAL);
		mTitleText.setCompoundDrawablePadding(DeviceUtil.getPixelFromDip(10));
		mTitleText.setVisibility(View.VISIBLE);
		mTitleText.setTextAppearance(getContext(), R.style.text_18_1490c5);
		mTitleText.setPadding(DeviceUtil.getPixelFromDip(25), 0, 0, 0);
		addView(mTitleText, new LayoutParams(LayoutParams.MATCH_PARENT, DeviceUtil.getPixelFromDip(50)));

		mTitleLine = new View(context);
		mTitleLine.setBackgroundColor(getResources().getColor(R.color.text_dialog_title));
		addView(mTitleLine, new LayoutParams(LayoutParams.MATCH_PARENT, DeviceUtil.getPixelFromDip(2)));
		// list
		mListView = new ListView(context);
		Drawable drawable = getResources().getDrawable(R.color.table_stroke);
		mListView.setDivider(drawable);
		mListView.setDividerHeight(2);
//		mListView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		mListView.setSelector(R.drawable.home_myctrip_info_selector);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//CtripActionLogUtil.logCode("ClickCode");//117 
				// 不可选项点击无反应
			    if (nEnabledIndexs != null && nEnabledIndexs.length > 0){
	                for (int i=0; i<nEnabledIndexs.length; i++){
	                    if (position == nEnabledIndexs[i]){
	                        return;
	                    }
	                }
			    }
				// 可选项可点击
				nIndex = position;
				mListAdapter.notifyDataSetChanged();
				if (mDropdownItemClickListener != null) {
					bIsItemSelected = true;
					mDropdownItemClickListener.onDropdownItemClick(position, mObjectList.get(position));
				}
			}
		});
		mListView.setFocusable(true);
		addView(mListView, new LayoutParams(LayoutParams.MATCH_PARENT, (int) (nItemHeight * nLines + nLines)));

		// bottom text
		mObjectList = new ArrayList<Object>();
		mListAdapter = new ListAdapter();
		mListView.setAdapter(mListAdapter);
		mListView.setCacheColorHint(Color.TRANSPARENT);
		mBottomText = new XsTextView(context);
		mBottomText.setGravity(Gravity.CENTER);
		int nBottomPadding = DeviceUtil.getPixelFromDip(10);
		mBottomText.setPadding(nBottomPadding, nBottomPadding, nBottomPadding, nBottomPadding);
		mBottomText.setTextAppearance(context, R.style.text_12_656565);
		mBottomText.setVisibility(View.GONE);
		addView(mBottomText);
	}

	@Override
	protected void onAttachedToWindow() {
		if (getLayoutParams() instanceof FrameLayout.LayoutParams) {
			FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) getLayoutParams();
			int margin = DeviceUtil.getPixelFromDip(LISTVIEW_MARGIN);
			lp.leftMargin = margin;
			lp.rightMargin = margin;
			setLayoutParams(lp);
		}
		if (mListView != null) {
			mListView.requestFocus();
		}
		super.onAttachedToWindow();
	}

	@Override
	protected void onDetachedFromWindow() {
		// 如果选中列表Item后关闭的情况，不通知cancel listener
		if (!bIsItemSelected && mDropDownViewCalceledListener != null) {
			mDropDownViewCalceledListener.onDropdownViewCanceled();
		}
		super.onDetachedFromWindow();
	}

	protected class ListAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mObjectList.size();
		}

		@Override
		public Object getItem(int position) {
			return mObjectList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			CheckedTextView itemView = null;
			if (convertView == null ) {
				convertView =
						LayoutInflater.from(getContext()).inflate(R.layout.list_item_drop_down_layout, null, false);

				itemView = (CheckedTextView) convertView;
				itemView.setHeight(nItemHeight);
//				itemView.setTextSize(nTextSize);
				itemView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, nTextSize);
				itemView.setGravity(Gravity.CENTER_VERTICAL);
				if (bIsShowHighlight && nColorResId != 0) {
					try {
						itemView.setTextColor(getResources().getColorStateList(nColorResId));
					} catch (NotFoundException e) {
						e.printStackTrace();
					}
				}

				// int paddingLeft = DeviceUtil.getPixelFromDip(25);
				// int paddingRight = DeviceUtil.getPixelFromDip(15);
//				itemView.setPadding(paddingLeft, 0, paddingRight, 0);
			} else {
				itemView = (CheckedTextView) convertView;
			}

			Object item = mObjectList.get(position);
			// 填写文字
			if (item instanceof CharSequence) {
				itemView.setText((CharSequence) item);
			} else {
				itemView.setText(item.toString());
			}
			// 打钩
			if (bIsShowCheck) {
				boolean isSelected = false;
				if (nIndex >= 0) {
					isSelected = (nIndex == position);
				} else if (mObject != null) {
					isSelected = mObject.toString().equals(item.toString());
				}

				itemView.setChecked(isSelected);
			} else {
				itemView.setChecked(false);
			}
			// 不可选项
			if (nEnabledIndexs != null && nEnabledIndexs.length > 0) {
			    itemView.setBackgroundResource(R.drawable.home_myctrip_info_selector);
			    boolean isEnabled = true;
			    for (int i=0; i<nEnabledIndexs.length; i++){
			        if (position == nEnabledIndexs[i]) {
			            isEnabled = false;
			            itemView.setChecked(false);
			            itemView.setBackgroundResource(R.drawable.bg_transparent);
			            break;
			        }
			    }
			    itemView.setEnabled(isEnabled);
			} else {
			    itemView.setEnabled(true);
			}
			return convertView;
		}
	}

	/**
	 * 设置List Item点击事件监听Listener
	 * 
	 * @param listener
	 *            点击事件的监听对象
	 */
	public void setOnDropdownItemClickListener(OnDropdownItemClickListener listener) {
		mDropdownItemClickListener = listener;
	}

	/**
	 * 设置Listview取消选择时的监听Listener
	 * 
	 * @param listener
	 *            取消事件的监听对象
	 */
	public void setOnDropdownViewCanceledListener(OnDropdownViewCancelListener listener) {
		mDropDownViewCalceledListener = listener;
	}

	/**
	 * 设置列表是否支持选中项颜色高亮，必须在显示之前就设置
	 * 
	 * @param isHighlight
	 *            true支持选中项高亮, false不支持
	 * @param colorResId
	 *            高亮的Text颜色selector resource id
	 */
	public void setListHighLight(boolean isHighlight, int colorResId) {
		bIsShowHighlight = isHighlight;
		nColorResId = colorResId;
	}

	/**
	 * 设置列表显示数据源
	 * 
	 * @param objectList
	 *            数据集合
	 */
	public void setDatas(List<?> objectList) {
		if (objectList != null) {
			setShowLines(objectList.size());
			mObjectList.clear();
			mObjectList.addAll(objectList);
			mListAdapter.notifyDataSetChanged();
		}
	}

	/**
	 * 设置列表显示数据源
	 * 
	 * @param strings
	 *            数据数组
	 */
	public void setDatas(Object[] strings) {
		if (strings != null) {
			setDatas((List<Object>) Arrays.asList(strings));
		}
	}

	/**
	 * 设置列表默认选中的Item
	 * 
	 * @param index
	 *            选中Item的index值
	 */
	public void setSelected(int index) {
		if (nIndex != index) {
			nIndex = index;
			mListAdapter.notifyDataSetChanged();
		}
	}

	/**
	 * 设置列表默认选中的Item
	 * 
	 * @param object
	 *            选中Item对应的数据对象
	 */
	public void setSelected(Object object) {
		if (mObject != object) {
			mObject = object;
			nIndex = -1;
			mListAdapter.notifyDataSetChanged();
		}
	}

	/**
	 * 设置显示的标题文字
	 * 
	 * @param title
	 *            标题文字
	 */
	public void setTitleText(String title) {
		if (StringUtil.emptyOrNull(title)) {
			mTitleText.setVisibility(View.GONE);
		} else {
			mTitleText.setVisibility(View.VISIBLE);
			mTitleText.setText(title);
		}
	}

	/**
	 * 设置显示的标题文字
	 * 
	 * @param titleResId
	 *            标题文字
	 */
	public void setTitleText(int titleResId) {
		if (titleResId != 0) {
			setTitleText(getResources().getString(titleResId));
		} else {
			setTitleText(null);
		}
	}

	/**
	 * 设置显示的标题Icon
	 * 
	 * @param drawable
	 *            标题Icon
	 */
	public void setTitleIcon(Drawable drawable) {
		if (drawable == null) {
			mTitleText.setCompoundDrawables(null, null, null, null);
		} else {
			mTitleText.setCompoundDrawable(drawable, 0, 0, 0);
		}
	}

	/**
	 * 设置显示的标题Icon
	 * 
	 * @param resId
	 *            标题Icon
	 */
	public void setTitleIcon(int resId) {
		if (resId != 0) {
			setTitleIcon(getResources().getDrawable(resId));
		} else {
			setTitleIcon(null);
		}
	}


	/**
	 * 设置是否高亮选中项
	 * 
	 * @param isShowCheck
	 *            true高亮显示选中项，false不高亮显示选中项
	 */
	public void isShowCheck(boolean isShowCheck) {
		if (bIsShowCheck != isShowCheck) {
			bIsShowCheck = isShowCheck;
			mListAdapter.notifyDataSetChanged();
		}
	}

	/**
	 * 设置Listview显示的行数
	 * 
	 * @param lines
	 *            显示的行数
	 */
	public void setShowLines(float lines) {
		if (lines != nLines && lines > 0) {
			if (lines < 5.5f) {
				nLines = lines;
			} else {
				nLines = 5.5f;
			}
			LayoutParams mListParams = (LinearLayout.LayoutParams) mListView.getLayoutParams();
			mListParams.height = (int) (nItemHeight * nLines + nLines);
			mListView.setLayoutParams(mListParams);
		}
	}

	/**
	 * 设置ListView的选中Item的背景效果
	 * 
	 * @param resid
	 *            selector resource id
	 */
	public void setListViewSelector(int resid) {
		if (resid > 0) {
			mListView.setSelector(resid);
		}
	}


	/**
	 * 设置Listview Item的显示字体大小
	 * 
	 * @param textSize
	 *            字体大小
	 */
	public void setItemTextSize(int textSize) {
		// nTextSize = DeviceUtil.getPixelFromDip(textSize);
		nTextSize = textSize;
		if (mListAdapter != null) {
			mListAdapter.notifyDataSetChanged();
		}
	}
}
