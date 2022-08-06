package com.bbzavrsni.zavrsni.service.interfaces;

import com.bbzavrsni.zavrsni.command.MessageGroupCommand;
import com.bbzavrsni.zavrsni.model.dto.MessageGroupDTO;
import com.bbzavrsni.zavrsni.model.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> findAllUsers();

    List<MessageGroupDTO> findAllGroups(Long userId);

    MessageGroupDTO saveMessageGroup(MessageGroupCommand messageGroupCommand);

    List<UserDTO> getUsersInGroup(Long groupId);
}
