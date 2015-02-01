package com.oracle.oc2.omc.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "../../../../../applicationContext.xml" })
public class I18NUtilTest {

	@Autowired
	private I18NUtil i18nUtil;

	@Test
	public void testGetMessage() {
		i18nUtil.getMessage("system.name").equals("OSB管理平台");
	}

	public void tetGetMessageDefault() {
		i18nUtil.getMessage("nosuchkey").equals("nosuchkey");
	}

}
