package com.pratifolio.notification_service.service;

import com.pratifolio.notification_service.model.MailTemplate;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public ResponseEntity<String> sendMail(MailTemplate mailTemplate) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(mailTemplate.to());
            message.setSubject(mailTemplate.subject());
            message.setText(mailTemplate.body());
            mailSender.send(message);

            return new ResponseEntity<>("Mail Sent Successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error Sending Mail: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public void sendHtml(String to, String subject, String htmlBody) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true); // true means this is HTML
        mailSender.send(message);
    }
}
