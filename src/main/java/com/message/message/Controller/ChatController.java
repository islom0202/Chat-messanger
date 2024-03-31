package com.message.message.Controller;

import com.message.message.Entity.ChatMessageEntity;
import com.message.message.Service.ChatMessageEntityService;
import com.message.message.Service.ChatUserService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ChatController {

    private final ChatMessageEntityService chatMessageEntityService;
    private final ChatUserService chatUserService;

    public ChatController(ChatMessageEntityService chatMessageEntityService, ChatUserService chatUserService) {
        this.chatMessageEntityService = chatMessageEntityService;
        this.chatUserService = chatUserService;
    }

    @MessageMapping("/chat-sending")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String time = sdf.format(new Date());
        chatMessage.setTime(time);
        chatMessageEntityService.saveMessage(chatMessage);
        return chatMessage;
    }

    @MessageMapping("/chat-add-user")
    @SendTo("/topic/public")
    public ChatMessage addUser(
            @Payload ChatMessage chatMessage,
            SimpMessageHeaderAccessor headerAccessor
    ){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String time = sdf.format(new Date());
        chatMessage.setTime(time);
        headerAccessor.getSessionAttributes().put("username",chatMessage.getSender());
        chatUserService.saveUser(chatMessage);
        return chatMessage;
    }

    @MessageMapping("/chat-history")
    @SendTo("/topic/public")
    public List<ChatMessageEntity> chatHistory(){
        return chatMessageEntityService.findAll();
    }

    @MessageMapping("/typing")
    @SendTo("/topic/public")
    public String typing() {
        return "typing...";
    }

    @MessageMapping("/stop-typing")
    @SendTo("/topic/public")
    public String stopTyping() {
        return "";
    }
    @GetMapping
    public String form(){
        return "index";
    }
}
