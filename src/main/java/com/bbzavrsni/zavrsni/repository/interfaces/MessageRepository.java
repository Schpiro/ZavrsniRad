package com.bbzavrsni.zavrsni.repository.interfaces;

import com.bbzavrsni.zavrsni.model.pojo.Message;
import com.bbzavrsni.zavrsni.model.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message,Long> {
    List<Message> findAllByRecipient_Recipient_Username(String recipient);

    List<Message> findAllByRecipient_RecipientGroup_Id(Integer groupId);
}
