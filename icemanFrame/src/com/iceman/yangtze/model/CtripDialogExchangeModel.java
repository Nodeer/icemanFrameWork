package com.iceman.yangtze.model;

import java.io.Serializable;

import net.tsz.afinal.http.HttpHandler;
import android.view.Gravity;
import android.view.View.OnClickListener;

public class CtripDialogExchangeModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3685432164096360692L;
	public CtripDialogExchangeModelBuilder ctripDialogExchangeModelBuilder;

	public OnClickListener compatibilityListener;// 错误弹框 按键点击事件

	public OnClickListener compatibilityPositiveListener;

	public OnClickListener compatibilityNegativeListener;

	public CtripDialogExchangeModel(CtripDialogExchangeModelBuilder ctripDialogExchangeModelBuilder) {
		this.ctripDialogExchangeModelBuilder = ctripDialogExchangeModelBuilder;
	}

	public CtripDialogType getDialogType() {
		return ctripDialogExchangeModelBuilder.dialogType;
	}

	public String getDialogTitle() {
		return ctripDialogExchangeModelBuilder.dialogTitle;
	}

	public String getDialogContext() {
		return ctripDialogExchangeModelBuilder.dialogContext;
	}
	public boolean isHasTitle() {
		return ctripDialogExchangeModelBuilder.hasTitle;
	}

	public String getPostiveText() {
		return ctripDialogExchangeModelBuilder.postiveText;
	}

	public String getNegativeText() {
		return ctripDialogExchangeModelBuilder.negativeText;
	}

	public String getTag() {
		return ctripDialogExchangeModelBuilder.tag;
	}

	public String getSingleText() {
		return ctripDialogExchangeModelBuilder.singleText;
	}
	public HttpHandler getHttpHandler() {
		return ctripDialogExchangeModelBuilder.http;
	}

	public boolean isBussinessCancleable() {
		return ctripDialogExchangeModelBuilder.isBussinessCancleable;
	}

	public boolean isBackable() {
		return ctripDialogExchangeModelBuilder.isBackable;
	}

	public boolean isSpaceable() {
		return ctripDialogExchangeModelBuilder.isSpaceable;
	}

	public int getGravity() {
		return ctripDialogExchangeModelBuilder.gravity;
	}

    public String getNetTag() {
        return ctripDialogExchangeModelBuilder.netTag;
    }

//	public String getOldTag() {
//		return ctripDialogExchangeModelBuilder.oldTag;
//	}

//	public CtripDialogType getOldCtripDialogType() {
//		return ctripDialogExchangeModelBuilder.oldDialogType;
//	}

	public static class CtripDialogExchangeModelBuilder implements Serializable {
		/**
         * 
         */
		private static final long serialVersionUID = -3685432164096360693L;
		/**
		 * 弹出框类型
		 */
		private CtripDialogType dialogType = CtripDialogType.SINGLE;
//		/**
//		 * 弹出框类型
//		 */
//		private CtripDialogType oldDialogType = CtripDialogType.SINGLE;
		/**
		 * 弹出框标题
		 */
		private String dialogTitle = "";
		/**
		 * 弹出框内容
		 */
		private String dialogContext = "";
		/**
		 * 是否显示标题
		 */
		private boolean hasTitle = true;
		/**
		 * 确认按键
		 */
		private String postiveText = "";
		/**
		 * 取消按键
		 */
		private String negativeText = "";
		/**
		 * 单按键
		 */
		private String singleText = "";
		/**
		 * tag
		 */
		private String tag = "";
		/**
		 * 转换前oldTag
		 */
		private String oldTag = "";
		/**
		 * back可点（默认可点）
		 */
		private boolean isBackable = true;
		/**
		 * 空白可点（默认可点）
		 */
		private boolean isSpaceable = true;
		/**
		 * 服务可取消(默认可取消)
		 */
		private boolean isBussinessCancleable = true;
		/**
		 * 网络事件tag
		 */
		private String netTag;

		private transient HttpHandler http;

		private int gravity = Gravity.CENTER;

		public CtripDialogExchangeModelBuilder(CtripDialogType ctripHDDialogType, String tag) {
			this.dialogType = ctripHDDialogType;
			this.tag = tag;
		}
		public CtripDialogExchangeModelBuilder setTag(String tag) {
			this.tag = tag;
			return this;
		}
//		public CtripDialogExchangeModelBuilder setOldTag(String oldTag) {
//			this.oldTag = oldTag;
//			return this;
//		}
//
//		public CtripDialogExchangeModelBuilder setOldCtripDialogType(CtripDialogType oldDialogType) {
//			this.oldDialogType = oldDialogType;
//			return this;
//		}
		public CtripDialogExchangeModelBuilder setDialogType(CtripDialogType ctripHDDialogType) {
			this.dialogType = ctripHDDialogType;
			return this;
		}
		public CtripDialogExchangeModelBuilder setDialogTitle(String dialogTitle) {
			this.dialogTitle = dialogTitle;
			return this;
		}

		public CtripDialogExchangeModelBuilder setDialogContext(String dialogContext) {
			this.dialogContext = dialogContext;
			return this;
		}

		public CtripDialogExchangeModelBuilder setHasTitle(boolean hasTitle) {
			this.hasTitle = hasTitle;
			return this;
		}

		public CtripDialogExchangeModelBuilder setPostiveText(String postiveText) {
			this.postiveText = postiveText;
			return this;
		}

		public CtripDialogExchangeModelBuilder setNegativeText(String negativeText) {
			this.negativeText = negativeText;
			return this;
		}

		public CtripDialogExchangeModelBuilder setSingleText(String singleText) {
			this.singleText = singleText;
			return this;
		}

		public CtripDialogExchangeModelBuilder setBackable(boolean isBackable) {
			this.isBackable = isBackable;
			return this;
		}

		public CtripDialogExchangeModelBuilder setBussinessCancleable(boolean isBussinessCancleable) {
			this.isBussinessCancleable = isBussinessCancleable;
			return this;
		}

		public CtripDialogExchangeModelBuilder setSpaceable(boolean isSpaceable) {
			this.isSpaceable = isSpaceable;
			return this;
		}
		public CtripDialogExchangeModelBuilder setHttp(HttpHandler http) {
			this.http = http;
			return this;
		}

		public CtripDialogExchangeModelBuilder setGravity(int gravity) {
			this.gravity = gravity;
			return this;
		}
		public CtripDialogExchangeModel creat() {
			return new CtripDialogExchangeModel(this);
		}

        public CtripDialogExchangeModelBuilder setNetTag(String tag) {
            this.netTag = tag;
            return this;
        }
    }
}
