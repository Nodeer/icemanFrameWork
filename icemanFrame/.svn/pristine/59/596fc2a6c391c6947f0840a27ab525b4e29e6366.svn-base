package com.iceman.yangtze;

import android.os.Bundle;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

/**
 * Created by xubin on 14-5-30.
 */
public abstract class ServerActivity extends BaseActivity {
	protected RequestQueue mRequestQueue;

    public abstract void handResponse(String tag, Object response);

    public abstract void handError(String tag, VolleyError error);

	public RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(this);
            mRequestQueue.start();
		}
		return mRequestQueue;
	}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRequestQueue = Volley.newRequestQueue(this);
        mRequestQueue.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRequestQueue.stop();
    }
}
