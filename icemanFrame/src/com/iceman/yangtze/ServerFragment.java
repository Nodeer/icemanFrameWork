package com.iceman.yangtze;

import android.os.Bundle;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

/**
 * Created by xubin on 14-6-3.
 */
public abstract class ServerFragment extends BaseFragment {
	protected RequestQueue mRequestQueue;

	public abstract void handResponse(String tag, Object response);

	public abstract void handError(String tag, VolleyError error);

	public RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(getActivity());
			mRequestQueue.start();
		}
		return mRequestQueue;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mRequestQueue = Volley.newRequestQueue(getActivity());
		mRequestQueue.start();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mRequestQueue.stop();
	}
}
