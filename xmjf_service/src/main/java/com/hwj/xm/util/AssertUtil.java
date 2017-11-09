package com.hwj.xm.util;


import com.hwj.xm.exception.ParamsException;

public class AssertUtil {
	
	public static void isTrue(Boolean flag,String errorMsg) {
		if(flag){
			throw new ParamsException(errorMsg);
		}
	}
	
	
	public static void isTrue(Boolean flag,String errorMsg,Integer errorCode) {
		if(flag){
			throw new ParamsException(errorMsg,errorCode);
		}
	}
	

	
}
