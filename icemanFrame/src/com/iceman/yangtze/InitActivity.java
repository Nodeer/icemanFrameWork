package com.iceman.yangtze;

import android.os.Message;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.crittercism.app.Crittercism;
import com.google.gson.Gson;
import com.iceman.yangtze.manager.*;
import com.iceman.yangtze.model.VersionModel;
import com.iceman.yangtze.util.SharePreUtil;

import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.FinalDb;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import com.iceman.yangtze.interfaces.SingleDialogFragmentCallBack;
import com.iceman.yangtze.model.CtripDialogExchangeModel.CtripDialogExchangeModelBuilder;
import com.iceman.yangtze.model.CtripDialogType;
import com.iceman.yangtze.model.CtripServerExchangeModel.ServerSendModelBuilder;
import com.iceman.yangtze.util.DeviceUtil;

import java.util.HashMap;

/**
 * 首页activity
 *
 * @author 徐斌
 * @date 2013.9.23
 */
public class InitActivity extends ServerActivity implements View.OnClickListener, SingleDialogFragmentCallBack {

    private TextView mPromptText;

    private ImageView logImg;
    private TextView text1, text2;
    private int initNum = 3;
    private boolean animOk = false;

    private Handler mInitHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:// 初始化图片加载工具,数据库
                    initNum--;
                    initNet();
                    break;
                case 2:// 初始化网络,连接首页
                    initNum--;
                    mPromptText.append("\n检查新版本");
                    getNewVersion();
                    break;
                case 3:// 检查新版本
                    initNum--;
                    if (animOk) {
                        // finish();
                    }
                    break;
            }
        }
    };

    private void getNewVersion() {
        ServerSendModelBuilder builder = new ServerSendModelBuilder();
        builder.setUrl("")/* .setServerInterface(mGetNewVersionServerInterface) */.setTag(NetEventTag.GET_NEW_VERSION);
        ServerManager.getTargetNow(builder.create(), null, this);
    }

    @Override
    public void onClick(View v) {
        // Intent intent = new Intent();
        switch (v.getId()) {
            default:
                break;
        }
    }

    @Override
    public void handResponse(String tag, Object response) {
        if (tag.equals(NetEventTag.GET_HOME_PAGE)) {
            System.out.println(response);
        } else if (tag.equals(NetEventTag.GET_NEW_VERSION)) {

        }
    }

    @Override
    public void handError(String tag, VolleyError error) {
        if (tag.equals(NetEventTag.GET_HOME_PAGE)) {
            System.out.println(error);
        } else if (tag.equals(NetEventTag.GET_NEW_VERSION)) {
            mInitHandler.sendMessage(mInitHandler.obtainMessage(3));
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppController.isHomeAlive = true;
        setContentView(R.layout.init_screen_layout);
        new Thread() {
            @Override
            public void run() {
                initConfig();
                mInitHandler.sendEmptyMessage(1);
            }
        }.start();
        logImg = (ImageView) findViewById(R.id.cjdx_img);
        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        Animation ani = AnimationUtils.loadAnimation(this, R.anim.init_img_disappear);
        ani.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                text1.setVisibility(View.GONE);
                text2.setVisibility(View.GONE);
                logImg.setVisibility(View.GONE);
                animOk = true;
                if (initNum == 0) {
                    // startActivity(new Intent(InitActivity.this, MainActivity.class));
                    // finish();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        logImg.startAnimation(ani);
        mPromptText = (TextView) findViewById(R.id.init_prompt);
        mPromptText.setText("初始化图片加载工具,数据库");

    }

    private void initNet() {
        try {
            // 通过context得到ConnectivityManager连接管理
            ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            // 通过ConnectivityManager得到NetworkInfo网络信息
            NetworkInfo info = manager.getActiveNetworkInfo();
            // 获取NetworkInfo中的apn信息
            if (info == null) {
                CtripDialogExchangeModelBuilder builder = new CtripDialogExchangeModelBuilder(CtripDialogType.SINGLE, "");
                builder.setBackable(false).setSpaceable(false).setDialogContext("未连接互联网,请打开网络连接!");
                DialogManager.showDialogFragment(getSupportFragmentManager(), builder.creat(), null);
            } else {
                initHomePage();
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtripDialogExchangeModelBuilder builder = new CtripDialogExchangeModelBuilder(CtripDialogType.SINGLE, "");
            builder.setBackable(false).setSpaceable(false).setDialogContext("检查网络状态出现异常,请退出重试!");
            DialogManager.showDialogFragment(getSupportFragmentManager(), builder.creat(), null);
        }
    }

    private void initHomePage() {
        ServerSendModelBuilder builder = new ServerSendModelBuilder();
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("houseId", "123");
        map.put("esateId", "30");
        builder.setbShowProcess(true).setbIsCancleable(true).setUrl("http://192.168.1.33:8080/ihouse/House/houseDetailService.rest").setIsPost(true)
                .setTag(NetEventTag.GET_HOME_PAGE);
        ServerManager.getTargetNow(builder.create(), null, this);
    }

    public void initConfig() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        DisplayMetrics dm = DeviceUtil.getAppWidthAndHeight(this);
        AppController.diaplayHeight = dm.heightPixels;
        AppController.diaplayWidth = dm.widthPixels;

        AppController.fb = FinalBitmap.create(this);
        AppController.fb.configLoadfailImage(R.drawable.loading_default);
        AppController.fb.configLoadingImage(R.drawable.loading_default);
        AppController.fd = FinalDb.create(this);
    }

    @Override
    public void onSingleBtnClick(String tag) {
        finish();
    }
}
