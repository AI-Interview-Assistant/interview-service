package com.interviewX.interview_service.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class JitsiConfig {

  @Value("${jitsi.server.url}")
  private String jitsiServerUrl;

  @Value("${jitsi.jwt.secret}")
  private String jitsiJwtSecret;

  @Value("${jitsi.jwt.appId}")
  private String jitsiAppId;
}
