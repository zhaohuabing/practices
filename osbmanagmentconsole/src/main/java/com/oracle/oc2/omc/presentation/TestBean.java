package com.oracle.oc2.omc.presentation;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.oracle.oc2.omc.utils.I18NUtil;

@Controller
public class TestBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 145923723570550043L;

	private static Logger log = Logger.getLogger(TestBean.class);

	@Autowired
	private I18NUtil i18NUtil;

	private String text = "xxxx";

	private int count;

	// getter setter

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
		log.info("set count");
	}

	public String getText() {

		log.info("*********get text**********");
		return i18NUtil.getMessage("system.name") + text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public ArrayList<String> complete(String query) {
		try {
			ArrayList<String> results = new ArrayList<String>();
			for (int i = 0; i < 10; i++)
				results.add("text" + i);
			return results;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ArrayList<String>();
	}

	public void increment() {
		count++;
	}

}
