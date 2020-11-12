package com.tathink.univa.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.tathink.univa.controller.form.UserLoginForm;
import com.tathink.univa.domain.Manager;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession httpSession = request.getSession();
		Manager form = (Manager) httpSession.getAttribute("user");
		
		/*if(form == null) {
			// 로그인 페이지로 리다이렉트?
			// response.~
			//throw new Exception("로그인 필요.");
		}
		httpSession.setMaxInactiveInterval(60*60);*/
		return true;
	}
	
	public void preHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// controller 후 처리
	}
}
