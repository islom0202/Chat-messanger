package com.message.message.DisconnectListener;

import com.message.message.Controller.ChatMessage;
import com.message.message.Controller.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebsocketDisconnectListener {

    private final SimpMessageSendingOperations sendingOperations;

    @EventListener
    public void handleWebsocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = headerAccessor.getSessionAttributes().get("username").toString();

        if (username != null) {
            log.info("user disconnected: {}", username);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String time = sdf.format(new Date());
            var chatMessage = ChatMessage.builder()
                    .type(MessageType.LEAVE)
                    .time(time)
                    .sender(username)
                    .build();

            sendingOperations.convertAndSend("/topic/public", chatMessage);
        }
    }
}
