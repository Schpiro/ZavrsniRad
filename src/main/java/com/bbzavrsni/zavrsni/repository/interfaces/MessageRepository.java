package com.bbzavrsni.zavrsni.repository.interfaces;

import com.bbzavrsni.zavrsni.model.pojo.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Long> {
    List<Message> findAllByRecipient_Recipient_Id(Long recipientId);

    List<Message> findAllByRecipient_Recipient_IdAndCreator_Id(Long recipientId, Long creator);

    List<Message> findAllByRecipient_RecipientGroup_Id(Long groupId);
}