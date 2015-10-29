package com.mastek.dstl.service.notification;

import org.apache.log4j.Logger;
import org.apache.solr.common.SolrDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	private static final Logger logger = Logger.getLogger(EmailService.class);

	@Autowired
	private JavaMailSender javaMailSender = new JavaMailSenderImpl();

	public void sendEmail(String emailAddress, String keywords, SolrDocument solrDocument) {
		String msg = "Found keywords:\t" + keywords;
		msg += "\nThe related URL is:\t" + solrDocument.getFieldValue("url");
		System.out.println(msg);
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(emailAddress);
			mailMessage.setSubject("Event Detected For  "+ "\"" +  keywords  + "\"");
			mailMessage.setText(msg);
			javaMailSender.send(mailMessage);
		} catch (Exception e) {
			logger.error("Failed.", e);
		}
	}

}
