package com.bbzavrsni.zavrsni.repository.interfaces;

import com.bbzavrsni.zavrsni.model.pojo.MessageRecipient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRecipientRepository extends JpaRepository<MessageRecipient, Long> {
}
