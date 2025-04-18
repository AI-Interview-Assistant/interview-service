package com.interviewX.interview_service.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  public static String formatDateTime(LocalDateTime dateTime) {
    return dateTime != null ? dateTime.format(FORMATTER) : null;
  }

  public static LocalDateTime parseDateTime(String dateTimeStr) {
    return dateTimeStr != null ? LocalDateTime.parse(dateTimeStr, FORMATTER) : null;
  }

  private DateTimeUtil() {
    // Prevent instantiation
  }
}
