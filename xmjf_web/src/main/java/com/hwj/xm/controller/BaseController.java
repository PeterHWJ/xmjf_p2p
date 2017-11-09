package com.hwj.xm.controller;

import com.hwj.xm.constant.P2PConstant;
import com.hwj.xm.model.ResultInfo;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;


public class BaseController {
	
	@ModelAttribute
	public void preMethod(HttpServletRequest request){
		request.setAttribute("ctx", request.getContextPath());
	}


	public ResultInfo failed(String msg){
		ResultInfo result=new ResultInfo();
		result.setMsg(msg);
		result.setCode(P2PConstant.OP_FAILED_CODE);
		return result;
	}

	public ResultInfo failed(){
		ResultInfo result=new ResultInfo();
		result.setMsg(P2PConstant.OP_FAILED_MSG);
		result.setCode(P2PConstant.OP_FAILED_CODE);
		return result;
	}

	public ResultInfo success(String msg){
		ResultInfo result=new ResultInfo();
		result.setMsg(msg);
		result.setCode(P2PConstant.OP_SUCCESS_CODE);
		return result;
	}

	public ResultInfo success(String msg,Object rst){
		ResultInfo result=new ResultInfo();
		result.setMsg(msg);
		result.setResult(rst);
		result.setCode(P2PConstant.OP_SUCCESS_CODE);
		return result;
	}

}
