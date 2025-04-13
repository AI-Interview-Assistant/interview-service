package com.interviewX.interview_service.controller;

import com.interviewX.interview_service.dtos.ChatMessageRequest;
import com.interviewX.interview_service.dtos.ChatMessageResponse;
import com.interviewX.interview_service.mapper.ChatMessageMapper;
import com.interviewX.interview_service.model.ChatMessage;
import com.interviewX.interview_service.model.Interview;
import com.interviewX.interview_service.repository.InterviewRepository;
import com.interviewX.interview_service.service.ChatService;
import com.interviewX.interview_service.constant.InterviewConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.interviewX.interview_service.mapper.ChatMessageMapper.*;

@Controller
@RestController
@RequestMapping("chat")
@RequiredArgsConstructor
public class ChatController {

  private final SimpMessagingTemplate messagingTemplate;
  private final ChatService chatService;
  private final InterviewRepository interviewRepository;

  @MessageMapping("/chat/{interviewId}")
  public void handleWebSocketMessage(@DestinationVariable UUID interviewId,
                                     ChatMessageRequest request) {
    try {
      Interview interview = interviewRepository.findById(interviewId)
          .orElseThrow(() -> new RuntimeException(InterviewConstants.INTERVIEW_NOT_FOUND));

      ChatMessage saved = chatService.sendMessage(interviewId, toEntity(request, interview));
      ChatMessageResponse response = toResponse(saved);

      messagingTemplate.convertAndSend("/topic/interview/" + interviewId, response);
    } catch (Exception e) {
      System.err.println(InterviewConstants.CHAT_ERROR + ": " + e.getMessage());
    }
  }

  @PostMapping("/{interviewId}")
  public ChatMessageResponse sendMessageViaRest(@PathVariable UUID interviewId,
                                                @RequestBody ChatMessageRequest request) {
    Interview interview = interviewRepository.findById(interviewId)
        .orElseThrow(() -> new RuntimeException(InterviewConstants.INTERVIEW_NOT_FOUND));

    ChatMessage saved = chatService.sendMessage(interviewId, toEntity(request, interview));
    return toResponse(saved);
  }

  @GetMapping("/{interviewId}")
  public List<ChatMessageResponse> getMessages(@PathVariable UUID interviewId) {
    return chatService.getMessagesByInterviewId(interviewId)
        .stream()
        .map(ChatMessageMapper::toResponse)
        .collect(Collectors.toList());
  }

  @DeleteMapping("/message/{messageId}")
  public String deleteMessage(@PathVariable UUID messageId) {
    chatService.deleteMessage(messageId);
    return "Message deleted successfully.";
  }
}
