package com.interviewX.interview_service.repository;

import com.interviewX.interview_service.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, UUID> {
  List<ChatMessage> findByInterviewId(UUID interviewId);
}
