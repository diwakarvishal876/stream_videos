package com.streaming.notification_service.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationEvent {
    private String recipientEmail;
    private String title;
    private String message;
    private String status;
}