package com.interviewX.interview_service.dtos;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ChatMessageResponse {
    private UUID id;
    private String sender;
    private String message;
    private LocalDateTime timestamp;
    private UUID interviewId;
}
