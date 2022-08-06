package com.bbzavrsni.zavrsni.service.interfaces;

import com.bbzavrsni.zavrsni.command.CommentCommand;
import com.bbzavrsni.zavrsni.model.dto.CommentDTO;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<CommentDTO> findCommentForEvent(Long id);

    Optional<CommentDTO> createComment(CommentCommand commentCommand, Long id);
}
