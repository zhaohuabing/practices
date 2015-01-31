package com.oracle.oc2.omc.presentation;

import java.io.Serializable;
import java.util.ArrayList;

public class TestBean implements Serializable {
	private String text = "xxxx";

	private int count;

	// getter setter

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public ArrayList<String> complete(String query) {
		System.out
				.println("#######################TestBean######################");
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
