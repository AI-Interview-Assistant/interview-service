package com.interviewX.interview_service.service.impl;

import com.interviewX.interview_service.constant.InterviewConstants;
import com.interviewX.interview_service.constant.JitsiConstants;
import com.interviewX.interview_service.exception.InterviewNotFoundException;
import com.interviewX.interview_service.exception.JitsiMeetingNotFoundException;
import com.interviewX.interview_service.model.Interview;
import com.interviewX.interview_service.model.JitsiMeeting;
import com.interviewX.interview_service.repository.InterviewRepository;
import com.interviewX.interview_service.repository.JitsiRepository;
import com.interviewX.interview_service.service.JitsiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JitsiServiceImpl implements JitsiService {

  private final InterviewRepository interviewRepository;
  private final JitsiRepository jitsiRepository;

  @Transactional
  @Override
  public JitsiMeeting createMeeting(UUID interviewId) {
    Interview interview = interviewRepository.findById(interviewId)
        .orElseThrow(() -> new InterviewNotFoundException(InterviewConstants.INTERVIEW_NOT_FOUND + interviewId));

    JitsiMeeting meeting = JitsiMeeting.builder()
        .interview(interview)
        .meetingUrl(JitsiConstants.JITSI_BASE_URL + "/" + interviewId)
        .createdAt(LocalDateTime.now())
        .expiresAt(LocalDateTime.now().plusHours(1)) // for example: 1 hour duration
        .build();

    return jitsiRepository.save(meeting);
  }

  @Override
  public JitsiMeeting getMeetingByInterviewId(UUID interviewId) {
    return jitsiRepository.findByInterviewId(interviewId)
        .orElseThrow(() -> new JitsiMeetingNotFoundException(JitsiConstants.JITSI_MEETING_NOT_FOUND));
  }

  @Transactional
  @Override
  public void deleteMeeting(UUID interviewId) {
    JitsiMeeting meeting = getMeetingByInterviewId(interviewId);
    jitsiRepository.delete(meeting);
  }
}
