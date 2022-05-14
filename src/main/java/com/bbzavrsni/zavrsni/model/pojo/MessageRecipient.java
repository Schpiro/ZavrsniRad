package com.bbzavrsni.zavrsni.model.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
public class MessageRecipient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="recipient_id")
    private User recipient;
    @ManyToOne(targetEntity = MessageGroup.class)
    @JoinColumn(name="recipient_group_id")
    private UserGroup recipientGroup;
    @OneToOne(targetEntity = Message.class)
    @JoinColumn(name = "message_id")
    private Message message;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageRecipient that = (MessageRecipient) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
