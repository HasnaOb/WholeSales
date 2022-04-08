package com.jal.wholesales.dao.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.company.wholesales.service.MailService;
import com.company.wholesales.service.impl.MailServiceImpl;

public class MailServiceTest {

	
private static Logger logger = LogManager.getLogger(MailServiceTest.class);	
	
	private MailService mailservice=null;
	
	public MailServiceTest() {
		mailservice = new MailServiceImpl();
	}
	
	public void testSendPlain() {
		logger.trace("Entrando...");
		
		try {
			String from = "hasna.1310.ub@gmail.com";
			String to = "hasna.1310.ub@gmail.com";
			mailservice.sendEmail(from , "Test", "Testeando el mail service");
			
			logger.info("Mail a "+to+": Enviado con éxito.");
		}catch (Exception e) {
			logger.error(e);			
		}
	}
	
	public static void main(String[] args) {
		MailServiceTest test = new MailServiceTest();
		test.testSendPlain();

	}
}
