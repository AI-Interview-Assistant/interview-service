package com.interviewX.interview_service.service.impl;

import com.interviewX.interview_service.constant.ChatConstants;
import com.interviewX.interview_service.constant.InterviewConstants;
import com.interviewX.interview_service.exception.ChatMessageNotFoundException;
import com.interviewX.interview_service.exception.InterviewNotFoundException;
import com.interviewX.interview_service.model.ChatMessage;
import com.interviewX.interview_service.model.Interview;
import com.interviewX.interview_service.repository.ChatMessageRepository;
import com.interviewX.interview_service.repository.InterviewRepository;
import com.interviewX.interview_service.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

  private final ChatMessageRepository chatMessageRepository;
  private final InterviewRepository interviewRepository;

  @Transactional
  @Override
  public ChatMessage sendMessage(UUID interviewId, ChatMessage chatMessage) {
    Interview interview = interviewRepository.findById(interviewId)
        .orElseThrow(() -> new InterviewNotFoundException(InterviewConstants.INTERVIEW_NOT_FOUND));

    chatMessage.setInterview(interview);
    chatMessage.setTimestamp(LocalDateTime.now());

    return chatMessageRepository.save(chatMessage);
  }

  @Override
  public List<ChatMessage> getMessagesByInterviewId(UUID interviewId) {
    try {
      return chatMessageRepository.findByInterviewId(interviewId);
    } catch (Exception e) {
      throw new RuntimeException("Error retrieving chat messages: " + e.getMessage());
    }
  }

  @Transactional
  @Override
  public void deleteMessage(UUID messageId) {
    ChatMessage chatMessage = chatMessageRepository.findById(messageId)
        .orElseThrow(() -> new ChatMessageNotFoundException(ChatConstants.ERROR_CHAT_NOT_FOUND));
    chatMessageRepository.delete(chatMessage);
  }
}
