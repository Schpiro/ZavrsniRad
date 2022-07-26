package com.bbzavrsni.zavrsni.service;

import com.bbzavrsni.zavrsni.model.dto.MessageGroupDTO;
import com.bbzavrsni.zavrsni.model.dto.UserDTO;
import com.bbzavrsni.zavrsni.model.pojo.MessageGroup;
import com.bbzavrsni.zavrsni.model.pojo.User;
import com.bbzavrsni.zavrsni.repository.interfaces.MessageGroupRepository;
import com.bbzavrsni.zavrsni.repository.interfaces.UserRepository;
import com.bbzavrsni.zavrsni.service.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final MessageGroupRepository messageGroupRepository;

    public UserServiceImpl(UserRepository userRepository, MessageGroupRepository messageGroupRepository) {
        this.userRepository = userRepository;
        this.messageGroupRepository = messageGroupRepository;
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return userRepository.findAll().stream().map(this::mapUserToDTO).collect(Collectors.toList());
    }

    @Override
    public List<MessageGroupDTO> findAllGroups(Long userId){
        return messageGroupRepository.findAllByGroupParticipant_Id(userId).stream().map(this::mapGroupToDTO).collect(Collectors.toList());
    }

    private UserDTO mapUserToDTO(final User user) {
        return new UserDTO(user.getId(), user.getUsername());
    }

    private MessageGroupDTO mapGroupToDTO(final MessageGroup messageGroup) {
        return new MessageGroupDTO(messageGroup.getId(), messageGroup.getName());
    }

}
