package com.interviewX.interview_service.repository;

import com.interviewX.interview_service.model.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, UUID> {
}
