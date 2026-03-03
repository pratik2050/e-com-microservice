package com.pratifolio.notification_service.model.dto;

public record MailTemplate(
        String to,
        String subject,
        String body
) {
}
