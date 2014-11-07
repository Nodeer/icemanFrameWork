package com.iceman.yangtze.manager;

import android.os.Bundle;
import android.os.ResultReceiver;
public class IMMResult extends ResultReceiver {
	public int result = -1;

	public IMMResult() {
		super(null);
	}

	@Override
	public void onReceiveResult(int r, Bundle data) {
		result = r;
	}

	// poll result value for up to 500 milliseconds
	public int getResult() {
		return result;
	}
}
