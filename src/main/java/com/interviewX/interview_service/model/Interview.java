package com.interviewX.interview_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "interviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Interview {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String interviewer;

  @Column(nullable = false)
  private String candidate;

  @Column(nullable = false)
  private LocalDateTime scheduledTime;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private InterviewStatus status;

  @Column(nullable = false, unique = true)
  private String jitsiMeetingLink;
}