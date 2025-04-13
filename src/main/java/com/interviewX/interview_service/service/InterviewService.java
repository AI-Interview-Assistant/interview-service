package com.interviewX.interview_service.service;

import com.interviewX.interview_service.dtos.InterviewRequest;
import com.interviewX.interview_service.dtos.InterviewResponse;
import com.interviewX.interview_service.model.InterviewStatus;

import java.util.List;
import java.util.UUID;

public interface InterviewService {
  InterviewResponse createInterview(InterviewRequest request);
  InterviewResponse getInterviewById(UUID interviewId);
  List<InterviewResponse> getAllInterviews();
  InterviewResponse updateInterview(UUID interviewId, InterviewRequest updatedRequest);
  void deleteInterview(UUID interviewId);
  InterviewResponse updateInterviewStatus(UUID interviewId, InterviewStatus status);
}
