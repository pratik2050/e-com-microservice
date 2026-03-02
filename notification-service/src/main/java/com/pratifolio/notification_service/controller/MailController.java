package com.pratifolio.notification_service.controller;

import com.pratifolio.notification_service.model.MailTemplate;
import com.pratifolio.notification_service.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("notify")
public class MailController {

    @Autowired
    private MailService mailService;

    @GetMapping("send-mail")
    public ResponseEntity<?> sendMail(@RequestBody MailTemplate mailTemplate) {
        return mailService.sendMail(mailTemplate);
    }

}
