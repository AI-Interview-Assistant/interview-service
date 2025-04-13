package com.interviewX.interview_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "chat_messages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessage {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private String sender;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String message;

  @Column(nullable = false)
  private LocalDateTime timestamp;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "interview_id", nullable = false)
  private Interview interview;
}
