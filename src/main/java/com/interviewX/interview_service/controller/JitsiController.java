package com.interviewX.interview_service.controller;

import com.interviewX.interview_service.dtos.JitsiMeetingResponse;
import com.interviewX.interview_service.model.JitsiMeeting;
import com.interviewX.interview_service.service.JitsiService;
import com.interviewX.interview_service.util.ApiResponse;
import com.interviewX.interview_service.constant.JitsiConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.interviewX.interview_service.mapper.JitsiMeetingMapper.toResponse;

@RestController
@RequestMapping("jitsi")
@RequiredArgsConstructor
public class JitsiController {

  private final JitsiService jitsiService;

  @PostMapping("/create/{interviewId}")
  public ResponseEntity<ApiResponse<JitsiMeetingResponse>> createMeeting(@PathVariable UUID interviewId) {
    try {
      JitsiMeeting meeting = jitsiService.createMeeting(interviewId);
      return ResponseEntity.ok(new ApiResponse<>(true, JitsiConstants.JITSI_MEETING_CREATED, toResponse(meeting)));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
    }
  }

  @GetMapping("/{interviewId}")
  public ResponseEntity<ApiResponse<JitsiMeetingResponse>> getMeeting(@PathVariable UUID interviewId) {
    try {
      JitsiMeeting meeting = jitsiService.getMeetingByInterviewId(interviewId);
      return ResponseEntity.ok(new ApiResponse<>(true, JitsiConstants.JITSI_MEETING_FOUND, toResponse(meeting)));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
    }
  }

  @DeleteMapping("/{interviewId}")
  public ResponseEntity<ApiResponse<String>> deleteMeeting(@PathVariable UUID interviewId) {
    try {
      jitsiService.deleteMeeting(interviewId);
      return ResponseEntity.ok(new ApiResponse<>(true, JitsiConstants.JITSI_MEETING_DELETED, "Meeting removed successfully"));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
    }
  }
}
