package com.company.wholesales.service.impl;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import com.company.wholesales.service.MailService;
import com.jal.wholesales.dao.config.ConfigurationManager;
import com.jal.wholesales.dao.util.ConstantConfigUtils;
import com.wholesales.exception.MailException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MailServiceImpl implements MailService {
	private static final String CFGM_PFX = "mail.";
	private static final String SERVER = CFGM_PFX + "server";
	private static final String PORT = CFGM_PFX + "port";
	private static final String ACCOUNT = CFGM_PFX + "account";
	private static final String PASSWORD = CFGM_PFX + "password";

	private static Logger logger = LogManager.getLogger(MailServiceImpl.class);

	public MailServiceImpl() {

	}

	@Override
	public void sendEmail(String from, String subject, String text, String... to) throws MailException {

		logger.traceEntry();

		ConfigurationManager CM = ConfigurationManager.getInstance();

		try {
			if (logger.isDebugEnabled()) {
				logger.debug("Sending Email from " + from + " to " + to + "...");
			}

			Email email = new SimpleEmail();
			email.setHostName(CM.getInstance().getParameter(ConstantConfigUtils.WHOLESALES_PROPERTIES, SERVER));
			email.setSmtpPort(
					Integer.valueOf(CM.getInstance().getParameter(ConstantConfigUtils.WHOLESALES_PROPERTIES, PORT)));
			email.setAuthenticator(
					(new DefaultAuthenticator(CM.getParameter(ConstantConfigUtils.WHOLESALES_PROPERTIES, ACCOUNT),
							CM.getParameter(ConstantConfigUtils.WHOLESALES_PROPERTIES, PASSWORD))));
			email.setSSLOnConnect(true);
			email.setFrom(from);
			email.setSubject(subject);
			email.setMsg(text);
			email.addTo(to);
			email.send();

			logger.traceExit();

		} catch (EmailException ee) {
			logger.error("Sending from " + from + " to " + to + "...", ee);
			throw new MailException(ee.getMessage(), ee);
		}
	}

	public void sendHTML(String from, String subject, String htmlMessage, String... to) throws MailException {

		logger.traceEntry();

		ConfigurationManager CM = ConfigurationManager.getInstance();

		try {

			if (logger.isDebugEnabled()) {
				logger.debug("Sending HTML from " + from + " to " + to + "...");
			}

			HtmlEmail email = new HtmlEmail();
			email.setHostName(CM.getParameter(ConstantConfigUtils.WHOLESALES_PROPERTIES, SERVER));
			email.setSmtpPort(Integer.valueOf(CM.getParameter(ConstantConfigUtils.WHOLESALES_PROPERTIES, PORT)));
			email.setAuthenticator(
					new DefaultAuthenticator(CM.getParameter(ConstantConfigUtils.WHOLESALES_PROPERTIES, ACCOUNT),
							CM.getParameter(ConstantConfigUtils.WHOLESALES_PROPERTIES, PASSWORD)));
			email.setSSLOnConnect(true);
			email.setFrom(from);
			email.setSubject(subject);
			email.setHtmlMsg(htmlMessage);
			email.addTo(to);
			email.send();

			logger.traceExit();

		} catch (EmailException ee) {
			if (logger.isErrorEnabled()) {
				logger.error("Sending from " + from + " to " + to + "...", ee);
			}
			throw new MailException(ee.getMessage(), ee);
		}
	}
}