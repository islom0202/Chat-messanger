package com.message.message.OneToOneChat.Controller;

import com.message.message.OneToOneChat.Entity.OneChatUser;
import com.message.message.OneToOneChat.Service.OneChatUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OneChatUserController {

    private final OneChatUserService chatUserService;

    @MessageMapping("/user.addOneChatUser")
    @SendTo("/user/topic")
    public OneChatUser addOneChatUser(@Payload OneChatUser user) {
        chatUserService.saveChatUser(user);
        return user;
    }

    @MessageMapping("/user.disconnectOneChatUser")
    @SendTo("/user/topic")
    public OneChatUser disconnectOneChatUser(@Payload OneChatUser user) {
        chatUserService.disconnectChatUser(user);
        return user;
    }

    @GetMapping("/users")
    public ResponseEntity<List<OneChatUser>> findConnectedUsers() {
        return ResponseEntity.ok(chatUserService.findConnectedUsers());
    }
}
