package com.interviewX.interview_service.service;

import com.interviewX.interview_service.model.ChatMessage;

import java.util.List;
import java.util.UUID;

public interface ChatService {
  ChatMessage sendMessage(UUID interviewId, ChatMessage chatMessage);
  List<ChatMessage> getMessagesByInterviewId(UUID interviewId);
  void deleteMessage(UUID messageId);
}
