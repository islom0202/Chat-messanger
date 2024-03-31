package com.message.message.OneToOneChat.Repository;

import com.message.message.OneToOneChat.Entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long> {
    ChatRoom findBySenderNameAndRecipientNameAndChatName(String senderName, String recipientName, String chatName);
}
