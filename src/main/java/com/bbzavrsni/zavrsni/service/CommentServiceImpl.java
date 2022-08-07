package com.bbzavrsni.zavrsni.service;

import com.bbzavrsni.zavrsni.command.CommentCommand;
import com.bbzavrsni.zavrsni.model.dto.CommentDTO;
import com.bbzavrsni.zavrsni.model.pojo.Comment;
import com.bbzavrsni.zavrsni.model.pojo.Event;
import com.bbzavrsni.zavrsni.model.pojo.User;
import com.bbzavrsni.zavrsni.repository.interfaces.CommentRepository;
import com.bbzavrsni.zavrsni.service.interfaces.CommentService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public CommentServiceImpl(CommentRepository commentRepository, EntityManager entityManager) {
        this.commentRepository = commentRepository;
        this.entityManager = entityManager;
    }

    @Override
    public List<CommentDTO> findCommentForEvent(Long id) {
        return commentRepository.findAllByEvent_Id(id).stream().map(this::mapCommentToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<CommentDTO> createComment(CommentCommand commentCommand, Long id) {
        return Optional.of(mapCommentToDTO(commentRepository.save(mapCommandToComment(commentCommand,id))));
    }

    private Comment mapCommandToComment(CommentCommand commentCommand, Long id) {
        return new Comment(entityManager.getReference(User.class,commentCommand.getCreator()),
                commentCommand.getCommentBody(),
                commentCommand.getParentComment()!=null?entityManager.getReference(Comment.class,commentCommand.getParentComment()):null,
                entityManager.getReference(Event.class,id));
    }

    private CommentDTO mapCommentToDTO(Comment comment) {
        return new CommentDTO(comment.getId(),
                comment.getEvent().getId(),
                comment.getCreator().getId(),
                comment.getCommentBody(),
                comment.getCreationDate().toString(),
                comment.getParentComment()!=null? comment.getParentComment().getId():null);
    }
}
