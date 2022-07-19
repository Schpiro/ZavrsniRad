package com.bbzavrsni.zavrsni.model.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "creator_id")
    private User creator;
    private String messageBody;
    private LocalDateTime createDate;
    @ManyToOne(targetEntity = Message.class)
    @JoinColumn(name = "parent_message_id")
    private Message parentMessage;

    @OneToOne(mappedBy = "message")
    private MessageRecipient recipient;

    public Message(User creator, String messageBody, Message parentMessage) {
        this.creator = creator;
        this.messageBody = messageBody;
        this.createDate = LocalDateTime.now();
        this.parentMessage = parentMessage;
    }
}
