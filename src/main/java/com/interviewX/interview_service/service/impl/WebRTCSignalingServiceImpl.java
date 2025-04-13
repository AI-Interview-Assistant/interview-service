package com.interviewX.interview_service.service.impl;

import com.interviewX.interview_service.constant.InterviewConstants;
import com.interviewX.interview_service.exception.InterviewNotFoundException;
import com.interviewX.interview_service.model.Interview;
import com.interviewX.interview_service.model.WebRTCSignalMessage;
import com.interviewX.interview_service.repository.InterviewRepository;
import com.interviewX.interview_service.service.WebRTCSignalingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class WebRTCSignalingServiceImpl implements WebRTCSignalingService {

  private final InterviewRepository interviewRepository;

  private final Map<UUID, WebSocketSession> sessionMap = new ConcurrentHashMap<>();

  @Override
  public void processSignal(UUID interviewId, WebRTCSignalMessage signal) {
    Interview interview = interviewRepository.findById(interviewId)
        .orElseThrow(() -> new InterviewNotFoundException(InterviewConstants.INTERVIEW_NOT_FOUND));

    WebSocketSession session = sessionMap.get(interviewId);
    if (session != null && session.isOpen()) {
      try {
        session.sendMessage(new TextMessage(signal.getContent()));
      } catch (IOException e) {
        throw new RuntimeException("Error sending WebRTC signal: " + e.getMessage());
      }
    }
  }

  @Override
  public void registerSession(UUID interviewId, WebSocketSession session) {
    sessionMap.put(interviewId, session);
  }

  @Override
  public void removeSession(UUID interviewId) {
    sessionMap.remove(interviewId);
  }
}
