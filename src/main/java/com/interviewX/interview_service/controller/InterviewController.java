package com.interviewX.interview_service.controller;

import com.interviewX.interview_service.dtos.InterviewRequest;
import com.interviewX.interview_service.dtos.InterviewResponse;
import com.interviewX.interview_service.exception.InterviewNotFoundException;
import com.interviewX.interview_service.service.InterviewService;
import com.interviewX.interview_service.constant.InterviewConstants;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("interviews")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class InterviewController {

  private final InterviewService interviewService;

  @PostMapping
  public ResponseEntity<?> createInterview(@Valid @RequestBody InterviewRequest request) {
    try {
      System.out.println(request);
      InterviewResponse saved = interviewService.createInterview(request);
      return ResponseEntity.ok(saved);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body(InterviewConstants.ERROR_CREATING_INTERVIEW + e.getMessage());
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getInterview(@PathVariable UUID id) {
    try {
      InterviewResponse interview = interviewService.getInterviewById(id);
      return ResponseEntity.ok(interview);
    } catch (InterviewNotFoundException e) {
      return ResponseEntity.status(404).body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body(InterviewConstants.ERROR_FETCHING_INTERVIEW + e.getMessage());
    }
  }

  @GetMapping
  public ResponseEntity<?> getAllInterviews() {
    try {
      List<InterviewResponse> interviews = interviewService.getAllInterviews();
      return ResponseEntity.ok(interviews);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body(InterviewConstants.ERROR_FETCHING_INTERVIEWS + e.getMessage());
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateInterview(@PathVariable UUID id, @RequestBody InterviewRequest request) {
    try {
      InterviewResponse updated = interviewService.updateInterview(id, request);
      return ResponseEntity.ok(updated);
    } catch (InterviewNotFoundException e) {
      return ResponseEntity.status(404).body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body(InterviewConstants.ERROR_UPDATING_INTERVIEW + e.getMessage());
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteInterview(@PathVariable UUID id) {
    try {
      interviewService.deleteInterview(id);
      return ResponseEntity.noContent().build();
    } catch (InterviewNotFoundException e) {
      return ResponseEntity.status(404).body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body(InterviewConstants.ERROR_DELETING_INTERVIEW + e.getMessage());
    }
  }
}
