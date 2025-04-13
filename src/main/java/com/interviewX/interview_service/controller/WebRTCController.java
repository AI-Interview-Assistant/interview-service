package com.interviewX.interview_service.controller;

import com.interviewX.interview_service.model.WebRTCSignalMessage;
import com.interviewX.interview_service.service.WebRTCSignalingService;
import com.interviewX.interview_service.constant.InterviewConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class WebRTCController {

  private final SimpMessagingTemplate messagingTemplate;
  private final WebRTCSignalingService signalingService;

  @MessageMapping("/video/{interviewId}/signaling")
  public void handleSignaling(@DestinationVariable UUID interviewId, WebRTCSignalMessage message) {
    try {
      message.setTimestamp(LocalDateTime.now());
      signalingService.processSignal(interviewId, message);
      messagingTemplate.convertAndSendToUser(message.getReceiver(), "/queue/video", message);
    } catch (Exception e) {
      System.err.println(InterviewConstants.SIGNALING_ERROR + ": " + e.getMessage());
    }
  }
}
