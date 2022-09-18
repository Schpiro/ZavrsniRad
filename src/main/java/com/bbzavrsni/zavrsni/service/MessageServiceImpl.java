package com.bbzavrsni.zavrsni.service;

import com.bbzavrsni.zavrsni.command.MessageCommand;
import com.bbzavrsni.zavrsni.model.dto.MessageDTO;
import com.bbzavrsni.zavrsni.model.pojo.Message;
import com.bbzavrsni.zavrsni.model.pojo.MessageGroup;
import com.bbzavrsni.zavrsni.model.pojo.User;
import com.bbzavrsni.zavrsni.repository.interfaces.MessageRepository;
import com.bbzavrsni.zavrsni.service.interfaces.MessageService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    @PersistenceContext
    private final EntityManager entityManager;

    public MessageServiceImpl(MessageRepository messageRepository, EntityManager entityManager) {
        this.messageRepository = messageRepository;
        this.entityManager = entityManager;
    }

    @Override
    public List<MessageDTO> findAllByUser(Long userId) {
        return messageRepository.findAllByRecipient_Id(userId).stream().map(this::mapMessageToDTO).collect(Collectors.toList());
    }

    @Override
    public List<MessageDTO> getConversationWithUser(Long userId, Long senderId) {
        return messageRepository.fetchConversation(userId, senderId).stream().map(this::mapMessageToDTO).sorted(Comparator.comparing(MessageDTO::getCreationDate)).collect(Collectors.toList());
    }

    @Override
    public List<MessageDTO> findAllByGroup(Long groupId) {
        return messageRepository.findAllByRecipientGroup_Id(groupId).stream().map(this::mapMessageToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<MessageDTO> sendMessage(MessageCommand messageCommand, Long userId) {
        Message sentMessage = messageRepository.save(mapCommandToMessage(messageCommand, userId));
        return Optional.of(mapMessageToDTO(sentMessage));
    }

    private Message mapCommandToMessage(final MessageCommand messageCommand, Long userId) {
        return new Message(entityManager.getReference(User.class, userId),
                messageCommand.getMessageBody(),
                messageCommand.getParentMessage() != null ? entityManager.getReference(Message.class, messageCommand.getParentMessage()) : null,
                messageCommand.getRecipientId() != null ? entityManager.getReference(User.class, messageCommand.getRecipientId()) : null,
                messageCommand.getRecipientGroupId() != null ? entityManager.getReference(MessageGroup.class, messageCommand.getRecipientGroupId()) : null);
    }

    private MessageDTO mapMessageToDTO(final Message message) {
        return new MessageDTO(message.getCreator().getId(),
                message.getCreator().getUsername(),
                message.getRecipient() != null ? message.getRecipient().getId() : null,
                message.getRecipientGroup() != null ? message.getRecipientGroup().getId() : null,
                message.getRecipientGroup() != null ? message.getRecipientGroup().getGroupParticipant().stream().map(User::getId).collect(Collectors.toList()) : null,
                message.getCreateDate().toString(),
                message.getMessageBody());
    }

}
