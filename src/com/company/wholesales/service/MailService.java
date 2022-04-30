package com.company.wholesales.service;

import com.wholesales.exception.MailException;

public interface MailService {

	void sendEmail(String from, String subject, String text, String[] to) throws MailException;

	public void sendHTML(String from, String subject, String htmlMessage, String... to) throws MailException;

}
