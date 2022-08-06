package com.bbzavrsni.zavrsni.service.interfaces;

import com.bbzavrsni.zavrsni.model.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    List<CommentDTO> findCommentForEvent(Long id);
}
