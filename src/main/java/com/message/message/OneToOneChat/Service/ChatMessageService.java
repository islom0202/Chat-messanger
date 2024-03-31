package com.message.message.OneToOneChat.Service;

import com.message.message.OneToOneChat.Entity.ChatMessage;
import com.message.message.OneToOneChat.Repository.OneChatMessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChatMessageService {

    private final OneChatMessageRepository oneChatMessageRepository;
    private final ChatRoomService chatRoomService;

    public ChatMessage saveChatMessage(ChatMessage message) {
        String chatName = chatRoomService.getChatRoomName(message);
        message.setChatName(chatName);
        oneChatMessageRepository.save(message);
        return message;
    }

    public List<ChatMessage> chatMessageList(String senderName, String recipientName) {

        List<ChatMessage> messages = oneChatMessageRepository.findBySenderNameAndRecipientName(senderName, recipientName);

        if (!messages.isEmpty()) {
            return messages;
        } else
            throw new ObjectNotFoundException("not found");
    }

    static class ObjectNotFoundException extends RuntimeException {
        public ObjectNotFoundException(String message) {
            super(message);
        }
    }
}