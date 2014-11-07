package com.iceman.yangtze;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.iceman.yangtze.interfaces.ExcuteDialogFragmentCallBack;
import com.iceman.yangtze.manager.Constants;
import com.iceman.yangtze.widget.FragmentTabHost;
import com.iceman.yangtze.widget.XsTitleView;

public class UserHomeActivity extends BaseActivity implements ExcuteDialogFragmentCallBack {
	private FragmentTabHost mTabHost;
	private OnClickListener mOnTabClickListener = new OnClickListener() {
		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.tab_score:
				mTabHost.setCurrentTabByTag("score");
				break;
			case R.id.tab_timetable:
				mTabHost.setCurrentTabByTag("timetable");
				break;
			case R.id.tab_student:
				mTabHost.setCurrentTabByTag("student");
				break;
			case R.id.tab_mine:
				mTabHost.setCurrentTabByTag("mine");
				break;
			}

		}
	};
    private XsTitleView mTitleView;

    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage_layout);

        mTitleView = (XsTitleView) findViewById(R.id.title_view);
        mTitleView.setOnTitleClickListener(new XsTitleView.OnTitleClickListener() {

            @Override
            public void onLogoClick(View v) {

            }

            @Override
            public void onButtonClick(View v) {
                setResult(Constants.LOG_OUT_SUCCESS);
                finish();
            }
        });

		mTabHost = (FragmentTabHost) findViewById(R.id.tabhost);

//		mTabHost.addTab("score", ScoreMainFragment.class, null, R.id.tab_score);
//		mTabHost.addTab("timetable", TimeTableMainFragment.class, null, R.id.tab_timetable);
//		mTabHost.addTab("student", StudentMainFragment.class, null, R.id.tab_student);
//		mTabHost.addTab("mine", UserInfoFragment.class, null, R.id.tab_mine);
		mTabHost.setUp(this, getSupportFragmentManager());
		mTabHost.findViewById(R.id.tab_score).setOnClickListener(mOnTabClickListener);
		mTabHost.findViewById(R.id.tab_timetable).setOnClickListener(mOnTabClickListener);
		mTabHost.findViewById(R.id.tab_student).setOnClickListener(mOnTabClickListener);
		mTabHost.findViewById(R.id.tab_mine).setOnClickListener(mOnTabClickListener);
		mTabHost.findViewById(R.id.tab_score).setSelected(true);

	}

	@Override
	public void onPositiveBtnClick(String tag) {
	}

	@Override
	public void onNegtiveBtnClick(String tag) {
		// TODO Auto-generated method stub

	}

}
