package com.bbzavrsni.zavrsni.repository.interfaces;

import com.bbzavrsni.zavrsni.model.pojo.Message;
import com.bbzavrsni.zavrsni.model.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Long> {
    List<Message> findAllByRecipient_Username(String recipient);

    List<Message> findAllByRecipient_Groups(User recipient);
}
