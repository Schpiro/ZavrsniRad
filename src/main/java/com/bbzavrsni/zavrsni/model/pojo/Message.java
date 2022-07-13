package com.bbzavrsni.zavrsni.model.pojo;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Entity
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
    @ManyToOne(targetEntity = User.class)
    @JoinTable(
            name = "message_recipient",
            joinColumns = @JoinColumn(name = "message_id"),
            inverseJoinColumns = @JoinColumn(name = "recipient_id")
    )
    private User recipient;
    /*
    @ManyToOne(targetEntity = MessageGroup.class)
    @JoinTable(
            name = "message_recipient",
            joinColumns = @JoinColumn(name = "message_id"),
            inverseJoinColumns = @JoinColumn(name = "recipient_group_id")
    )
    private MessageGroup recipientGroup;
*/
}
