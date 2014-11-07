package com.iceman.yangtze.widget;


public interface LoadingCallBackListener {

	/**
	 * 服务加载成功时的响应方法
	 * 
	 * @param token
	 *            服务的Token
	 * @param responseModel
	 *            服务的response model
	 * @param isGoback
	 *            TODO
	 */
	public void businessSuccess(String data);

	/**
	 * 服务加载失败时的响应方法
	 * 
	 * @param token
	 *            服务的Token
	 * @param responseModel
	 *            服务的response model
	 * @param isGoback
	 *            TODO
	 */
	public void businessFail(String data);

	/**
	 * 服务取消加载时的响应方法
	 * 
	 * @param token
	 *            服务的Token
	 * @param responseModel
	 *            服务的response model
	 */
	public void businessCancel(String data);
}
