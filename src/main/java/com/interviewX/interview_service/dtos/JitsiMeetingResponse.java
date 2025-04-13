package com.interviewX.interview_service.dtos;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class JitsiMeetingResponse {
  private UUID id;
  private String meetingUrl;
  private LocalDateTime createdAt;
  private LocalDateTime expiresAt;
  private UUID interviewId;
}
