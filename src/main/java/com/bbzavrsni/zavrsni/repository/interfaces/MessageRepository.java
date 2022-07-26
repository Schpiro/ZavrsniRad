package com.bbzavrsni.zavrsni.repository.interfaces;

import com.bbzavrsni.zavrsni.model.pojo.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Long> {
    List<Message> findAllByRecipient_Recipient_Id(Long recipientId);

    List<Message> findAllByRecipient_Recipient_IdAndCreator_Id(Long recipientId, Long creator);

    @Query("SELECT msg FROM Message msg WHERE msg.creator.id=:creator and msg.recipient.recipient.id=:recipientId or msg.creator.id=:recipientId and msg.recipient.recipient.id=:creator")
    List<Message> fetchConversation(Long recipientId, Long creator);

    List<Message> findAllByRecipient_RecipientGroup_Id(Long groupId);
}