package com.oracle.oc2.omc.utils;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

/**
 * The i18n files locate in src/main/resources directory.
 * 
 * @author huabzhao
 * 
 */
@Component
public class I18NUtil {
	private static Logger log = Logger.getLogger(I18NUtil.class);

	@Autowired
	private ResourceBundleMessageSource resource;

	/**
	 * @param key
	 *            the key to lookup up, such as 'system.name'
	 * @return
	 */
	public String getMessage(String key) {
		try {
			return resource.getMessage(key, null, Locale.CHINESE);
		} catch (NoSuchMessageException ex) {
			log.warn("", ex);
		}
		return key;
	}

	/**
	 * @param key
	 *            the key to lookup up, such as 'system.name'
	 * @param args
	 *            Array of arguments that will be filled in for params within
	 *            the message (params look like "{0}", "{1,date}", "{2,time}"
	 *            within a message), or null if none.
	 * @return
	 * @throws NoSuchMessageException
	 */
	public String getMessage(String key, Object[] args) {

		try {
			return resource.getMessage(key, args, Locale.CHINESE);
		} catch (NoSuchMessageException ex) {
			log.warn("", ex);
		}
		return key;
	}
}
