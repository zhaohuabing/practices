package com.oracle.oc2.omc.presentation;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.oracle.oc2.omc.utils.Auth;
import com.oracle.oc2.omc.utils.I18NUtil;

@Controller
@Scope(value = "session")
public class User implements Serializable {

	private static final long serialVersionUID = -5847496590513282211L;

	private static Logger log = Logger.getLogger(User.class);

	private String username;
	private String password;

	@Autowired
	private Auth auth;

	@Autowired
	private I18NUtil i18NUtil;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void login() {

		try {
			auth.login(this.username, this.password);
		} catch (ServletException e) {
			AddErrorFacesMessage(i18NUtil.getMessage("login.err"));
			log.error("", e);
		} catch (IOException e) {
			AddErrorFacesMessage(i18NUtil.getMessage("login.err"));
			log.error("", e);
		}

	}

	public void logout() {
		try {
			auth.logout();
		} catch (IOException e) {
			AddErrorFacesMessage(i18NUtil.getMessage("logout.err"));
			log.error("", e);
		} catch (ServletException e) {
			AddErrorFacesMessage(i18NUtil.getMessage("logout.err"));
			log.error("", e);
		}
	}

	private void AddErrorFacesMessage(String message) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
				message, ""));
	}
}
