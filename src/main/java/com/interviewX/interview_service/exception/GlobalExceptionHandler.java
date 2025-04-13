package com.interviewX.interview_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(InterviewNotFoundException.class)
  public ResponseEntity<Map<String, String>> handleInterviewNotFound(InterviewNotFoundException ex) {
    return generateErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
  }

  @ExceptionHandler(ChatMessageNotFoundException.class)
  public ResponseEntity<Map<String, String>> handleChatMessageNotFound(ChatMessageNotFoundException ex) {
    return generateErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
  }

  @ExceptionHandler(JitsiMeetingNotFoundException.class)
  public ResponseEntity<Map<String, String>> handleJitsiMeetingNotFound(JitsiMeetingNotFoundException ex) {
    return generateErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
    return generateErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred: " + ex.getMessage());
  }

  private ResponseEntity<Map<String, String>> generateErrorResponse(HttpStatus status, String message) {
    Map<String, String> errorResponse = new HashMap<>();
    errorResponse.put("error", message);
    return new ResponseEntity<>(errorResponse, status);
  }
}
