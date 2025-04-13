package com.interviewX.interview_service.service.impl;

import com.interviewX.interview_service.dtos.InterviewRequest;
import com.interviewX.interview_service.dtos.InterviewResponse;
import com.interviewX.interview_service.exception.InterviewNotFoundException;
import com.interviewX.interview_service.model.Interview;
import com.interviewX.interview_service.model.InterviewStatus;
import com.interviewX.interview_service.repository.InterviewRepository;
import com.interviewX.interview_service.service.InterviewService;
import com.interviewX.interview_service.constant.InterviewConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class InterviewServiceImpl implements InterviewService {

  private final InterviewRepository interviewRepository;

  private InterviewResponse mapToResponse(Interview interview) {
    log.info("InterviewRequest: {}", interview);
    return InterviewResponse.builder()
        .id(interview.getId())
        .title(interview.getTitle())
        .interviewer(interview.getInterviewer())
        .candidate(interview.getCandidate())
        .scheduledTime(interview.getScheduledTime())
        .status(interview.getStatus())
        .jitsiMeetingLink(interview.getJitsiMeetingLink())
        .build();
  }

  @Transactional
  @Override
  public InterviewResponse createInterview(InterviewRequest request) {
    Interview interview = Interview.builder()
        .title(request.getTitle())
        .interviewer(request.getInterviewerId())
        .candidate(request.getCandidateId())
        .scheduledTime(request.getScheduledTime())
        .status(request.getStatus())
        .jitsiMeetingLink(request.getJitsiMeetingLink())
        .build();

    return mapToResponse(interviewRepository.save(interview));
  }

  @Override
  public InterviewResponse getInterviewById(UUID interviewId) {
    Interview interview = interviewRepository.findById(interviewId)
        .orElseThrow(() -> new InterviewNotFoundException(InterviewConstants.INTERVIEW_NOT_FOUND + interviewId));
    return mapToResponse(interview);
  }

  @Override
  public List<InterviewResponse> getAllInterviews() {
    return interviewRepository.findAll().stream()
        .map(this::mapToResponse)
        .collect(Collectors.toList());
  }

  @Transactional
  @Override
  public InterviewResponse updateInterview(UUID interviewId, InterviewRequest request) {
    Interview interview = interviewRepository.findById(interviewId)
        .orElseThrow(() -> new InterviewNotFoundException(InterviewConstants.INTERVIEW_NOT_FOUND + interviewId));

    interview.setTitle(request.getTitle());
    interview.setScheduledTime(request.getScheduledTime());
    interview.setStatus(request.getStatus());
    interview.setJitsiMeetingLink(request.getJitsiMeetingLink());
    interview.setInterviewer(request.getInterviewerId());
    interview.setCandidate(request.getCandidateId());

    return mapToResponse(interviewRepository.save(interview));
  }

  @Transactional
  @Override
  public void deleteInterview(UUID interviewId) {
    Interview interview = interviewRepository.findById(interviewId)
        .orElseThrow(() -> new InterviewNotFoundException(InterviewConstants.INTERVIEW_NOT_FOUND + interviewId));
    interviewRepository.delete(interview);
  }

  @Transactional
  @Override
  public InterviewResponse updateInterviewStatus(UUID interviewId, InterviewStatus status) {
    Interview interview = interviewRepository.findById(interviewId)
        .orElseThrow(() -> new InterviewNotFoundException(InterviewConstants.INTERVIEW_NOT_FOUND + interviewId));
    interview.setStatus(status);
    return mapToResponse(interviewRepository.save(interview));
  }
}
