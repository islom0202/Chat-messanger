package com.message.message.OneToOneChat.Service;

import com.message.message.OneToOneChat.Entity.ChatMessage;
import com.message.message.OneToOneChat.Entity.ChatRoom;
import com.message.message.OneToOneChat.Repository.ChatRoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public String getChatRoomName(ChatMessage message) {
        ChatRoom room = chatRoomRepository.findBySenderNameAndRecipientNameAndChatName(message.getSenderName(), message.getRecipientName(),message.getChatName());
        if (room != null){
            return room.getChatName();
        }else {
            return createChatName(message.getSenderName(), message.getRecipientName());
        }
    }

    private String createChatName(String senderName, String recipientName) {
        String chatName = String.format("%s_%s",senderName, recipientName);
        ChatRoom senderRecipient = ChatRoom.builder()
                .chatName(chatName)
                .senderName(senderName)
                .recipientName(recipientName)
                .build();

        ChatRoom recipientSender = ChatRoom.builder()
                .chatName(chatName)
                .senderName(recipientName)
                .recipientName(senderName)
                .build();
        chatRoomRepository.save(senderRecipient);
        chatRoomRepository.save(recipientSender);
        return chatName;
    }
}
