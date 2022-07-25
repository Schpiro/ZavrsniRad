package com.bbzavrsni.zavrsni.service;

import com.bbzavrsni.zavrsni.command.MessageCommand;
import com.bbzavrsni.zavrsni.model.dto.MessageDTO;
import com.bbzavrsni.zavrsni.model.pojo.Message;
import com.bbzavrsni.zavrsni.model.pojo.MessageGroup;
import com.bbzavrsni.zavrsni.model.pojo.MessageRecipient;
import com.bbzavrsni.zavrsni.model.pojo.User;
import com.bbzavrsni.zavrsni.repository.interfaces.MessageRecipientRepository;
import com.bbzavrsni.zavrsni.repository.interfaces.MessageRepository;
import com.bbzavrsni.zavrsni.service.interfaces.MessageService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final MessageRecipientRepository messageRecipientRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public MessageServiceImpl(MessageRepository messageRepository, MessageRecipientRepository messageRecipientRepository, EntityManager entityManager) {
        this.messageRepository = messageRepository;
        this.messageRecipientRepository = messageRecipientRepository;
        this.entityManager = entityManager;
    }

    @Override
    public List<MessageDTO> findAllByUser(Long userId) {
        return messageRepository.findAllByRecipient_Recipient_Id(userId).stream().map(this::mapMessageToDTO).collect(Collectors.toList());
    }

    @Override
    public List<MessageDTO> findAllBySender(Long userId, Long senderId) {
        return messageRepository.findAllByRecipient_Recipient_IdAndCreator_Id(userId,senderId).stream().map(this::mapMessageToDTO).collect(Collectors.toList());
    }

    @Override
    public List<MessageDTO> findAllByGroup(Long groupId) {
        return messageRepository.findAllByRecipient_RecipientGroup_Id(groupId).stream().map(this::mapMessageToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<MessageDTO> sendMessage(MessageCommand messageCommand, Long userId) {
        Message sentMessage = messageRepository.save(mapCommandToMessage(messageCommand,userId));
        messageRecipientRepository.save(mapMessageToRecipient(sentMessage, messageCommand.getRecipient(),messageCommand.getRecipientGroup()));
        return Optional.of(mapMessageToDTO(sentMessage));
    }

    private Message mapCommandToMessage(final MessageCommand messageCommand, Long userId){
        return new Message(entityManager.getReference(User.class, userId),
                            messageCommand.getMessageBody(),
                            messageCommand.getParentMessage()!=null?entityManager.getReference(Message.class,messageCommand.getParentMessage()):null);
    }

    private MessageRecipient mapMessageToRecipient(Message message, Long recipientId, Long recipientGroupId){
        return new MessageRecipient(message,
                                    recipientId!=null ? entityManager.getReference(User.class, recipientId):null,
                                    recipientGroupId!=null ? entityManager.getReference(MessageGroup.class,recipientGroupId):null);
    }

    private MessageDTO mapMessageToDTO(final Message message) {
        return new MessageDTO(message.getCreator().getUsername(),message.getCreateDate(),message.getMessageBody());
    }

}
