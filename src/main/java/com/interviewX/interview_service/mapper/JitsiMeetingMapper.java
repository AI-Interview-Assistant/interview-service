package com.interviewX.interview_service.mapper;

import com.interviewX.interview_service.dtos.JitsiMeetingResponse;
import com.interviewX.interview_service.model.JitsiMeeting;

public class JitsiMeetingMapper {

  public static JitsiMeetingResponse toResponse(JitsiMeeting meeting) {
    JitsiMeetingResponse response = new JitsiMeetingResponse();
    response.setId(meeting.getId());
    response.setMeetingUrl(meeting.getMeetingUrl());
    response.setCreatedAt(meeting.getCreatedAt());
    response.setExpiresAt(meeting.getExpiresAt());
    response.setInterviewId(meeting.getInterview().getId());
    return response;
  }
}
