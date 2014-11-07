package com.iceman.yangtze.widget;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.iceman.yangtze.R;


public class FragmentTabHost extends LinearLayout {
	public FrameLayout mFrameLayout;
	public LinearLayout mRelativeLayout;
	public Context mContext;
	public FragmentManager mFragmentManager;
	public boolean mAttached;
	public TabInfo mLastTab;
	public int mCurrentTab;
	public final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();
	public CtripTabHostListener ctripTabHostListener;

	public static interface CtripTabHostListener {
		/** 切换时触发 */
		public void onTabChange(int postion, String tag);

		/** 重复点击时触发 */
		public void onTabClick(int postion, String tag);
	}

	public static final class TabInfo {
		public final String tag;
		public final Class<?> clss;
		public final Bundle args;
		public Fragment fragment;
		public final int indicatorResId;

		TabInfo(String _tag, Class<?> _class, Bundle _args) {
			this(_tag, _class, _args, 0);
		}

		TabInfo(String _tag, Class<?> _class, Bundle _args, int _indicatorResId) {
			tag = _tag;
			clss = _class;
			args = _args;
			indicatorResId = _indicatorResId;
		}
	}

	static class CtripTabSavedState extends BaseSavedState {
		String curTab;

		CtripTabSavedState(Parcelable superState) {
			super(superState);
		}

		private CtripTabSavedState(Parcel in) {
			super(in);
			curTab = in.readString();
		}

		@Override
		public void writeToParcel(Parcel out, int flags) {
			super.writeToParcel(out, flags);
			out.writeString(curTab);
		}

		@Override
		public String toString() {
			return "CtripFragmentTabHost.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " curTab=" + curTab + "}";
		}

		public static final Creator<CtripTabSavedState> CREATOR = new Creator<CtripTabSavedState>() {
			public CtripTabSavedState createFromParcel(Parcel in) {
				return new CtripTabSavedState(in);
			}

			public CtripTabSavedState[] newArray(int size) {
				return new CtripTabSavedState[size];
			}
		};
	}

	public FragmentTabHost(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initFragmentTabHost();
	}

	private void initFragmentTabHost() {
		// TODO Auto-generated method stub
		mCurrentTab = -1;
		setOrientation(LinearLayout.VERTICAL);
	}

	public void setUp(Context context, FragmentManager manager) {
		mRelativeLayout = (LinearLayout) findViewById(R.id.tab_host_index);
		if (mRelativeLayout == null) {
			throw new RuntimeException("Your CtripFragmentTabHost must have a RelativeLayout whose id attribute is 'R.id.tab_host'");
		}
		mFrameLayout = (FrameLayout) findViewById(R.id.tab_host_continar);
		if (mFrameLayout == null) {
			throw new RuntimeException("Your CtripFragmentTabHost must have a FrameLayout whose id attribute is 'R.id.continar'");
		}
		mContext = context;
		mFragmentManager = manager;
	}

	public void addTab(String tag, Class<?> clss, Bundle args) {
		addTab(tag, clss, args, 0);
	}

	public void addTab(String tag, Class<?> clss, Bundle args, int indicatorResId) {
		TabInfo info = new TabInfo(tag, clss, args, indicatorResId);
		if (mAttached) {
			// If we are already attached to the window, then check to make
			// sure this tab's fragment is inactive if it exists. This shouldn't
			// normally happen.
			info.fragment = mFragmentManager.findFragmentByTag(tag);
			if (info.fragment != null && !info.fragment.isDetached()) {
				FragmentTransaction ft = mFragmentManager.beginTransaction();
				ft.detach(info.fragment);
				info.fragment = null;
				ft.commit();
			}
		}
		mTabs.add(info);
		if (mCurrentTab == -1) {
			mCurrentTab = 0;
		}
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		mAttached = true;
		String currentTab = getCurrentTabTag();
		// Go through all tabs and make sure their fragments match
		// the correct state.
		FragmentTransaction ft = null;
		for (int i = 0; i < mTabs.size(); i++) {
			TabInfo tab = mTabs.get(i);
			tab.fragment = mFragmentManager.findFragmentByTag(tab.tag);
			if (tab.fragment != null && !tab.fragment.isDetached()) {
				if (tab.tag.equals(currentTab)) {
					// The fragment for this tab is already there and
					// active, and it is what we really want to have
					// as the current tab. Nothing to do.
					mLastTab = tab;
				} else {
					if (ft == null) {
						ft = mFragmentManager.beginTransaction();
					}
					ft.detach(tab.fragment);
					tab.fragment = null;
				}
			}
		}
		ft = doTabChanged(currentTab, ft);
		if (ft != null) {
			ft.commitAllowingStateLoss();
			mFragmentManager.executePendingTransactions();
		}
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		mAttached = false;
	}

	@Override
	protected Parcelable onSaveInstanceState() {
		// TODO Auto-generated method stub
		Parcelable superState = super.onSaveInstanceState();
		CtripTabSavedState ctripTabSavedState = new CtripTabSavedState(superState);
		return ctripTabSavedState;
	}

	@Override
	protected void onRestoreInstanceState(Parcelable state) {
		// TODO Auto-generated method stub
		CtripTabSavedState ss = (CtripTabSavedState) state;
		super.onRestoreInstanceState(ss.getSuperState());
		setCurrentTabByTag(ss.curTab);
	}

	public void setCurrentTabByTag(String tag) {
		int i;
		FragmentTransaction ft = null;
		for (i = 0; i < mTabs.size(); i++) {
			if (mTabs.get(i).tag.equals(tag)) {
				ft = doTabChanged(tag, null);
				mCurrentTab = i;
				break;
			}
		}
		if (ft != null) {
			ft.commit();
			mFragmentManager.executePendingTransactions();
			if (ctripTabHostListener != null) {
				ctripTabHostListener.onTabChange(i, tag);
			}

			for (i = 0; i < mTabs.size(); i++) {
				if (mTabs.get(i).indicatorResId > 0) {
					View tab = findViewById(mTabs.get(i).indicatorResId);
					if (tab != null) {
						if (mCurrentTab == i) {
							findViewById(mTabs.get(i).indicatorResId).setSelected(true);
						} else {
							findViewById(mTabs.get(i).indicatorResId).setSelected(false);
						}
					}
				}
			}
		} else {
			if (ctripTabHostListener != null) {
				ctripTabHostListener.onTabClick(i, tag);
			}
		}
	}

	public FragmentTransaction doTabChanged(String tabId, FragmentTransaction ft) {
		TabInfo newTab = null;
		for (int i = 0; i < mTabs.size(); i++) {
			TabInfo tab = mTabs.get(i);
			if (tab.tag.equals(tabId)) {
				newTab = tab;
			}
		}
		if (newTab == null) {
			throw new IllegalStateException("No tab known for tag " + tabId);
		}
		if (mLastTab != newTab) {
			if (ft == null) {
				ft = mFragmentManager.beginTransaction();
			}
			if (mLastTab != null) {
				if (mLastTab.fragment != null) {
					ft.hide(mLastTab.fragment);
				}
			}
			if (newTab != null) {
                ft.setCustomAnimations(R.anim.fragment_tab_in, R.anim.fragment_tab_out);
				if (newTab.fragment == null) {
					newTab.fragment = Fragment.instantiate(mContext, newTab.clss.getName(), newTab.args);
                    ft.add(R.id.tab_host_continar, newTab.fragment, newTab.tag);
				} else {
					ft.show(newTab.fragment);
				}
			}
			mLastTab = newTab;
		}
		return ft;
	}

	public String getCurrentTabTag() {
		if (mCurrentTab >= 0 && mCurrentTab < mTabs.size()) {
			return mTabs.get(mCurrentTab).tag;
		}
		return null;
	}

	public TabInfo getCurrentTab() {
		return mLastTab;
	}

	public CtripTabHostListener getCtripTabHostListener() {
		return ctripTabHostListener;
	}

	public void setCtripTabHostListener(CtripTabHostListener ctripTabHostListener) {
		this.ctripTabHostListener = ctripTabHostListener;
	}

}
