package com.interviewX.interview_service.dtos;

import lombok.Data;

@Data
public class ChatMessageRequest {
    private String sender;
    private String message;
}
