package com.interviewX.interview_service.dtos;

import com.interviewX.interview_service.model.InterviewStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class InterviewResponse {
    private UUID id;
    private String title;
    private String interviewer;
    private String candidate;
    private LocalDateTime scheduledTime;
    private InterviewStatus status;
    private String jitsiMeetingLink;
}
