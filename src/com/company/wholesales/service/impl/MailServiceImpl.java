package com.company.wholesales.service.impl;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import com.company.wholesales.service.MailService;
import com.wholesales.exception.MailException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
 

public class MailServiceImpl implements MailService {
	
	
	private static Logger logger = LogManager.getLogger(MailServiceImpl.class);


		public MailServiceImpl() {
			
		}
	 
	@Override
	public void sendEmail(String to, String subject, String plainText) throws MailException {
		 
		if(logger.isDebugEnabled()) logger.debug("to: {}; subject: {}; plainText: {}", to, subject, plainText);
		
		try {
			Email email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("wings.broken1310@gmail.com", PASSWORD));
			email.setSSLOnConnect(true);
			email.setFrom("hasna.1310.ub@gmail.com");
			email.setSubject(subject);
			email.setMsg(plainText);
			email.addTo(to);
			email.send();
		}catch(EmailException e) {
			logger.error("Sending to "+to+"...", e);
			throw new MailException(e.getMessage(), e);
		}

	}
	
	
	
	public void sendHTML(String from, String subject, String htmlMessage, String... to) throws MailException{

		try {
			// Create the email message
			HtmlEmail email = new HtmlEmail();
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("wings.broken1310@gmail.com", PASSWORD));
			email.setSSLOnConnect(true);
			email.setFrom(from);
			email.setSubject(subject);
			email.setHtmlMsg(htmlMessage);

			email.addTo(to);

			email.send();
			System.out.println("Email Sent");
		}
		catch(EmailException e) {
			e.printStackTrace();
			throw new MailException();
		}
	}


	
	
	
	
	
 
	
	
	
	
	public static final String PASSWORD = "wingsdown";
}
