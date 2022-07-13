package com.bbzavrsni.zavrsni.model.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

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
    @ManyToOne(targetEntity = User.class)
    @JoinTable(
            name = "message_recipient",
            joinColumns = @JoinColumn(name = "message_id"),
            inverseJoinColumns = @JoinColumn(name = "recipient_id")
    )
    private User recipient;
    @ManyToOne(targetEntity = MessageGroup.class)
    @JoinTable(
            name = "message_recipient",
            joinColumns = @JoinColumn(name = "message_id"),
            inverseJoinColumns = @JoinColumn(name = "recipient_group_id")
    )
    private MessageGroup recipientGroup;

    public Message(User creator, String messageBody, LocalDateTime createDate, Message parentMessage, User recipient) {
        this.creator = creator;
        this.messageBody = messageBody;
        this.createDate = createDate;
        this.parentMessage = parentMessage;
        this.recipient = recipient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
