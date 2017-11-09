package com.hwj.xm.exception.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.hwj.xm.constant.P2PConstant;
import com.hwj.xm.exception.ParamsException;
import com.hwj.xm.model.ResultInfo;

/**
 * 全局异常处理
 * @author Peter
 *
 */
@Component
public class GlobalExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception e) {
		/**
		 * 	1.	视图
		 * 	2.	json
		 */
		if (handler instanceof HandlerMethod) {
			
			
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			/**
			 * controller响应的内容判断 view json
			 */
			Method method = handlerMethod.getMethod();
			ResponseBody responseBody = method.getAnnotation(ResponseBody.class);
			if (responseBody!=null) {
				
				/**
				 * 响应json
				 */
				ResultInfo resultInfo = new ResultInfo();
				resultInfo.setCode(P2PConstant.OP_FAILED_CODE);
				resultInfo.setMsg(P2PConstant.OP_FAILED_MSG);
				if (e instanceof ParamsException) {
					ParamsException pe=(ParamsException) e;
					resultInfo.setCode(pe.getErrorCode());
					resultInfo.setMsg(pe.getErrorMsg());
				}
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json;charset=utf-8");
				PrintWriter pw = null;
				
				try {
					pw=response.getWriter();
				} catch (IOException ex) {
					ex.printStackTrace();
				}finally{
					if(null!=pw){
						pw.write(JSON.toJSONString(resultInfo));
						pw.flush();
						pw.close();
					}
				}
				return null;
			}else {
				/**
				 * 转到视图
				 */
				
				ModelAndView modelAndView=getDefaultModelAndView(request);
				if(e instanceof ParamsException){
					ParamsException pe=(ParamsException) e;
					modelAndView.addObject("errorMsg", pe.getErrorMsg());
				}
				return modelAndView;
				
			}
			
		}else {
			return getDefaultModelAndView(request);
		}
		
	}
	
	/**
	 * 异常的 ModelAndView 的处理
	 * @param request
	 * @return
	 */
	private ModelAndView getDefaultModelAndView(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error");
		modelAndView.addObject("ctx",request.getContextPath());
		modelAndView.addObject("uri",request.getRequestURI());
		modelAndView.addObject("errorMsg",P2PConstant.OP_FAILED_MSG);
		return modelAndView;
	}

}
