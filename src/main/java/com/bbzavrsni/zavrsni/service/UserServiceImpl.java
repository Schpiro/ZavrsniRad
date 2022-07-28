package com.bbzavrsni.zavrsni.service;

import com.bbzavrsni.zavrsni.command.MessageGroupCommand;
import com.bbzavrsni.zavrsni.model.dto.MessageGroupDTO;
import com.bbzavrsni.zavrsni.model.dto.UserDTO;
import com.bbzavrsni.zavrsni.model.pojo.MessageGroup;
import com.bbzavrsni.zavrsni.model.pojo.User;
import com.bbzavrsni.zavrsni.repository.interfaces.MessageGroupRepository;
import com.bbzavrsni.zavrsni.repository.interfaces.UserRepository;
import com.bbzavrsni.zavrsni.service.interfaces.UserService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final MessageGroupRepository messageGroupRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public UserServiceImpl(UserRepository userRepository, MessageGroupRepository messageGroupRepository, EntityManager entityManager) {
        this.userRepository = userRepository;
        this.messageGroupRepository = messageGroupRepository;
        this.entityManager = entityManager;
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return userRepository.findAll().stream().map(this::mapUserToDTO).collect(Collectors.toList());
    }

    @Override
    public List<MessageGroupDTO> findAllGroups(Long userId) {
        return messageGroupRepository.findAllByGroupParticipant_Id(userId).stream().map(this::mapGroupToDTO).collect(Collectors.toList());
    }

    @Override
    public MessageGroupDTO saveMessageGroup(MessageGroupCommand messageGroupCommand) {
        MessageGroup messageGroup = mapCommandToMessageGroup(messageGroupCommand);
        messageGroupRepository.save(messageGroup);
        return mapGroupToDTO(messageGroup);
    }

    private UserDTO mapUserToDTO(final User user) {
        return new UserDTO(user.getId(), user.getUsername());
    }

    private MessageGroupDTO mapGroupToDTO(final MessageGroup messageGroup) {
        return new MessageGroupDTO(messageGroup.getId(), messageGroup.getName(),messageGroup.getGroupParticipant().stream().map(x->x.getId().longValue()).collect(Collectors.toList()));
    }

    private MessageGroup mapCommandToMessageGroup(MessageGroupCommand messageGroupCommand){
        List<Long> userIds = messageGroupCommand.getGroupParticipants();
        String groupName = messageGroupCommand.getGroupName();
        List<User> listOfUsers = new ArrayList<>();
        for (Long userId: userIds) listOfUsers.add(entityManager.getReference(User.class, userId));
        return new MessageGroup(groupName,listOfUsers);
    }

}
