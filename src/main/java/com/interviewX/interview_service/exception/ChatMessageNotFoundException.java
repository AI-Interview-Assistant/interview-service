package com.interviewX.interview_service.exception;

public class ChatMessageNotFoundException extends RuntimeException {
  public ChatMessageNotFoundException(String message) {
    super(message);
  }
}
