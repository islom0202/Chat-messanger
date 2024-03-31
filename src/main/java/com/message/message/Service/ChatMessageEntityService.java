package com.message.message.Service;

import com.message.message.Controller.ChatMessage;
import com.message.message.Entity.ChatMessageEntity;
import com.message.message.Repository.ChatMessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChatMessageEntityService {

    private ChatMessageRepository chatMessageRepository;

    public void saveMessage(ChatMessage chatMessage){
        ChatMessageEntity chatMessageEntity = new ChatMessageEntity();
        chatMessageEntity.setSender(chatMessage.getSender());
        chatMessageEntity.setContent(chatMessage.getContent());
        chatMessageEntity.setTime(chatMessage.getTime());
        chatMessageEntity.setType(chatMessage.getType());

        chatMessageRepository.save(chatMessageEntity);
    }

    public List<ChatMessageEntity> findAll() {
        return chatMessageRepository.findAll();
    }
}
