package com.iceman.yangtze.model;

import java.util.ArrayList;
import java.util.HashMap;

import android.view.View.OnClickListener;

import com.iceman.yangtze.interfaces.BaseServerInterface;

public class CtripServerExchangeModel {
	public ServerSendModelBuilder builder;

	public CtripServerExchangeModel(ServerSendModelBuilder builder) {
		this.builder = builder;
	}

	public boolean isbIsCancleable() {
		return builder.bIsCancleable;
	}

	public boolean isbShowProcess() {
		return builder.bShowProcess;
	}

	public String getProcessText() {
		return builder.processText;
	}

	public boolean isPost() {
		return builder.isPost;
	}

	public String getTag(){
        return builder.tag;
    }

	public String getUrl() {
		return builder.url;
	}
	public Object getRequest() {
		return builder.request;
	}

//	public HashMap<String, String> getParams() {
//		return builder.params;
//	}

	public BaseServerInterface getServerInterface() {
		return builder.baseServerInterfaces;
	}

	public OnClickListener getCancleLoadingListener() {
		return builder.cancleLoadingListener;
	}

	public void setServerInterface(BaseServerInterface baseServerInterface) {
		builder.setServerInterface(baseServerInterface);
	}

	public static class ServerSendModelBuilder {
		/**
		 * 服务是否可取消 (默认false)
		 */
		protected boolean bIsCancleable = false;
		/**
		 * 是否显示转圈 (默认false)
		 */
		protected boolean bShowProcess = false;
		/**
		 * 转圈文案 (默认“”)
		 */
		protected String processText = "";
		/**
		 * 该服务涉及加载控件
		 */
		protected BaseServerInterface baseServerInterfaces = null;
		/**
		 * 取消服务叉图标，点击监听（出消框以外外的逻辑）
		 */
		protected OnClickListener cancleLoadingListener;
		/** 网址 */
		protected String url;
//		/** 参数 */
//		protected HashMap<String, String> params;
		/** 是否是用post */
		protected boolean isPost = false;
		/** 服务使用的tag */
		protected String tag;
        /**服务请求model*/
        protected Object request;

		public ServerSendModelBuilder() {
		}

		public ServerSendModelBuilder setbIsCancleable(boolean bIsCancleable) {
			this.bIsCancleable = bIsCancleable;
			return this;
		}

		public ServerSendModelBuilder setbShowProcess(boolean bShowProcess) {
			this.bShowProcess = bShowProcess;
			return this;
		}

		public ServerSendModelBuilder setProcessText(String processText) {
			this.processText = processText;
			return this;
		}

		public ServerSendModelBuilder setJumpCode(String jumpCode) {
			return this;
		}

		public ServerSendModelBuilder setUrl(String url) {
			this.url = url;
			return this;
		}

		public ServerSendModelBuilder setIsPost(boolean isPost) {
			this.isPost = isPost;
			return this;
		}
		public ServerSendModelBuilder setRequestModel(Object request) {
			this.request = request;
			return this;
		}

//		public ServerSendModelBuilder setParams(HashMap<String, String> params) {
//			this.params = params;
//			return this;
//		}

		public ServerSendModelBuilder setServerInterface(BaseServerInterface baseServerInterface) {
			if (baseServerInterfaces == null) {
				baseServerInterfaces = baseServerInterface;
			}
			return this;
		}

		public ServerSendModelBuilder setCancleLoadingListener(OnClickListener cancleLoadingListener) {
			this.cancleLoadingListener = cancleLoadingListener;
			return this;
		}

		public void setTag(String tag) {
			this.tag = tag;
		}

		/**
		 * 创建获取服务使用的model
		 * 
		 * @return
		 */
		public CtripServerExchangeModel create() {
			CtripServerExchangeModel sendModel = new CtripServerExchangeModel(this);
			return sendModel;
		}

	}
}