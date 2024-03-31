package com.message.message.OneToOneChat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ChatNotification {

    private Long id;
    private String senderName;
    private String recipientName;
    private String content;
    private Date timestamp;
}
