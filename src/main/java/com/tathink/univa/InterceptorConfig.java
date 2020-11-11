package com.tathink.univa;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.tathink.univa.interceptor.LoginInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(new LoginInterceptor())
									.excludePathPatterns("/css/**", "/external/**", "/favicon/**", "/images/**", "/js/**","/scss/**","/widget/**")
									.excludePathPatterns("/user/login", "/solution/**", "/css/**")
									.addPathPatterns("/**");
	}

}
