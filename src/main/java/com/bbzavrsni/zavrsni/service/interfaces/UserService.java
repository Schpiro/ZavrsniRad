package com.bbzavrsni.zavrsni.service.interfaces;

import com.bbzavrsni.zavrsni.model.dto.MessageGroupDTO;
import com.bbzavrsni.zavrsni.model.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> findAllUsers();

    List<MessageGroupDTO> findAllGroups(Long userId);

}
