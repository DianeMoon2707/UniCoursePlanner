package com.uni_course_planner.service.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.uni_course_planner.service.email.text.EmailText;

@Service
public class EmailService 
{
	private final JavaMailSender mailSender;

	public EmailService(JavaMailSender mailSender)
	{
		this.mailSender = mailSender;
	}

	public void sendEmail(String receiver, EmailText email)
	{
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(receiver);
		message.setSubject(email.getTopic());
		message.setText(email.getText());
		
		mailSender.send(message);
	}
}
