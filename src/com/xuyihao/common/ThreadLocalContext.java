package com.xuyihao.common;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 定义线程上下文，保存Spring容器上下文
 * 
 * @Author Xuyh created at 2016年8月25日 上午10:45:16
 */
public class ThreadLocalContext {
	private static WebApplicationContext webApplicationContext = null;

	public static void setContext(ServletContext servletContext) {
		if (webApplicationContext == null) {
			webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		}
	}

	public static WebApplicationContext setContextAndRet(ServletContext servletContext) {
		if (webApplicationContext == null) {
			webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		}
		return webApplicationContext;
	}

	public static WebApplicationContext getWebApplicationContext() {
		if (webApplicationContext != null) {
			return webApplicationContext;
		} else {
			return null;
		}
	}

	public static Object getBean(String beanName) {
		if (webApplicationContext != null) {
			return webApplicationContext.getBean(beanName);
		} else {
			return null;
		}
	}
}
