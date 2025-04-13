package com.interviewX.interview_service.repository;

import com.interviewX.interview_service.model.JitsiMeeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JitsiRepository extends JpaRepository<JitsiMeeting, UUID> {
  Optional<JitsiMeeting> findByInterviewId(UUID interviewId);
}
