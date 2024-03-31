package com.message.message.OneToOneChat.Repository;

import com.message.message.OneToOneChat.Entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OneChatMessageRepository extends JpaRepository<ChatMessage,Long> {
    List<ChatMessage> findBySenderNameAndRecipientName(String senderName, String recipientName);
}
