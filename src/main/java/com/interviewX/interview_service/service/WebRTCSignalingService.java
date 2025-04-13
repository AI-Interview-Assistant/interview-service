package com.interviewX.interview_service.service;

import com.interviewX.interview_service.model.WebRTCSignalMessage;

import java.util.UUID;

public interface WebRTCSignalingService {
  void processSignal(UUID interviewId, WebRTCSignalMessage signal);
  void registerSession(UUID interviewId, org.springframework.web.socket.WebSocketSession session);
  void removeSession(UUID interviewId);
}
