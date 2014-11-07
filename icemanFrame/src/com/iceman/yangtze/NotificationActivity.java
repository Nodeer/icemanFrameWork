package com.iceman.yangtze;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;

import com.iceman.yangtze.interfaces.Constants;

import java.io.Serializable;


/**
 * Created by iceman.xu on 2014/10/27.
 * 封装了一些通用的代码,用来简化绑定广播,接收消息的功能
 */
public abstract class NotificationActivity extends FragmentActivity {
    protected MyBroadCastReceiver mReceiver;

    protected class MyBroadCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            int code = intent.getIntExtra(Constants.MESSAGE_CODE, Constants.MESSAGE_FAIL);
            String message = intent.getStringExtra(Constants.MESSAGE_CONTENT);
            Serializable data = intent.getSerializableExtra(Constants.MESSAGE_DATA);
            onMessageReceive(action, code, message, data);
        }
    }

    protected abstract void onMessageReceive(String action, int code, String message, Serializable data);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mReceiver = new MyBroadCastReceiver();
        IntentFilter filter = new IntentFilter();
        String[] actions = listReceiveActions();
        for (String s : actions) {
            filter.addAction(s);
        }
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(this);
        manager.registerReceiver(mReceiver, filter);
    }

    protected abstract String[] listReceiveActions();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
    }
}
