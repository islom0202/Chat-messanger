package com.message.message.Entity;

import com.message.message.Controller.MessageType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "chat_message")
public class ChatMessageEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sender;

    private String content;

    private String time;

    @Enumerated(EnumType.STRING)
    private MessageType type;
}
