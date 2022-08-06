package com.bbzavrsni.zavrsni.service;

import com.bbzavrsni.zavrsni.model.dto.CommentDTO;
import com.bbzavrsni.zavrsni.model.pojo.Comment;
import com.bbzavrsni.zavrsni.repository.interfaces.CommentRepository;
import com.bbzavrsni.zavrsni.service.interfaces.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    @Override
    public List<CommentDTO> findCommentForEvent(Long id) {
        return commentRepository.findAllByEvent_Id(id).stream().map(this::mapCommentToDTO).collect(Collectors.toList());
    }

    private CommentDTO mapCommentToDTO(Comment comment) {
        return new CommentDTO(comment.getCreator().getId(),
                comment.getCommentBody(),
                comment.getCreationDate(),
                comment.getParentComment()!=null? comment.getParentComment().getId():null);
    }
}
