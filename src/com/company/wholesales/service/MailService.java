package com.company.wholesales.service;

import com.wholesales.exception.MailException;

public interface MailService {

	public void sendEmail (String to,String subject, String plainText)
			throws MailException;

}
