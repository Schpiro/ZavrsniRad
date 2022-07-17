package com.bbzavrsni.zavrsni.model.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class MessageRecipient {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(targetEntity = Message.class)
    @JoinColumn(name = "message_id")
    private Message message;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "recipient_id")
    private User recipient;

    @ManyToOne(targetEntity = MessageGroup.class)
    @JoinColumn(name = "recipient_group_id")
    private MessageGroup recipientGroup;
}
