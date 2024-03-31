package com.message.message.OneToOneChat.Controller;

import com.message.message.OneToOneChat.ChatNotification;
import com.message.message.OneToOneChat.Entity.ChatMessage;
import com.message.message.OneToOneChat.Service.ChatMessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class OneChatMessageController {

    private final ChatMessageService chatMessageService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage message) {
        ChatMessage savedMessage = chatMessageService.saveChatMessage(message);
        messagingTemplate.convertAndSendToUser(
                message.getRecipientName(),
                "/topic/message",
                ChatNotification.builder()
                        .id(savedMessage.getId())
                        .recipientName(savedMessage.getRecipientName())
                        .senderName(savedMessage.getSenderName())
                        .content(savedMessage.getContent())
                        .build()
        );
    }

    @GetMapping("/messages/{senderName}/{recipientName}")
    public ResponseEntity<List<ChatMessage>> findChatMessages(
            @RequestParam("senderName") String senderName,
            @RequestParam("recipientName") String recipientName
    ) {
        return ResponseEntity.ok(chatMessageService.chatMessageList(senderName, recipientName));
    }
}
