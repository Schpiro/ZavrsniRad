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
    @GeneratedValue
    private Integer id;
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

    public Message(User creator, String messageBody, LocalDateTime createDate, Message parentMessage, MessageRecipient recipient) {
        this.creator = creator;
        this.messageBody = messageBody;
        this.createDate = createDate;
        this.parentMessage = parentMessage;
        this.recipient = recipient;
    }
}
