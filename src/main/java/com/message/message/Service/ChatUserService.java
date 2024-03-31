package com.message.message.Service;

import com.message.message.Controller.ChatMessage;
import com.message.message.Entity.ChatUser;
import com.message.message.Repository.ChatUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChatUserService {

    private ChatUserRepository chatUserRepository;

    public void saveUser(ChatMessage user){
        ChatUser result = new ChatUser();
        result.setUsername(user.getSender());
        chatUserRepository.save(result);
    }
}
