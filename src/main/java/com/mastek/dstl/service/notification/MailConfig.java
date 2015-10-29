package com.mastek.dstl.service.notification;

import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@PropertySource("classpath:mail.properties")
public class MailConfig {

	@Resource(name = "mailProperties")
	private Properties mailProperties;

	@Value(value = "${mail.username}")
	private String username;

	@Value(value = "${mail.password}")
	private String password;

	@Bean
	public JavaMailSender javaMailService() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setUsername(username);
		javaMailSender.setPassword(password);
		javaMailSender.setJavaMailProperties(mailProperties);
		return javaMailSender;
	}

	@Bean(name = "mailProperties")
	public PropertiesFactoryBean mapper() {
		PropertiesFactoryBean bean = new PropertiesFactoryBean();
		bean.setLocation(new ClassPathResource("mail.properties"));
		return bean;
	}

}