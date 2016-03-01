package com.elita.hackaton.service;

public interface EmailService {
	void send(String to, String subject, String body);
}
