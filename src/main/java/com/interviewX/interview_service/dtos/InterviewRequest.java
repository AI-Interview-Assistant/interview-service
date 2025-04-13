package com.interviewX.interview_service.dtos;

import com.interviewX.interview_service.model.InterviewStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InterviewRequest {
    private String title;
    private String interviewerId;
    private String candidateId;
    private LocalDateTime scheduledTime;
    private InterviewStatus status;
    private String jitsiMeetingLink;
}
