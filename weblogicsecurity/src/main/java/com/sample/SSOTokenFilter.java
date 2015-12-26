package com.sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
public class SSOTokenFilter implements Filter {
	public SSOTokenFilter() {
		super();
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HeaderMapRequestWrapper requestWrapper = new HeaderMapRequestWrapper(
				req);
		requestWrapper.addHeader("SamplePerimeterAtnToken", "username=welcome");
		chain.doFilter(requestWrapper, response);
		System.out.println("SSOTokenFilter URL:" + req.getRequestURL());
	}

	public void destroy() {
	}
}
