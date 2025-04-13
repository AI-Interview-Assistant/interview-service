package com.interviewX.interview_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "jitsi_meetings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JitsiMeeting {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @OneToOne
  @JoinColumn(name = "interview_id", nullable = false, unique = true)
  private Interview interview;

  @Column(nullable = false, unique = true)
  private String meetingUrl;

  @Column(nullable = false)
  private LocalDateTime createdAt;

  @Column(nullable = false)
  private LocalDateTime expiresAt;
}
