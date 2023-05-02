package br.com.branetlogistica.msclient.core.interceptor.service;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import br.com.branetlogistica.msclient.core.context.Context;
import br.com.branetlogistica.msclient.core.context.util.ContextUtil;

public class InterceptorRequest implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {

		if (request.getDispatcherType() != DispatcherType.REQUEST)
			return true;

		Context.setContextData(ContextUtil.toContextData(Context.getContextData(), request, object));
		return true;

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		Context.clear();
	}

}