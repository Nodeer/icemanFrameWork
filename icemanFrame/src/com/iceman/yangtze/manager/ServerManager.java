package com.iceman.yangtze.manager;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import android.support.v4.app.FragmentManager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.iceman.yangtze.ServerActivity;
import com.iceman.yangtze.ServerFragment;
import com.iceman.yangtze.model.CtripDialogExchangeModel.CtripDialogExchangeModelBuilder;
import com.iceman.yangtze.model.CtripDialogType;
import com.iceman.yangtze.model.CtripServerExchangeModel;
import com.iceman.yangtze.widget.BaseDialogFragmentV2;

public class ServerManager {

    public static void getTargetNow(final CtripServerExchangeModel model, final ServerFragment fragment, final ServerActivity baseActivityV2) {
        BaseDialogFragmentV2 dialogFragmentV2 = null;
        if (model.isbShowProcess()) {
            FragmentManager fragmentManager = null;
            if (baseActivityV2 != null) {
                fragmentManager = baseActivityV2.getSupportFragmentManager();
            } else if (fragment != null) {
                fragmentManager = fragment.getFragmentManager();
            }
            if (fragmentManager != null) {
                CtripDialogExchangeModelBuilder builder = new CtripDialogExchangeModelBuilder(CtripDialogType.PROGRESS, "");
                builder.setNetTag(model.getTag()).setBackable(false).setSpaceable(false).setDialogContext(model.getProcessText());
                if (model.isbIsCancleable()) {
                    dialogFragmentV2 = DialogManager.showDialogFragment(fragmentManager, builder.creat(), fragment);
                } else {
                    dialogFragmentV2 = DialogManager.showDialogFragment(fragmentManager,
                            builder.setBussinessCancleable(false).creat(), fragment);
                }
            }
        }

        final BaseDialogFragmentV2 dialog = dialogFragmentV2;

        final StringRequest request = new StringRequest(model.isPost() ? Request.Method.POST : Request.Method.GET, model.getUrl(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (model.getServerInterface() != null) {
                            model.getServerInterface().bussinessSuccess(response);
                        }
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                        if (fragment != null) {
                            fragment.handResponse(model.getTag(), response);
                        } else if (baseActivityV2 != null) {
                            baseActivityV2.handResponse(model.getTag(), response);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (model.getServerInterface() != null) {
                    model.getServerInterface().bussinessFail("");
                }
                if (dialog != null) {
                    dialog.dismiss();
                }
                if (fragment != null) {
                    fragment.handError(model.getTag(), error);
                    CtripDialogExchangeModelBuilder builder = new CtripDialogExchangeModelBuilder(CtripDialogType.SINGLE, "error");
                    DialogManager.showDialogFragment(fragment.getChildFragmentManager(), builder.creat(), fragment);
                } else if (baseActivityV2 != null) {
                    baseActivityV2.handError(model.getTag(), error);
                    CtripDialogExchangeModelBuilder builder = new CtripDialogExchangeModelBuilder(CtripDialogType.SINGLE, "error");
                    DialogManager.showDialogFragment(baseActivityV2.getSupportFragmentManager(), builder.creat(), null);
                }

            }
        }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                if (model.isPost()) {
                    Map<String, String> map = new HashMap<String, String>();
                    Object obj = model.getRequest();
                    Field[] fs = obj.getClass().getDeclaredFields();
                    for (int i = 0; i < fs.length; i++) {
                        Field f = fs[i];
                        f.setAccessible(true);
                        String val = null;
                        try {
                            val = (String) f.get(obj);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        map.put(f.getName(), val);
                    }
                    return map;
                } else {
                    return super.getParams();
                }
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
//                map.put("Accept","application/json");
//                map.put("App-Key","");
//                map.put("App-Secret","a5ae6fee5b88c146d0ea6a52c329f757");
//                map.put("App_Time","1403505612448");
                map.put("Content-Type", "application/json;charset=UTF-8");
//                map.put("Accept-Encoding","gzip,*");
                return map;
            }
        };

        if (model.getServerInterface() != null) {
            model.getServerInterface().bussinessStart();
        }

        if (fragment != null) {
            fragment.getRequestQueue().add(request);
        } else if (baseActivityV2 != null) {
            baseActivityV2.getRequestQueue().add(request);
        }
        // if (model.isPost()) {
        // NetClient.getInstance().post(model.getUrl(), model.getParams(), model.getServerInterface(), baseDialogFragmentV2);
        // } else {
        // NetClient.getInstance().get(model.getUrl(), model.getServerInterface(), baseDialogFragmentV2);
        // }

    }
}
