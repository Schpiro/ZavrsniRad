package com.bbzavrsni.zavrsni.repository.interfaces;

import com.bbzavrsni.zavrsni.model.dto.UserDTO;
import com.bbzavrsni.zavrsni.model.pojo.Message;
import com.bbzavrsni.zavrsni.model.pojo.MessageGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageGroupRepository extends JpaRepository<MessageGroup, Long> {
    List<MessageGroup> findAllByGroupParticipant_Id(Long recipientId);
}
