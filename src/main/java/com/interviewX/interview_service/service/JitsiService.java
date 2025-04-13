package com.interviewX.interview_service.service;

import com.interviewX.interview_service.model.JitsiMeeting;

import java.util.UUID;

public interface JitsiService {
  JitsiMeeting createMeeting(UUID interviewId);
  JitsiMeeting getMeetingByInterviewId(UUID interviewId);
  void deleteMeeting(UUID interviewId);
}
