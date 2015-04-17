package com.zjq.servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


/**
 * 处理页面编码格式的过滤器
 * @author ZJQ
 */
public class CharactorFilter implements Filter {
	// 字符编码
	String encoding = null;

	public void destroy() {
		encoding = null;
	}


	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// 判断字符编码是否为空
		if(encoding != null){
			// 设置request的编码格式
			request.setCharacterEncoding(encoding);
			// 设置response字符编码
     		response.setContentType("text/html; charset="+encoding);
		}
		// 传递给下一过滤器
		chain.doFilter(request, response);
	}


	public void init(FilterConfig filterConfig) throws ServletException {
		// 获取初始化参数
		encoding = filterConfig.getInitParameter("encoding");
		//SessionFactoryProvider.
	}
}
