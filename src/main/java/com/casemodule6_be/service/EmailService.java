package com.casemodule6_be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
    private JavaMailSender javaMailSender;
    @Autowired
    EmailService(JavaMailSender javaMailSender){
        this.javaMailSender=javaMailSender;
    }
    @Async
    public void sendEmail(String to,String subject,String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
    public void forgotMail(String to,String subject,String password) throws MessagingException{
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setFrom("leanhtuan0198@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);
        String htmlMsg = "Email :" + to + "<br><b>Password: </b> "+ password + "<br><a href=\"http://localhost:4200/login\">Click here to Login</a></p>";
        message.setContent(htmlMsg,"text/html");
        javaMailSender.send(message);
    }
}
