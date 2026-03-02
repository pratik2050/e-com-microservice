package com.pratifolio.notification_service.model;

public record MailTemplate(
        String to,
        String subject,
        String body
) {
}
