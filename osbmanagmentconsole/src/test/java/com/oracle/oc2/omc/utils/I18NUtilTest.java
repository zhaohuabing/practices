package com.oracle.oc2.omc.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "../../../../../config/applicationContext.xml" })
public class I18NUtilTest {

	@Autowired
	private I18NUtil i18nUtil;

	@Test
	public void testGetMessage() {
		i18nUtil.getMessage("junit.test1").equals("��Ԫ����");

		i18nUtil.getMessage("junit.test2", new String[] { "����1", "����2" })
				.equals("��Ԫ����1���Բ���2");
	}

	public void tetGetMessageDefault() {
		i18nUtil.getMessage("nosuchkey").equals("nosuchkey");
	}

}
