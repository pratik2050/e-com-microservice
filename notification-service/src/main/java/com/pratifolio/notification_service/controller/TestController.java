package com.pratifolio.notification_service.controller;

import com.pratifolio.notification_service.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestService testm;

    @GetMapping("/test-mail")
    public String sendMail(@RequestParam("to") String to, @RequestParam("subject") String subject, @RequestParam("body") String body) {
        testm.sendPlainText(to, subject, body);

        return "Sending...";
    }

}
