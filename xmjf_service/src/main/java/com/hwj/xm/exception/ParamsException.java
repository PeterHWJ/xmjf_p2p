package com.hwj.xm.exception;

public class ParamsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorMsg="操作失败";
	private Integer errorCode=300;
	
	public ParamsException(String errorMsg) {
		super(errorMsg);
		this.errorMsg = errorMsg;
	}
	public ParamsException(String errorMsg, Integer errorCode) {
		super(errorMsg);
		this.errorMsg = errorMsg;
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	
	

}
