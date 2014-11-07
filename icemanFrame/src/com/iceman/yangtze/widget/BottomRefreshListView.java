package com.iceman.yangtze.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iceman.yangtze.R;

/**
 * Function: 底部自动刷新listview
 * 
 * @author yrguo
 * @Date 2012-11-22 下午3:35:15
 * @edit by nanzhiwen 2013-9-29 下午
 * 
 */
public class BottomRefreshListView extends ListView implements OnScrollListener {

	public interface OnLoadMoreListener {
		public void onLoadMore();
	}

	private static final String TAG = "BottomFreashListView";
	/**
	 * mOnScrollListener:滚动监听
	 */
	private OnScrollListener mOnScrollListener;

	/**
	 * mFooterDividerView:底部分割线
	 */
	private View mFooterDividerView;
	/**
	 * mFooterView:底部view
	 */
	private RelativeLayout mFooterView;
	/**
	 * mProgressLayout:加载布局
	 */
	private LinearLayout mProgressLayout;
	/**
	 * mProgressBarLoadMore:加载框
	 */
	private ProgressBar mProgressBarLoadMore;
	/**
	 * mLoadComplete:结束文字
	 */
	private TextView mLoadComplete;
	/**
	 * mLoadingText:加载中文字
	 */
	private TextView mLoadingText;
	/**
	 * mOnLoadMoreListener:加载监听
	 */
	private OnLoadMoreListener mOnLoadMoreListener;
	/**
	 * bIsLoadingMore:是否正在加载下一页
	 */
	private boolean bIsLoadingMore = false;
	/**
	 * bIsLoaded:全部加载完毕
	 */
	private boolean bIsLoaded = false;
	/**
	 * nCurrentScrollState:滚动状态
	 */
	private int nCurrentScrollState;
	/**
	 * oldFirstVisibleItem:
	 */
	private int oldFirstVisibleItem = 0;
	/**
	 * itemClickListener:item点击监听
	 */
	private OnItemClickListener itemClickListener;
	private boolean showLine;
	private OnItemClickListener selfItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			if (position != parent.getCount() - 1) {
				if (itemClickListener != null) {
					itemClickListener.onItemClick(parent, view, position, id);
				}
			}
		}
	};

	public BottomRefreshListView(Context context) {
		this(context, null);
	}

	public BottomRefreshListView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public BottomRefreshListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	/**
	 * 功能描述:初始化
	 * 
	 * <pre>
	 *     yrguo:   2012-11-22      新建
	 * </pre>
	 * 
	 * @param context
	 */
	private void init(Context context) {
		// footer
		showLine = true;
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mFooterView = (RelativeLayout) inflater.inflate(R.layout.load_more_footer, this, false);
		mProgressLayout = (LinearLayout) mFooterView.findViewById(R.id.loading_more_layout);
		mProgressBarLoadMore = (ProgressBar) mFooterView.findViewById(R.id.load_more_progressBar);
		mLoadComplete = (TextView) mFooterView.findViewById(R.id.load_complete);
		mLoadingText = (TextView) mFooterView.findViewById(R.id.load_text);
		mFooterView.setClickable(false);
		if (showLine) {
			View view = new View(getContext());
			view.setBackgroundColor(getResources().getColor(R.color.ui_bg_divider));
			view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 1));
			mFooterDividerView = view;
			addFooterView(view, null, false);
		}
		addFooterView(mFooterView, null, false);

		setCacheColorHint(Color.TRANSPARENT);
		// setFastScrollEnabled(true);
		super.setOnScrollListener(this);
		super.setOnItemClickListener(selfItemClickListener);
	}

	@Override
	public void setAdapter(ListAdapter adapter) {
		super.setAdapter(adapter);
	}

	@Override
	public void setOnScrollListener(AbsListView.OnScrollListener listener) {
		mOnScrollListener = listener;
	}

	@Override
	public void setOnItemClickListener(OnItemClickListener listener) {
		itemClickListener = listener;
	}

	public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
		mOnLoadMoreListener = onLoadMoreListener;
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		if (mOnScrollListener != null) {
			mOnScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
		}

		if (mOnLoadMoreListener != null && !bIsLoaded) {
			if (visibleItemCount == totalItemCount) {
				mProgressBarLoadMore.setVisibility(View.GONE);
				mProgressLayout.setVisibility(View.GONE);
				return;
			}
			if (firstVisibleItem > this.oldFirstVisibleItem) {
				System.out.println("Scroll up!");
				boolean loadMore = firstVisibleItem + visibleItemCount >= totalItemCount - 4;
				if (!bIsLoadingMore && loadMore && nCurrentScrollState != SCROLL_STATE_IDLE) {
					mProgressBarLoadMore.setVisibility(View.VISIBLE);
					mProgressLayout.setVisibility(View.VISIBLE);
					bIsLoadingMore = true;
					onLoadMore();
				}
			}
			oldFirstVisibleItem = firstVisibleItem;
		} else {
			if (mProgressBarLoadMore != null) {
				mProgressBarLoadMore.setVisibility(View.GONE);
			}

		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		nCurrentScrollState = scrollState;

		if (mOnScrollListener != null) {
			mOnScrollListener.onScrollStateChanged(view, scrollState);
		}
	}

	/**
	 * 功能描述:加载更多
	 * 
	 * <pre>
	 *     yrguo:   2012-11-22      新建
	 * </pre>
	 * 
	 */
	public void onLoadMore() {
		Log.d(TAG, "onLoadMore");
		if (mOnLoadMoreListener != null) {
			mOnLoadMoreListener.onLoadMore();
		}
	}

	/**
	 * 功能描述:状态回至
	 * 
	 * <pre>
	 *     yrguo:   2012-11-22      新建
	 * </pre>
	 * 
	 */
	public void resetAll() {
		bIsLoadingMore = false;
		bIsLoaded = false;
		mLoadComplete.setVisibility(View.GONE);
	}

	public void setDefFooterViewVisible(int visibility) {
		if (mFooterView != null) {
			mFooterView.setVisibility(visibility);
		}
	}

	/**
	 * 
	 * 功能描述:替换默认的footerView
	 * 
	 * <pre>
	 *     geyb:   2013-5-16      新建
	 * </pre>
	 * 
	 * @param v
	 */
	public void replaceFooterView(View v) {
		if (mFooterView != null) {
			try {
				removeFooterView(mFooterView);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		addFooterView(v, null, false);
	}

	/**
	 * 功能描述:单次查询结束
	 * 
	 * <pre>
	 *     yrguo:   2012-11-22      新建
	 * </pre>
	 * 
	 */
	public void onLoadMoreComplete() {
		bIsLoadingMore = false;
		mProgressBarLoadMore.setVisibility(View.GONE);
		mProgressLayout.setVisibility(View.GONE);
	}

	/**
	 * 功能描述:已到最后一页
	 * 
	 * <pre>
	 *     yrguo:   2012-11-22      新建
	 * </pre>
	 * 
	 */
	public void onAllLoaded() {
		onLoadMoreComplete();
		bIsLoaded = true;
		mLoadComplete.setVisibility(View.VISIBLE);
	}

	/**
	 * 功能描述:设置结束文字
	 * 
	 * <pre>
	 *     yrguo:   2012-11-22      新建
	 * </pre>
	 * 
	 * @param text
	 */
	public void setPromptText(String text) {
		if (mLoadComplete != null) {
			mLoadComplete.setText(text);
		}
	}

	/**
	 * 功能描述:设置加载中文字
	 * 
	 * <pre>
	 *     yrguo:   2012-11-22      新建
	 * </pre>
	 * 
	 * @param text
	 */
	public void setLoadingText(String text) {
		if (mLoadingText != null) {
			mLoadingText.setText(text);
		}
	}

	/**
	 * 功能描述:隐藏分割线
	 * 
	 * <pre>
	 *     yrguo:   2012-11-22      新建
	 * </pre>
	 * 
	 */
	public void hideFooterDivider() {
		if (mFooterDividerView != null && mFooterDividerView.isShown()) {
			// mFooterDividerView.setVisibility(View.GONE);
			removeFooterView(mFooterDividerView);
		}
	}
}