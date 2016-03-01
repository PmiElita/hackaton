package com.elita.hackaton.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.elita.hackaton.service.EmailService;

@Service("emailService")
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Async
	@Override
	public void send(final String to, final String subject, final String body) {
		final MimeMessage mimeMessage = mailSender.createMimeMessage();
		final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
		try {
			helper.addTo(to);
			helper.setSubject(subject);
			helper.setText(body, true);
		} catch (final MessagingException e) {
			throw new RuntimeException("email wasn't sent to " + to, e);
		}
		mailSender.send(mimeMessage);
	}

}
