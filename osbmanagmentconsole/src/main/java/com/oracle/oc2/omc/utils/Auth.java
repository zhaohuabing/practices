package com.oracle.oc2.omc.utils;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

/**
 * @author huabzhao
 * @Created on 2015-2-5
 */
@Component
public class Auth {
	private static final String APP_LOGIN_PAGE = "login.xhtml";
	private static final String APP_DEFAULT_PAGE = "default.xhtml";

	public void logout() throws IOException, ServletException {
		HttpServletRequest request = getHttpServletRequest();
		request.logout();

		redirect(APP_LOGIN_PAGE);

	}

	public void login(String username, String password)
			throws ServletException, IOException {
		HttpServletRequest request = getHttpServletRequest();

		request.login(username, password);

		redirect(APP_DEFAULT_PAGE);
	}

	private void redirect(String url) throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect(url);
	}

	private HttpServletRequest getHttpServletRequest() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext
				.getRequest();
		return request;
	}
}
