package com.interviewX.interview_service.mapper;

import com.interviewX.interview_service.dtos.ChatMessageRequest;
import com.interviewX.interview_service.dtos.ChatMessageResponse;
import com.interviewX.interview_service.model.ChatMessage;
import com.interviewX.interview_service.model.Interview;

import java.time.LocalDateTime;

public class ChatMessageMapper {

    public static ChatMessage toEntity(ChatMessageRequest request, Interview interview) {
        return ChatMessage.builder()
                .sender(request.getSender())
                .message(request.getMessage())
                .timestamp(LocalDateTime.now())
                .interview(interview)
                .build();
    }

    public static ChatMessageResponse toResponse(ChatMessage message) {
        ChatMessageResponse response = new ChatMessageResponse();
        response.setId(message.getId());
        response.setSender(message.getSender());
        response.setMessage(message.getMessage());
        response.setTimestamp(message.getTimestamp());
        response.setInterviewId(message.getInterview().getId());
        return response;
    }
}
